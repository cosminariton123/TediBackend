package com.aritoncosmin.ProiectSpringJava.service;

import com.aritoncosmin.ProiectSpringJava.exceptions.BadRequest;
import com.aritoncosmin.ProiectSpringJava.exceptions.InternalServerError;
import com.aritoncosmin.ProiectSpringJava.exceptions.NotFoundException;
import com.aritoncosmin.ProiectSpringJava.model.Inventory;
import com.aritoncosmin.ProiectSpringJava.model.ServiceAuto;
import com.aritoncosmin.ProiectSpringJava.repository.InventoryRepository;
import com.aritoncosmin.ProiectSpringJava.repository.ServiceAutoRepository;
import io.swagger.models.auth.In;
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
public class MaintenanceServiceTest {

    @InjectMocks
    MaintenanceService maintenanceService;

    @Mock
    ServiceAutoRepository serviceAutoRepository;

    @Mock
    InventoryRepository inventoryRepository;

    @Test
    void findServiceAutoByIdHappyFlow(){
        ServiceAuto serviceAuto = new ServiceAuto();
        serviceAuto.setId(1);

        when(serviceAutoRepository.findServiceAutoById(serviceAuto.getId())).thenReturn(serviceAuto);

        ServiceAuto result = maintenanceService.findServiceAutoById(serviceAuto.getId());

        assertEquals(serviceAuto.getId(), result.getId());
    }

    @Test
    void findServiceAutoSadFlow(){
        ServiceAuto serviceAuto = new ServiceAuto();
        serviceAuto.setId(1);

        when(serviceAutoRepository.findServiceAutoById(serviceAuto.getId())).thenReturn(null);

        RuntimeException exception = assertThrows(NotFoundException.class,
                () -> maintenanceService.findServiceAutoById(serviceAuto.getId()));

        assertEquals("ServiceAuto with id " + serviceAuto.getId() + " not found", exception.getMessage());
    }

    @Test
    void saveServiceAutoHappyFlow(){
        Inventory inventory = new Inventory();
        inventory.setId(1);

        ServiceAuto serviceAuto = new ServiceAuto();
        serviceAuto.setId(1);
        serviceAuto.setInventory(inventory);

        ServiceAuto anotherServiceAuto = new ServiceAuto();
        anotherServiceAuto.setId(2);

        when(serviceAutoRepository.findServiceAutoByInventoryId(serviceAuto.getInventory().getId())).thenReturn(serviceAuto);
        when(serviceAutoRepository.save(serviceAuto)).thenReturn(serviceAuto);

        ServiceAuto result = maintenanceService.saveServiceAuto(serviceAuto);

        assertEquals(serviceAuto.getId(), result.getId());
    }

    @Test
    void saveServiceAutoSadFlow(){
        Inventory inventory = new Inventory();
        inventory.setId(1);

        ServiceAuto serviceAuto = new ServiceAuto();
        serviceAuto.setId(1);
        serviceAuto.setInventory(inventory);

        ServiceAuto anotherServiceAuto = new ServiceAuto();
        anotherServiceAuto.setId(2);
        anotherServiceAuto.setInventory(inventory);

        when(serviceAutoRepository.findServiceAutoByInventoryId(serviceAuto.getInventory().getId())).thenReturn(anotherServiceAuto);

        Exception exception = assertThrows(BadRequest.class,
                () -> maintenanceService.saveServiceAuto(serviceAuto));

        assertEquals("Given inventory is already assigned to the serviceAuto with id " + anotherServiceAuto.getId(), exception.getMessage());
    }

    @Test
    void modifyServiceAuto(){
        Inventory inventory = new Inventory();
        inventory.setId(1);

        ServiceAuto serviceAuto = new ServiceAuto();
        serviceAuto.setId(1);
        serviceAuto.setInventory(inventory);
        serviceAuto.setName("a");
        serviceAuto.setNrOfWorkers(1);

        ServiceAuto anotherServiceAuto = new ServiceAuto();
        anotherServiceAuto.setId(1);

        when(serviceAutoRepository.findServiceAutoById(serviceAuto.getId())).thenReturn(anotherServiceAuto);
        when(serviceAutoRepository.save(anotherServiceAuto)).thenReturn(anotherServiceAuto);

        ServiceAuto result = maintenanceService.modifyServiceAuto(serviceAuto);

        assertEquals(serviceAuto.getId(), result.getId());
        assertEquals(serviceAuto.getName(), result.getName());
        assertEquals(serviceAuto.getInventory(), result.getInventory());
        assertEquals(serviceAuto.getNrOfWorkers(), result.getNrOfWorkers());
    }


