package com.aritoncosmin.ProiectSpringJava.mappers;

import com.aritoncosmin.ProiectSpringJava.dtos.InventoryCreateDTO;
import com.aritoncosmin.ProiectSpringJava.dtos.InventoryModifyDTO;
import com.aritoncosmin.ProiectSpringJava.dtos.ServiceAutoCreateDTO;
import com.aritoncosmin.ProiectSpringJava.dtos.ServiceAutoModifyDTO;
import com.aritoncosmin.ProiectSpringJava.model.Inventory;
import com.aritoncosmin.ProiectSpringJava.model.ServiceAuto;
import com.aritoncosmin.ProiectSpringJava.service.MaintenanceService;
import org.springframework.stereotype.Component;

@Component
public class MaintenanceMapper {

    MaintenanceService maintenanceService;

    public MaintenanceMapper(MaintenanceService maintenanceService){
        this.maintenanceService = maintenanceService;
    }

    public ServiceAuto ServiceAutoCreateDTOToServiceAuto(ServiceAutoCreateDTO serviceAutoCreateDTO){
        ServiceAuto serviceAuto = new ServiceAuto();
        serviceAuto.setName(serviceAutoCreateDTO.getName());
        serviceAuto.setNrOfWorkers(serviceAutoCreateDTO.getNrOfWorkers());

        Inventory inventory = maintenanceService.findInventoryById(serviceAutoCreateDTO.getInventoryId());
        serviceAuto.setInventory(inventory);
        return serviceAuto;
    }

    public ServiceAuto ServiceAutoModifyDTOToServiceAuto(ServiceAutoModifyDTO serviceAutoModifyDTO){
        ServiceAuto serviceAuto = new ServiceAuto();
        serviceAuto.setName(serviceAutoModifyDTO.getName());
        serviceAuto.setNrOfWorkers(serviceAutoModifyDTO.getNrOfWorkers());

        Inventory inventory = maintenanceService.findInventoryById(serviceAutoModifyDTO.getInventoryId());
        serviceAuto.setInventory(inventory);
        return serviceAuto;

    }

    public Inventory InventoryCreateDTOToInventory(InventoryCreateDTO inventoryCreateDTO){
        Inventory inventory = new Inventory();
        inventory.setWindshieldQuantity(inventoryCreateDTO.getWindshieldQuantity());
        inventory.setRadiatorQuantity(inventoryCreateDTO.getRadiatorQuantity());
        inventory.setFanQuantity(inventoryCreateDTO.getFanQuantity());
        inventory.setEngineQuantity(inventoryCreateDTO.getEngineQuantity());
        return inventory;
    }

    public Inventory InventoryModifyDTOToInventory(InventoryModifyDTO inventoryModifyDTO){
        Inventory inventory = new Inventory();
        inventory.setWindshieldQuantity(inventoryModifyDTO.getWindshieldQuantity());
        inventory.setRadiatorQuantity(inventoryModifyDTO.getRadiatorQuantity());
        inventory.setFanQuantity(inventoryModifyDTO.getFanQuantity());
        inventory.setEngineQuantity(inventoryModifyDTO.getEngineQuantity());
        return inventory;
    }
}
