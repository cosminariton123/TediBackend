package com.aritoncosmin.ProiectSpringJava.controller;

import com.aritoncosmin.ProiectSpringJava.dtos.*;
import com.aritoncosmin.ProiectSpringJava.mappers.ManagementMapper;
import com.aritoncosmin.ProiectSpringJava.model.Driver;
import com.aritoncosmin.ProiectSpringJava.model.LongHaul;
import com.aritoncosmin.ProiectSpringJava.model.Truck;
import com.aritoncosmin.ProiectSpringJava.service.ManagementService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@Validated
@RequestMapping("/management")
public class ManagementController {

    private final ManagementService managementService;
    private final ManagementMapper managementMapper;

    public ManagementController(ManagementService managementService, ManagementMapper managementMapper){
        this.managementService = managementService;
        this.managementMapper = managementMapper;
    }

    @GetMapping("/truck/{id}")
    public ResponseEntity<Truck> getTruck(@PathVariable Integer id){
        return ResponseEntity.ok().body(managementService.findTruckById(id));
    }

    @PostMapping("/truck")
    public ResponseEntity<Truck> saveNewTruck(@RequestBody TruckCreateDTO truckCreateDTO){
        Truck truck = managementMapper.TruckCreateDTOToTruck(truckCreateDTO);
        Truck savedTruck = managementService.saveTruck(truck);
        return ResponseEntity.created(URI.create("/management/truck/" + savedTruck.getId())).body(savedTruck);
    }

    @PutMapping("/truck")
    public ResponseEntity<Truck> modifyTruck(@RequestBody TruckModifyDTO truckModifyDTO){
        Truck truck = managementMapper.TruckModifyDTOToTruck(truckModifyDTO);
        Truck savedTruck = managementService.modifyTruck(truck);
        return ResponseEntity.ok().body(savedTruck);
    }

    @DeleteMapping("/truck/{id}")
    public ResponseEntity<Truck> deleteTruck(@PathVariable Integer id){
        Truck deletedTruck = managementService.deleteTruckById(id);
        return ResponseEntity.ok().body(deletedTruck);
    }

    @GetMapping("/driver/{id}")
    public ResponseEntity<DriverGetDTO> getDriver(@PathVariable Integer id){
        Driver foundDriver = managementService.findDriverById(id);
        DriverGetDTO driverGetDTO = managementMapper.DriverToDriverGetDTO(foundDriver);
        return ResponseEntity.ok().body(driverGetDTO);
    }

    @PostMapping("/driver")
    public ResponseEntity<Driver> saveNewDriver(@RequestBody @Valid DriverCreateDTO driverCreateDTO){
        Driver driver = managementMapper.DriverCreateDTOToDriver(driverCreateDTO);
        Driver savedDriver = managementService.saveDriver(driver);
        return ResponseEntity.created(URI.create("/management/driver/" + savedDriver.getId())).body(savedDriver);
    }

    @PutMapping("/driver")
    public ResponseEntity<DriverGetDTO> modifyDriver(@RequestBody @Valid DriverModifyDTO driverModifyDTO){
        Driver driver = managementMapper.DriverModifyDTOToDriver(driverModifyDTO);
        Driver savedDriver = managementService.modifyDriver(driver);
        DriverGetDTO driverGetDTO = managementMapper.DriverToDriverGetDTO(savedDriver);
        return ResponseEntity.ok().body(driverGetDTO);
    }

    @DeleteMapping("/driver/{id}")
    public ResponseEntity<Driver> deleteDriver(@PathVariable Integer id){
        Driver deletedDriver = managementService.deleteDriverById(id);
        return ResponseEntity.ok().body(deletedDriver);
    }

    @GetMapping("/long_haul/{id}")
    public ResponseEntity<LongHaul> getLongHaul(@PathVariable Integer id){
        LongHaul longHaul = managementService.findLongHaulById(id);
        return ResponseEntity.ok().body(longHaul);
    }

    @PostMapping("/long_haul")
    public ResponseEntity<LongHaul> saveNewLongHaul(@RequestBody @Valid LongHaulCreateDTO longHaulCreateDTO){
        LongHaul longHaul = managementMapper.LongHaulCreateDTOToLongHaul(longHaulCreateDTO);
        LongHaul savedLongHaul = managementService.saveLongHaul(longHaul);
        return ResponseEntity.created(URI.create("/management/long_haul/" + savedLongHaul.getId())).body(savedLongHaul);
    }

    @PutMapping("/long_haul")
    public ResponseEntity<LongHaul> modifyLongHaul(@RequestBody @Valid LongHaulModifyDTO longHaulModifyDTO){
        LongHaul longHaul = managementMapper.LongHaulModifyDTOToLongHaul(longHaulModifyDTO);
        LongHaul savedLongHaul = managementService.modifyLongHaul(longHaul);
        return ResponseEntity.ok().body(savedLongHaul);
    }

    @DeleteMapping("/long_haul/{id}")
    public ResponseEntity<LongHaul> deleteLongHaul(@PathVariable Integer id){
        LongHaul deletedLongHaul = managementService.deleteLongHaulById(id);
        return ResponseEntity.ok().body(deletedLongHaul);
    }

}
