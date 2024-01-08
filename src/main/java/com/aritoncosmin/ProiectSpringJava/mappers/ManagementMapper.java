package com.aritoncosmin.ProiectSpringJava.mappers;

import com.aritoncosmin.ProiectSpringJava.dtos.*;
import com.aritoncosmin.ProiectSpringJava.model.Driver;
import com.aritoncosmin.ProiectSpringJava.model.LongHaul;
import com.aritoncosmin.ProiectSpringJava.model.Truck;
import com.aritoncosmin.ProiectSpringJava.service.HousingService;
import com.aritoncosmin.ProiectSpringJava.service.ManagementService;
import com.aritoncosmin.ProiectSpringJava.service.MusicService;
import org.springframework.stereotype.Component;

@Component
public class ManagementMapper {

    MusicService musicService;
    ManagementService managementService;
    HousingService housingService;

    public ManagementMapper(MusicService musicService, ManagementService managementService, HousingService  housingService){
        this.musicService = musicService;
        this.managementService = managementService;
        this.housingService = housingService;
    }

    public Truck TruckCreateDTOToTruck(TruckCreateDTO truckCreateDTO){
        Truck truck = new Truck();
        truck.setBrand(truckCreateDTO.getBrand());
        truck.setKm(truckCreateDTO.getKm());
        return truck;
    }

    public Truck TruckModifyDTOToTruck(TruckModifyDTO truckModifyDTO){
        Truck truck = new Truck();
        truck.setBrand(truckModifyDTO.getBrand());
        truck.setKm(truckModifyDTO.getKm());
        truck.setId(truckModifyDTO.getId());
        return truck;
    }

    public Driver DriverCreateDTOToDriver(DriverCreateDTO driverCreateDTO){
        Driver driver = new Driver();
        driver.setAge(driverCreateDTO.getAge());
        driver.setFirstName(driverCreateDTO.getFirstName());
        driver.setLastName(driverCreateDTO.getLastName());

        Truck truck = managementService.findTruckById(driverCreateDTO.getTruckId());
        driver.setTruck(truck);

        return driver;
    }

    public Driver DriverModifyDTOToDriver(DriverModifyDTO driverModifyDTO){
        Driver driver = new Driver();
        driver.setId(driverModifyDTO.getId());
        driver.setAge(driverModifyDTO.getAge());
        driver.setLastName(driverModifyDTO.getLastName());
        driver.setFirstName(driverModifyDTO.getFirstName());

        Truck truck = managementService.findTruckById(driverModifyDTO.getTruckId());
        driver.setTruck(truck);

        return driver;
    }

    public LongHaul LongHaulCreateDTOToLongHaul(LongHaulCreateDTO longHaulCreateDTO){
        LongHaul longHaul = new LongHaul();
        longHaul.setStartingAddress(longHaulCreateDTO.getStartingAddress());
        longHaul.setDestinationAddress(longHaulCreateDTO.getDestinationAddress());

        Truck truck = managementService.findTruckById(longHaulCreateDTO.getTruckId());
        longHaul.setTruck(truck);

        for (Integer hotelId:
             longHaulCreateDTO.getHotelListIds()) {
            longHaul.getHotelList().add(housingService.findHotelById(hotelId));
        }
        return longHaul;
    }

    public LongHaul LongHaulModifyDTOToLongHaul(LongHaulModifyDTO longHaulModifyDTO){
        LongHaul longHaul = new LongHaul();
        longHaul.setStartingAddress(longHaulModifyDTO.getStartingAddress());
        longHaul.setDestinationAddress(longHaulModifyDTO.getDestinationAddress());

        Truck truck = managementService.findTruckById(longHaulModifyDTO.getTruckId());
        longHaul.setTruck(truck);

        for (Integer hotelId:
             longHaulModifyDTO.getHotelListIds()) {
            longHaul.getHotelList().add(housingService.findHotelById(hotelId));
        }
        return longHaul;
    }

    public DriverGetDTO DriverToDriverGetDTO(Driver driver){
        DriverGetDTO driverGetDTO = new DriverGetDTO();
        driverGetDTO.setId(driver.getId());
        driverGetDTO.setAge(driver.getAge());
        driverGetDTO.setFirstName(driver.getFirstName());
        driverGetDTO.setLastName(driver.getLastName());
        driverGetDTO.setTruck(driver.getTruck());
        return driverGetDTO;
    }
}