    @Test
    void deleteServiceAutoByIdHappyFlow(){
        ServiceAuto serviceAuto = new ServiceAuto();
        serviceAuto.setId(1);

        when(serviceAutoRepository.findServiceAutoById(serviceAuto.getId())).thenReturn(serviceAuto);
        when(serviceAutoRepository.deleteServiceAutoById(serviceAuto.getId())).thenReturn(1);

        ServiceAuto result = maintenanceService.deleteServiceAutoById(serviceAuto.getId());

        assertEquals(serviceAuto.getId(), result.getId());

    }

    @Test
    void deleteServiceAutoByIdSadFlow(){
        ServiceAuto serviceAuto = new ServiceAuto();
        serviceAuto.setId(1);

        when(serviceAutoRepository.findServiceAutoById(serviceAuto.getId())).thenReturn(serviceAuto);
        when(serviceAutoRepository.deleteServiceAutoById(serviceAuto.getId())).thenReturn(0);

        Exception exception = assertThrows(InternalServerError.class,
                () -> maintenanceService.deleteServiceAutoById(serviceAuto.getId()));

        assertEquals("Deleted count <=0, serviceAuto with id " + serviceAuto.getId() + " still exists", exception.getMessage());
    }

    @Test
    void findInventoryByIdHappyFlow(){
        Inventory inventory = new Inventory();
        inventory.setId(1);

        when(inventoryRepository.findInventoryById(inventory.getId())).thenReturn(inventory);

        Inventory result = maintenanceService.findInventoryById(inventory.getId());

        assertEquals(inventory.getId(), result.getId());
    }

    @Test
    void findInventoryByIdSadFlow(){
        Inventory inventory = new Inventory();
        inventory.setId(1);

        when(inventoryRepository.findInventoryById(inventory.getId())).thenReturn(null);

        Exception exception = assertThrows(NotFoundException.class,
                () -> maintenanceService.findInventoryById(inventory.getId()));

        assertEquals("inventory with id " + inventory.getId() + " not found", exception.getMessage());
    }

    @Test
    void saveInventory(){
        Inventory inventory = new Inventory();
        inventory.setId(1);

        when(inventoryRepository.save(inventory)).thenReturn(inventory);

        Inventory result = maintenanceService.saveInventory(inventory);

        assertEquals(inventory.getId(), result.getId());

    }

    @Test
    void modifyInventory(){
        Inventory inventory = new Inventory();
        inventory.setId(1);
        inventory.setEngineQuantity(1);
        inventory.setFanQuantity(1);
        inventory.setRadiatorQuantity(1);
        inventory.setWindshieldQuantity(1);

        Inventory anotherInventory = new Inventory();
        anotherInventory.setId(1);

        when(inventoryRepository.save(anotherInventory)).thenReturn(anotherInventory);
        when(inventoryRepository.findInventoryById(inventory.getId())).thenReturn(anotherInventory);

        Inventory result = maintenanceService.modifyInventory(inventory);

        assertEquals(inventory.getId(), result.getId());
        assertEquals(inventory.getEngineQuantity(), result.getEngineQuantity());
        assertEquals(inventory.getFanQuantity(), result.getFanQuantity());
        assertEquals(inventory.getRadiatorQuantity(), result.getRadiatorQuantity());

    }

    @Test
    void deleteInventoryByIdHappyFlow(){
        Inventory inventory = new Inventory();
        inventory.setId(1);

        ServiceAuto serviceAuto = new ServiceAuto();
        serviceAuto.setId(1);
        serviceAuto.setInventory(inventory);

        when(inventoryRepository.findInventoryById(inventory.getId())).thenReturn(inventory);
        when(serviceAutoRepository.findServiceAutoByInventoryId(serviceAuto.getInventory().getId())).thenReturn(serviceAuto);
        when(inventoryRepository.deleteInventoryById(inventory.getId())).thenReturn(1);

        Inventory result = maintenanceService.deleteInventoryById(inventory.getId());

        assertEquals(inventory.getId(), result.getId());
    }

    @Test
    void deleteInventoryByIdSadFlow(){
        Inventory inventory = new Inventory();
        inventory.setId(1);

        ServiceAuto serviceAuto = new ServiceAuto();
        serviceAuto.setId(1);
        serviceAuto.setInventory(inventory);

        when(inventoryRepository.findInventoryById(inventory.getId())).thenReturn(inventory);
        when(serviceAutoRepository.findServiceAutoByInventoryId(serviceAuto.getInventory().getId())).thenReturn(serviceAuto);
        when(inventoryRepository.deleteInventoryById(inventory.getId())).thenReturn(0);

        Exception exception = assertThrows(InternalServerError.class,
                () -> maintenanceService.deleteInventoryById(inventory.getId()));

        assertEquals("Deleted count <=0, inventory with id " + inventory.getId() + " still exists", exception.getMessage());
    }
}
