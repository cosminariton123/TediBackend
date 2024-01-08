package com.aritoncosmin.ProiectSpringJava.controller;

import com.aritoncosmin.ProiectSpringJava.dtos.DriverCreateDTO;
import com.aritoncosmin.ProiectSpringJava.dtos.DriverGetDTO;
import com.aritoncosmin.ProiectSpringJava.dtos.TruckCreateDTO;
import com.aritoncosmin.ProiectSpringJava.dtos.TruckModifyDTO;
import com.aritoncosmin.ProiectSpringJava.mappers.ManagementMapper;
import com.aritoncosmin.ProiectSpringJava.model.Driver;
import com.aritoncosmin.ProiectSpringJava.model.Truck;
import com.aritoncosmin.ProiectSpringJava.service.ManagementService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ManagementControllerTest {

    @InjectMocks
    private ManagementController managementController;

    @Mock
    private ManagementService managementService;

    @Mock
    private ManagementMapper managementMapper;

    @Test
    @DisplayName("Running getTruck")
    void getTruck(){

        Truck searchedTruck = new Truck();
        searchedTruck.setId(1);
        searchedTruck.setBrand("a");

        when(managementService.findTruckById(searchedTruck.getId())).thenReturn(searchedTruck);

        ResponseEntity<Truck> result = managementController.getTruck(searchedTruck.getId());

        assertNotNull(result);
        assertEquals(searchedTruck.getBrand() , result.getBody().getBrand());
    }

    @Test
    @DisplayName("Running saveNewTruck")
    void saveNewTruck(){

        TruckCreateDTO truckCreateDTO = new TruckCreateDTO();
        truckCreateDTO.setBrand("a");

        Truck savedTruck = new Truck();
        savedTruck.setId(1);
        savedTruck.setBrand("a");

        when(managementMapper.TruckCreateDTOToTruck(truckCreateDTO)).thenReturn(savedTruck);
        when(managementService.saveTruck(savedTruck)).thenReturn(savedTruck);

        ResponseEntity<Truck> result = managementController.saveNewTruck(truckCreateDTO);

        assertNotNull(result);
        assertEquals(savedTruck.getBrand() , result.getBody().getBrand());
    }

    @Test
    @DisplayName("Running deleteTruck")
    void deleteTruck(){

        Truck deletedTruck = new Truck();
        deletedTruck.setId(1);
        deletedTruck.setBrand("a");

        when(managementService.deleteTruckById(deletedTruck.getId())).thenReturn(deletedTruck);

        ResponseEntity<Truck> result = managementController.deleteTruck(deletedTruck.getId());

        assertNotNull(result);
        assertEquals(deletedTruck.getBrand(), result.getBody().getBrand());
    }

    @Test
    @DisplayName("Running modifyTruck")
    void modifyTruck(){

        Truck modifiedTruck = new Truck();
        modifiedTruck.setId(1);
        modifiedTruck.setBrand("a");

        TruckModifyDTO truckModifyDTO = new TruckModifyDTO();
        truckModifyDTO.setId(1);
        truckModifyDTO.setBrand("a");

        when(managementService.modifyTruck(modifiedTruck)).thenReturn(modifiedTruck);
        when(managementMapper.TruckModifyDTOToTruck(truckModifyDTO)).thenReturn(modifiedTruck);

        ResponseEntity<Truck> result = managementController.modifyTruck(truckModifyDTO);

        assertNotNull(result);
        assertEquals(modifiedTruck.getBrand(), result.getBody().getBrand());
    }

    @Test
    @DisplayName("Running getDriver")
    void getDriver(){

        Driver searchedDriver = new Driver();
        searchedDriver.setId(1);
        searchedDriver.setFirstName("a");

        DriverGetDTO driverGetDTO = new DriverGetDTO();
        driverGetDTO.setId(searchedDriver.getId());
        driverGetDTO.setFirstName(searchedDriver.getFirstName());

        when(managementService.findDriverById(searchedDriver.getId())).thenReturn(searchedDriver);
        when(managementMapper.DriverToDriverGetDTO(searchedDriver)).thenReturn(driverGetDTO);

        ResponseEntity<DriverGetDTO> result = managementController.getDriver(searchedDriver.getId());

        assertNotNull(result);
        assertEquals(searchedDriver.getFirstName(), result.getBody().getFirstName());
    }

    @Test
    @DisplayName("Running saveNewDriver")
    void saveNewDriver(){

        DriverCreateDTO driverCreateDTO = new DriverCreateDTO();
        driverCreateDTO.setFirstName("a");

        Driver savedDriver = new Driver();
        savedDriver.setId(1);
        savedDriver.setFirstName("a");

        when(managementMapper.DriverCreateDTOToDriver(driverCreateDTO)).thenReturn(savedDriver);
        when(managementService.saveDriver(savedDriver)).thenReturn(savedDriver);

        ResponseEntity<Driver> result = managementController.saveNewDriver(driverCreateDTO);

        assertNotNull(result);
        assertEquals(savedDriver.getFirstName(), result.getBody().getFirstName());
    }

}
