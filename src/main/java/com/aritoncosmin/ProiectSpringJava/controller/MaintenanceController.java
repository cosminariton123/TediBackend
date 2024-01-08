package com.aritoncosmin.ProiectSpringJava.controller;

import com.aritoncosmin.ProiectSpringJava.dtos.InventoryCreateDTO;
import com.aritoncosmin.ProiectSpringJava.dtos.InventoryModifyDTO;
import com.aritoncosmin.ProiectSpringJava.dtos.ServiceAutoCreateDTO;
import com.aritoncosmin.ProiectSpringJava.dtos.ServiceAutoModifyDTO;
import com.aritoncosmin.ProiectSpringJava.mappers.MaintenanceMapper;
import com.aritoncosmin.ProiectSpringJava.model.Inventory;
import com.aritoncosmin.ProiectSpringJava.model.ServiceAuto;
import com.aritoncosmin.ProiectSpringJava.service.MaintenanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@Validated
@RequestMapping("/maintenance")
public class MaintenanceController {

    MaintenanceService maintenanceService;
    MaintenanceMapper maintenanceMapper;

    public MaintenanceController(MaintenanceService maintenanceService, MaintenanceMapper maintenanceMapper){
        this.maintenanceService = maintenanceService;
        this.maintenanceMapper = maintenanceMapper;
    }

    @GetMapping("/service_auto/{id}")
    public ResponseEntity<ServiceAuto> findServiceAuto(@PathVariable Integer id){
        ServiceAuto foundServiceAuto = maintenanceService.findServiceAutoById(id);
        return ResponseEntity.ok().body(foundServiceAuto);
    }

    @PostMapping("/service_auto")
    public ResponseEntity<ServiceAuto> saveNewServiceAuto(@RequestBody @Valid ServiceAutoCreateDTO serviceAutoCreateDTO){
        ServiceAuto serviceAuto = maintenanceMapper.ServiceAutoCreateDTOToServiceAuto(serviceAutoCreateDTO);
        ServiceAuto savedServiceAuto = maintenanceService.saveServiceAuto(serviceAuto);
        return ResponseEntity.created(URI.create("/maintenance/service_auto/" + savedServiceAuto.getId())).body(savedServiceAuto);
    }

    @PutMapping("/service_auto")
    public ResponseEntity<ServiceAuto> modifyServiceAuto(@RequestBody @Valid ServiceAutoModifyDTO serviceAutoModifyDTO){
        ServiceAuto serviceAuto = maintenanceMapper.ServiceAutoModifyDTOToServiceAuto(serviceAutoModifyDTO);
        ServiceAuto savedServiceAuto = maintenanceService.modifyServiceAuto(serviceAuto);
        return ResponseEntity.ok().body(savedServiceAuto);
    }

    @DeleteMapping("/service_auto/{id}")
    public ResponseEntity<ServiceAuto> deleteServiceAutoById(@PathVariable Integer id){
        ServiceAuto deletedServiceAuto = maintenanceService.deleteServiceAutoById(id);
        return ResponseEntity.ok().body(deletedServiceAuto);
    }

    @GetMapping("/inventory/{id}")
    public ResponseEntity<Inventory> findInventoryById(@PathVariable Integer id){
        Inventory foundInventory = maintenanceService.findInventoryById(id);
        return ResponseEntity.ok().body(foundInventory);
    }

    @PostMapping("/inventory")
    public ResponseEntity<Inventory> saveInventory(@RequestBody @Valid InventoryCreateDTO inventoryCreateDTO){
        Inventory inventory = maintenanceMapper.InventoryCreateDTOToInventory(inventoryCreateDTO);
        Inventory savedInventory = maintenanceService.saveInventory(inventory);
        return ResponseEntity.created(URI.create("/maintenance/inventory/" + savedInventory.getId())).body(savedInventory);
    }

    @PutMapping("/inventory")
    public ResponseEntity<Inventory> modifyInventory(@RequestBody @Valid InventoryModifyDTO inventoryModifyDTO){
        Inventory inventory = maintenanceMapper.InventoryModifyDTOToInventory(inventoryModifyDTO);
        Inventory savedInventory = maintenanceService.modifyInventory(inventory);
        return ResponseEntity.ok().body(savedInventory);
    }

    @DeleteMapping("/inventory/{id}")
    public ResponseEntity<Inventory> deleteInventoryById(@PathVariable Integer id){
        Inventory deletedInventory = maintenanceService.deleteInventoryById(id);
        return ResponseEntity.ok().body(deletedInventory);
    }

}
