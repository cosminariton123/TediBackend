package com.aritoncosmin.ProiectSpringJava.controller;

import com.aritoncosmin.ProiectSpringJava.dtos.TemperatureChangeCard;
import com.aritoncosmin.ProiectSpringJava.mappers.ContinentDataMapper;
import com.aritoncosmin.ProiectSpringJava.model.Continent;
import com.aritoncosmin.ProiectSpringJava.model.ContinentData;
import com.aritoncosmin.ProiectSpringJava.service.ContinentDataService;
import com.aritoncosmin.ProiectSpringJava.service.ContinentService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Validated
@RequestMapping("/continents")
public class ContinentDataController {

    private final ContinentService continentService;

    private final ContinentDataService continentDataService;

    private final ContinentDataMapper continentDataMapper;

    public ContinentDataController(ContinentService continentService, ContinentDataService continentDataService, ContinentDataMapper continentDataMapper){
        this.continentService = continentService;
        this.continentDataService = continentDataService;
        this.continentDataMapper = continentDataMapper;
    }

    @GetMapping("/name/{name}/temperature_change")
    public ResponseEntity<List<TemperatureChangeCard>> getTemperatureChange(@PathVariable String name,
                                                                            @RequestParam Long year) {
        final Continent continent = continentService.findContinentByName(name);
        final List<ContinentData> continentDataList = continentDataService.findAllByYearGreaterThanEqualAndContinent(year, continent);
        final List<TemperatureChangeCard> temperatureChangeCardList = continentDataList
                .stream()
                .map(continentDataMapper::ContinentDataToTemperatureChangeCard)
                .collect(Collectors.toList());

        if (temperatureChangeCardList.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(temperatureChangeCardList);
    }

}
