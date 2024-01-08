package com.aritoncosmin.ProiectSpringJava.service;

import com.aritoncosmin.ProiectSpringJava.exceptions.BadRequest;
import com.aritoncosmin.ProiectSpringJava.exceptions.InternalServerError;
import com.aritoncosmin.ProiectSpringJava.exceptions.NotFoundException;
import com.aritoncosmin.ProiectSpringJava.model.Inventory;
import com.aritoncosmin.ProiectSpringJava.model.ServiceAuto;
import com.aritoncosmin.ProiectSpringJava.repository.InventoryRepository;
import com.aritoncosmin.ProiectSpringJava.repository.ServiceAutoRepository;
import org.springframework.stereotype.Service;

@Service
public class MaintenanceService {

    ServiceAutoRepository serviceAutoRepository;
    InventoryRepository inventoryRepository;

    public MaintenanceService(ServiceAutoRepository serviceAutoRepository, InventoryRepository inventoryRepository){
        this.serviceAutoRepository = serviceAutoRepository;
        this.inventoryRepository = inventoryRepository;
    }

    public ServiceAuto findServiceAutoById(Integer id){
        ServiceAuto serviceAuto = serviceAutoRepository.findServiceAutoById(id);

        if (serviceAuto == null)
            throw new NotFoundException("ServiceAuto with id " + id + " not found");

        return serviceAuto;
    }

    public ServiceAuto saveServiceAuto(ServiceAuto serviceAuto){
        ServiceAuto serviceAutoThatAlreadyHasAsignedGivenInventory = null;
        if (serviceAuto.getInventory() != null)
            serviceAutoThatAlreadyHasAsignedGivenInventory = serviceAutoRepository.findServiceAutoByInventoryId(serviceAuto.getInventory().getId());

        if (serviceAutoThatAlreadyHasAsignedGivenInventory != null && serviceAutoThatAlreadyHasAsignedGivenInventory != serviceAuto)
            throw new BadRequest("Given inventory is already assigned to the serviceAuto with id " + serviceAutoThatAlreadyHasAsignedGivenInventory.getId());
        return serviceAutoRepository.save(serviceAuto);
    }

    public ServiceAuto modifyServiceAuto(ServiceAuto serviceAuto){
        ServiceAuto foundServiceAuto = findServiceAutoById(serviceAuto.getId());
        foundServiceAuto.setName(serviceAuto.getName());
        foundServiceAuto.setNrOfWorkers(serviceAuto.getNrOfWorkers());
        foundServiceAuto.setInventory(serviceAuto.getInventory());

        return saveServiceAuto(foundServiceAuto);
    }

    public ServiceAuto deleteServiceAutoById(Integer id){
        ServiceAuto foundServiceAuto = findServiceAutoById(id);

        Integer deletedCount = serviceAutoRepository.deleteServiceAutoById(foundServiceAuto.getId());

        if (deletedCount > 0)
            return foundServiceAuto;

        throw new InternalServerError("Deleted count <=0, serviceAuto with id " + foundServiceAuto.getId() + " still exists");
    }

    public Inventory findInventoryById(Integer id){
        Inventory inventory = inventoryRepository.findInventoryById(id);

        if (inventory == null)
            throw new NotFoundException("inventory with id " + id + " not found");

        return  inventory;
    }

    public Inventory saveInventory(Inventory inventory){
        return inventoryRepository.save(inventory);
    }

    public Inventory modifyInventory(Inventory inventory){
        Inventory foundInventory = findInventoryById(inventory.getId());
        foundInventory.setEngineQuantity(inventory.getEngineQuantity());
        foundInventory.setFanQuantity(inventory.getFanQuantity());
        foundInventory.setRadiatorQuantity(inventory.getRadiatorQuantity());
        foundInventory.setWindshieldQuantity(inventory.getWindshieldQuantity());

        return saveInventory(foundInventory);
    }

    public Inventory deleteInventoryById(Integer id){
        Inventory foundInventory = findInventoryById(id);

        ServiceAuto serviceAuto = serviceAutoRepository.findServiceAutoByInventoryId(foundInventory.getId());

        if (serviceAuto != null){
            serviceAuto.setInventory(null);
            saveServiceAuto(serviceAuto);
        }

        Integer deletedCount = inventoryRepository.deleteInventoryById(foundInventory.getId());

        if (deletedCount > 0)
            return foundInventory;

        throw new InternalServerError("Deleted count <=0, inventory with id " + foundInventory.getId() + " still exists");
    }
}
