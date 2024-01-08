package com.aritoncosmin.ProiectSpringJava.service;

import com.aritoncosmin.ProiectSpringJava.exceptions.BadRequest;
import com.aritoncosmin.ProiectSpringJava.exceptions.InternalServerError;
import com.aritoncosmin.ProiectSpringJava.exceptions.NotFoundException;
import com.aritoncosmin.ProiectSpringJava.model.*;
import com.aritoncosmin.ProiectSpringJava.repository.DriverRepository;
import com.aritoncosmin.ProiectSpringJava.repository.LongHaulRepository;
import com.aritoncosmin.ProiectSpringJava.repository.TruckRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ManagementServiceTest {

    @InjectMocks
    private ManagementService managementService;

    @Mock
    private TruckRepository truckRepository;

    @Mock
    private DriverRepository driverRepository;

    @Mock
    private LongHaulRepository longHaulRepository;


    @Test
    @DisplayName("Running find by id Truck in a happy flow")
    void findByIdHappyFlow(){
        Truck searchedTruck = new Truck();
        searchedTruck.setId(1);
        searchedTruck.setBrand("a");
        searchedTruck.setKm(1);

        when(truckRepository.findTruckById(searchedTruck.getId())).thenReturn(searchedTruck);

        Truck result = managementService.findTruckById(searchedTruck.getId());

        assertEquals(searchedTruck.getBrand(), result.getBrand());
    }

    @Test
    @DisplayName("Running findTruckById in a sad flow")
    void findTruckByIdSadFlow(){
        Truck searchedTruck = new Truck();
        searchedTruck.setId(-1);
        searchedTruck.setBrand("a");
        searchedTruck.setKm(1);

        when(truckRepository.findTruckById(searchedTruck.getId())).thenReturn(null);

        RuntimeException exception = assertThrows(NotFoundException.class,
                () -> managementService.findTruckById(searchedTruck.getId()));

        assertEquals("Truck with id " + searchedTruck.getId() + " not found", exception.getMessage());
    }


    @Test
    @DisplayName("Running saveTruck")
    void saveTruck(){
        Truck newTruck = new Truck();
        newTruck.setBrand("a");
        newTruck.setKm(1);

        Truck savedTruck = new Truck();
        savedTruck.setId(1);
        savedTruck.setBrand(newTruck.getBrand());
        savedTruck.setKm(newTruck.getKm());

        when(truckRepository.save(newTruck)).thenReturn(savedTruck);

        Truck result = managementService.saveTruck(newTruck);

        assertNotNull(result);
        assertEquals(savedTruck.getBrand(), result.getBrand());
    }


    @Test
    @DisplayName("Running Delete Truck By id Happy Flow")
    void deleteTruckByIdHappyFlow(){

        Truck toDeleteTruck = new Truck();
        toDeleteTruck.setId(1);
        toDeleteTruck.setBrand("a");
        toDeleteTruck.setKm(1);

        Driver driver = new Driver();
        driver.setTruck(toDeleteTruck);


        List<LongHaul> longHaulList = new ArrayList<>();
        LongHaul l1 = new LongHaul();
        LongHaul l2 = new LongHaul();
        longHaulList.add(l1);
        longHaulList.add(l2);

        when(truckRepository.findTruckById(toDeleteTruck.getId())).thenReturn(toDeleteTruck);
        when(truckRepository.deleteTruckById(toDeleteTruck.getId())).thenReturn(1);
        when(longHaulRepository.findLongHaulsByTruckId(toDeleteTruck.getId())).thenReturn(longHaulList);
        when(driverRepository.findDriverByTruckId(toDeleteTruck.getId())).thenReturn(driver);

        Truck result = managementService.deleteTruckById(toDeleteTruck.getId());

        assertNotNull(result);
        assertEquals(toDeleteTruck.getBrand(), result.getBrand());

    }

    @Test
    @DisplayName("Running Delete Truck By id Sad Flow")
    void deleteTruckByIdSadFlow(){

        Truck toDeleteTruck = new Truck();
        toDeleteTruck.setId(1);
        toDeleteTruck.setBrand("a");
        toDeleteTruck.setKm(1);

        List<LongHaul> longHaulList = new ArrayList<>();
        LongHaul l1 = new LongHaul();
        LongHaul l2 = new LongHaul();
        longHaulList.add(l1);
        longHaulList.add(l2);

        when(truckRepository.deleteTruckById(toDeleteTruck.getId())).thenReturn(0);
        when(truckRepository.findTruckById(toDeleteTruck.getId())).thenReturn(toDeleteTruck);
        when(longHaulRepository.findLongHaulsByTruckId(toDeleteTruck.getId())).thenReturn(longHaulList);

        RuntimeException exception = assertThrows(InternalServerError.class,
                () -> managementService.deleteTruckById(toDeleteTruck.getId()));

        assertEquals("Deleted count <= 0, truck with id " + toDeleteTruck.getId() + " still exists", exception.getMessage());
    }

    @Test
    @DisplayName("Running modifyTruck")
    void modifyTruck(){

        Truck modifiedTruck = new Truck();
        modifiedTruck.setId(1);
        modifiedTruck.setBrand("a");
        modifiedTruck.setKm(2);

        Truck dbTruck = new Truck();
        dbTruck.setId(1);
        dbTruck.setBrand("b");
        dbTruck.setKm(1);

        when(truckRepository.findTruckById(modifiedTruck.getId())).thenReturn(dbTruck);
        when(truckRepository.save(modifiedTruck)).thenReturn(modifiedTruck);

        Truck result = managementService.modifyTruck(modifiedTruck);

        assertNotNull(result);
        assertEquals(modifiedTruck.getId(), result.getId());
        assertEquals(modifiedTruck.getBrand(), result.getBrand());
        assertEquals(modifiedTruck.getKm(), result.getKm());
    }


    @Test
    @DisplayName("Running saveDriver happy flow")
    void saveDriverHappyFlow(){
        Driver newDriver = new Driver();
        newDriver.setId(1);
        newDriver.setFirstName("a");

        when(driverRepository.save(newDriver)).thenReturn(newDriver);

        Driver result = managementService.saveDriver(newDriver);

        assertNotNull(result);
        assertEquals(newDriver.getFirstName(), result.getFirstName());
    }

    @Test
    @DisplayName("Running saveDriver sad flow one to one violation")
    void saveDriverSadFlowOneToOneViolation(){
        Truck truck = new Truck();
        truck.setId(1);

        Driver newDriver = new Driver();
        newDriver.setId(1);
        newDriver.setFirstName("a");
        newDriver.setTruck(truck);

        Driver anotherDriver = new Driver();
        anotherDriver.setId(1);
        anotherDriver.setTruck(truck);

        when(driverRepository.findDriverByTruckId(newDriver.getTruck().getId())).thenReturn(anotherDriver);

        RuntimeException exception = assertThrows(BadRequest.class,
                () -> managementService.saveDriver(newDriver));

        assertNotNull(exception);
        assertEquals("Given truck is already driven by driver with id " + anotherDriver.getId(), exception.getMessage());
    }

    @Test
    @DisplayName("Running saveDriver sad flow duplicates problem")
    void saveDriverSadFlowDuplicatesProblem(){
        Truck truck = new Truck();
        truck.setId(1);

        Playlist p1 = new Playlist();
        p1.setId(1);

        Driver newDriver = new Driver();
        newDriver.setId(1);
        newDriver.setFirstName("a");
        newDriver.setTruck(truck);
        newDriver.getPlaylists().add(p1);
        newDriver.getPlaylists().add(p1);

        Driver anotherDriver = new Driver();
        anotherDriver.setId(1);
        anotherDriver.setTruck(truck);

        when(driverRepository.findDriverByTruckId(newDriver.getTruck().getId())).thenReturn(newDriver);

        RuntimeException exception = assertThrows(BadRequest.class,
                () -> managementService.saveDriver(newDriver));

        assertEquals("Given playlist list contains duplicates. Remove the duplicates and try again", exception.getMessage());
    }

    @Test
    @DisplayName("Running findDriverById happy flow")
    void findDriverByIdHappyFlow(){
        Driver searchedDriver = new Driver();
        searchedDriver.setFirstName("a");
        searchedDriver.setId(1);

        when(driverRepository.findDriverById(searchedDriver.getId())).thenReturn(searchedDriver);

        Driver result = managementService.findDriverById(searchedDriver.getId());

        assertNotNull(result);
        assertEquals(searchedDriver.getFirstName(), result.getFirstName());
    }

    @Test
    @DisplayName("Running findDriverById sad flow")
    void findDriverByIdSadFlow(){
        Driver searchedDriver = new Driver();
        searchedDriver.setId(1);

        when(driverRepository.findDriverById(searchedDriver.getId())).thenReturn(null);

        RuntimeException exception = assertThrows(NotFoundException.class,
                () -> managementService.findDriverById(searchedDriver.getId()));

        assertNotNull(exception);
        assertEquals("Driver with id " + searchedDriver.getId() + " not found", exception.getMessage());
    }

    @Test
    @DisplayName("Running modifyingDriver")
    void modifyingDriver(){
        Truck truck = new Truck();
        truck.setBrand("a");

        Driver modifiedDriver = new Driver();
        modifiedDriver.setId(1);
        modifiedDriver.setFirstName("a");
        modifiedDriver.setAge(18);
        modifiedDriver.setLastName("a");
        modifiedDriver.setTruck(truck);

        when(driverRepository.findDriverById(modifiedDriver.getId())).thenReturn(modifiedDriver);
        when(driverRepository.save(modifiedDriver)).thenReturn(modifiedDriver);

        Driver result = managementService.modifyDriver(modifiedDriver);

        //assertNotNull(result);
        assertEquals(modifiedDriver.getId(), result.getId());
        assertEquals(modifiedDriver.getFirstName(), result.getFirstName());
        assertEquals(modifiedDriver.getLastName(), result.getLastName());
        assertEquals(modifiedDriver.getTruck().getBrand(), result.getTruck().getBrand());
        assertEquals(modifiedDriver.getAge(), result.getAge());
    }

    @Test
    @DisplayName("Running deleteDriverById happy flow")
    void deleteDriverByIdHappyFlow(){
        Driver deletedDriver = new Driver();
        deletedDriver.setId(1);

        when(driverRepository.findDriverById(deletedDriver.getId())).thenReturn(deletedDriver);
        when(driverRepository.deleteDriverById(deletedDriver.getId())).thenReturn(1);

        Driver result = managementService.deleteDriverById(deletedDriver.getId());

        assertNotNull(result);
        assertEquals(deletedDriver.getId(), result.getId());

    }

    @Test
    @DisplayName("Running deleteDriverById sad flow")
    void deleteDriverByIdSadFlow(){
        Driver deletedDriver = new Driver();
        deletedDriver.setId(1);

        when(driverRepository.findDriverById(deletedDriver.getId())).thenReturn(deletedDriver);
        when(driverRepository.deleteDriverById(deletedDriver.getId())).thenReturn(0);

        RuntimeException exception = assertThrows(InternalServerError.class,
                () -> managementService.deleteDriverById(deletedDriver.getId()));

        assertNotNull(exception);
        assertEquals("Deleted count <= 0, driver with id " + deletedDriver.getId() + " still exists", exception.getMessage());

    }

    @Test
    @DisplayName("Running findLongHaulById happy flow")
    void findLongHaulByIdHappyFlow(){
        LongHaul longHaul = new LongHaul();
        longHaul.setId(1);

        when(longHaulRepository.findLongHaulById(longHaul.getId())).thenReturn(longHaul);

        LongHaul result = managementService.findLongHaulById(longHaul.getId());

        assertEquals(longHaul.getId(), result.getId());

    }

    @Test
    @DisplayName("Running findLongHaulById sad flow")
    void findLongHaulByIdSadFlow(){
        LongHaul longHaul = new LongHaul();
        longHaul.setId(1);

        when(longHaulRepository.findLongHaulById(longHaul.getId())).thenReturn(null);

        RuntimeException exception = assertThrows(NotFoundException.class,
                () -> managementService.findLongHaulById(longHaul.getId()));

        assertEquals("Long haul with id " + longHaul.getId() + " not found", exception.getMessage());
    }

    @Test
    @DisplayName("Running saveLongHaul happy flow")
    void saveLongHaulHappyFlow(){
        LongHaul longHaul = new LongHaul();
        longHaul.setId(1);

        when(longHaulRepository.save(longHaul)).thenReturn(longHaul);

        LongHaul result = managementService.saveLongHaul(longHaul);

        assertEquals(longHaul.getId(), result.getId());
    }

    @Test
    @DisplayName("Running saveLongHaul sad flow")
    void saveLongHaulSadFlow(){
        Hotel h1 = new Hotel();
        LongHaul longHaul = new LongHaul();
        longHaul.setId(1);
        longHaul.getHotelList().add(h1);
        longHaul.getHotelList().add(h1);

        Exception exception = assertThrows(BadRequest.class,
                () -> managementService.saveLongHaul(longHaul));

        assertEquals("Given hotel list contains duplicates. Remove the duplicates and try again", exception.getMessage() );
    }

    @Test
    void modifyLongHaul(){
        Truck truck = new Truck();
        truck.setId(1);

        LongHaul longHaul = new LongHaul();
        longHaul.setId(1);
        longHaul.setDestinationAddress("a");
        longHaul.setStartingAddress("a");
        longHaul.setTruck(truck);

        LongHaul anotherLongHaul = new LongHaul();
        anotherLongHaul.setId(1);

        when(longHaulRepository.findLongHaulById(longHaul.getId())).thenReturn(anotherLongHaul);
        when(longHaulRepository.save(anotherLongHaul)).thenReturn(anotherLongHaul);

        LongHaul result = managementService.modifyLongHaul(longHaul);

        assertEquals(longHaul.getId(),  result.getId());
        assertEquals(longHaul.getHotelList(), result.getHotelList());
        assertEquals(longHaul.getTruck(), result.getTruck());
        assertEquals(longHaul.getDestinationAddress(), result.getDestinationAddress());
        assertEquals(longHaul.getStartingAddress(), result.getStartingAddress());
    }

    @Test
    void deleteLongHaulByIdHappyFlow(){
        LongHaul longHaul = new LongHaul();
        longHaul.setId(1);

        when(longHaulRepository.findLongHaulById(longHaul.getId())).thenReturn(longHaul);
        when(longHaulRepository.deleteLongHaulById(longHaul.getId())).thenReturn(1);

        LongHaul result = managementService.deleteLongHaulById(longHaul.getId());

        assertEquals(longHaul.getId(), result.getId());
    }

    @Test
    void deleteLongHaulByIdSadFlow(){
        LongHaul longHaul = new LongHaul();
        longHaul.setId(1);

        when(longHaulRepository.findLongHaulById(longHaul.getId())).thenReturn(longHaul);
        when(longHaulRepository.deleteLongHaulById(longHaul.getId())).thenReturn(0);

        RuntimeException exception = assertThrows(InternalServerError.class,
                () -> managementService.deleteLongHaulById(longHaul.getId()));

        assertEquals("Deleted count <=0, long haul with id " + longHaul.getId() + " still exists", exception.getMessage());
    }
}
