package com.aritoncosmin.ProiectSpringJava.service;

import com.aritoncosmin.ProiectSpringJava.exceptions.BadRequest;
import com.aritoncosmin.ProiectSpringJava.exceptions.InternalServerError;
import com.aritoncosmin.ProiectSpringJava.exceptions.NotFoundException;
import com.aritoncosmin.ProiectSpringJava.model.*;
import com.aritoncosmin.ProiectSpringJava.repository.DriverRepository;
import com.aritoncosmin.ProiectSpringJava.repository.LongHaulRepository;
import com.aritoncosmin.ProiectSpringJava.repository.PlaylistRepository;
import com.aritoncosmin.ProiectSpringJava.repository.TruckRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ManagementService {
    TruckRepository truckRepository;
    DriverRepository driverRepository;
    LongHaulRepository longHaulRepository;
    PlaylistRepository playlistRepository;

    ManagementService(TruckRepository truckRepository, DriverRepository driverRepository, LongHaulRepository longHaulRepository, PlaylistRepository playlistRepository){
        this.truckRepository = truckRepository;
        this.driverRepository = driverRepository;
        this.longHaulRepository = longHaulRepository;
        this.playlistRepository = playlistRepository;
    }

    public Truck findTruckById(Integer truckId){
        Truck truck = truckRepository.findTruckById(truckId);
        if (truck == null)
            throw new NotFoundException("Truck with id " + truckId + " not found");
        return truck;
    }

    public Truck saveTruck(Truck truck){
        return truckRepository.save(truck);}

    public Truck modifyTruck(Truck truck){
        Truck foundTruck = findTruckById(truck.getId());
        foundTruck.setKm(truck.getKm());
        foundTruck.setBrand(truck.getBrand());
        return saveTruck(truck);
    }

    @Transactional
    public Truck deleteTruckById(Integer truckId){
        Truck deletedTruck = findTruckById(truckId);

        Driver driver = driverRepository.findDriverByTruckId(truckId);

        if (driver != null){
            driver.setTruck(null);
            saveDriver(driver);
        }

        List<LongHaul> longHaulList = longHaulRepository.findLongHaulsByTruckId(truckId);

        for (LongHaul longHaul:
             longHaulList) {
            longHaul.setTruck(null);
            longHaulRepository.save(longHaul);
        }

        Integer deletedCount =  truckRepository.deleteTruckById(truckId);

        if (deletedCount > 0)
            return deletedTruck;

        throw new InternalServerError("Deleted count <= 0, truck with id " + deletedTruck.getId() + " still exists");
    }

    public Driver findDriverById(Integer driverId){
        Driver foundDriver = driverRepository.findDriverById(driverId);

        if (foundDriver == null)
            throw new NotFoundException("Driver with id " + driverId + " not found");
        return foundDriver;
    }

    public Driver saveDriver(Driver driver){
        Driver driverAlreadyAssignedToTruck = null;
        if (driver.getTruck() != null)
            driverAlreadyAssignedToTruck = driverRepository.findDriverByTruckId(driver.getTruck().getId());

        if (driverAlreadyAssignedToTruck != null && driverAlreadyAssignedToTruck != driver)
            throw new BadRequest("Given truck is already driven by driver with id " + driverAlreadyAssignedToTruck.getId());

        Set<Playlist> set = new HashSet<Playlist>(driver.getPlaylists());
        if(set.size() < driver.getPlaylists().size())
            throw new BadRequest("Given playlist list contains duplicates. Remove the duplicates and try again");

        return driverRepository.save(driver);
    }

    public Driver modifyDriver(Driver driver){
        Driver foundDriver = findDriverById(driver.getId());
        foundDriver.setTruck(driver.getTruck());
        foundDriver.setFirstName(driver.getFirstName());
        foundDriver.setAge(driver.getAge());
        foundDriver.setLastName(driver.getLastName());

        return saveDriver(foundDriver);
    }

    @Transactional
    public Driver deleteDriverById(Integer id){
        Driver deletedDriver = findDriverById(id);
        Integer deletedCount = driverRepository.deleteDriverById(id);

        if (deletedCount > 0)
            return deletedDriver;

        throw new InternalServerError("Deleted count <= 0, driver with id " + deletedDriver.getId() + " still exists");
    }

    public LongHaul findLongHaulById(Integer id){
        LongHaul longHaul = longHaulRepository.findLongHaulById(id);

        if (longHaul == null)
            throw new NotFoundException("Long haul with id " + id + " not found");

        return longHaul;
    }

    public LongHaul saveLongHaul(LongHaul longHaul){

        Set<Hotel> set = new HashSet<Hotel>(longHaul.getHotelList());
        if(set.size() < longHaul.getHotelList().size())
            throw new BadRequest("Given hotel list contains duplicates. Remove the duplicates and try again");
        return longHaulRepository.save(longHaul);
    }

    public LongHaul modifyLongHaul(LongHaul longHaul){
        LongHaul foundLongHaul = findLongHaulById(longHaul.getId());
        foundLongHaul.setStartingAddress(longHaul.getStartingAddress());
        foundLongHaul.setDestinationAddress(longHaul.getDestinationAddress());
        foundLongHaul.setTruck(longHaul.getTruck());
        foundLongHaul.setHotelList(longHaul.getHotelList());
        return saveLongHaul(foundLongHaul);
    }

    @Transactional
    public LongHaul deleteLongHaulById(Integer id){
        LongHaul deletedLongHaul = findLongHaulById(id);
        Integer deletedCount = longHaulRepository.deleteLongHaulById(id);

        if (deletedCount > 0)
            return deletedLongHaul;

        throw new InternalServerError("Deleted count <=0, long haul with id " + id + " still exists");
    }
}
