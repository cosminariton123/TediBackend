package com.aritoncosmin.ProiectSpringJava.controller;

import com.aritoncosmin.ProiectSpringJava.dtos.GdpPopulationYearDTO;
import com.aritoncosmin.ProiectSpringJava.dtos.PopulationAndGdpCard;
import com.aritoncosmin.ProiectSpringJava.mappers.CountryDataMapper;
import com.aritoncosmin.ProiectSpringJava.model.Country;
import com.aritoncosmin.ProiectSpringJava.model.CountryData;
import com.aritoncosmin.ProiectSpringJava.service.CountryDataService;
import com.aritoncosmin.ProiectSpringJava.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@Validated
@RequestMapping("/countries")
public class CountryDataController {

    private final CountryDataService countryDataService;

    private final CountryDataMapper countryDataMapper;

    private final CountryService countryService;

    public CountryDataController(CountryDataService countryDataService, CountryDataMapper countryDataMapper, CountryService countryService) {
        this.countryDataService = countryDataService;
        this.countryDataMapper = countryDataMapper;
        this.countryService = countryService;
    }

    @GetMapping("/iso_code/{iso_code}/data/{year}")
    public ResponseEntity<PopulationAndGdpCard> retrievePopulationAndGdpByIsoCode(@PathVariable String iso_code,
                                                                                  @PathVariable Long year) {
        final Country country = countryService.findCountryByIsoCode(iso_code);
        final CountryData countryData = countryDataService.findCountryDataByYearAndCountry(year, country);
        final PopulationAndGdpCard populationAndGdpCard = countryDataMapper.CountryDataToPopulationAndGdpCard(countryData);
        return ResponseEntity.ok(populationAndGdpCard);
    }


    @PostMapping("/iso_code/{iso_code}/data")
    public ResponseEntity<CountryData> createPopulationAndGdpByIsoCode(@PathVariable String iso_code,
                                                                       @RequestBody @Valid GdpPopulationYearDTO gdpPopulationYearDTO){
        final Country country = countryService.findCountryByIsoCode(iso_code);
        final CountryData countryData = countryDataMapper.GdpPopulationYearDTOToCountryData(gdpPopulationYearDTO, country);
        CountryData savedCountryData = countryDataService.saveNew(countryData);
        return ResponseEntity.created(URI.create("/countries/iso_code/" + country.getIsoCode() + "/data/" + savedCountryData.getYear())).body(savedCountryData);
    }

    @PutMapping("/iso_code/{iso_code}/data")
    public ResponseEntity<CountryData> updatePopulationAndGdpByIsoCode(@PathVariable String iso_code,
                                                                       @RequestBody @Valid GdpPopulationYearDTO gdpPopulationYearDTO){
        final Country country = countryService.findCountryByIsoCode(iso_code);
        final CountryData countryData = countryDataMapper.GdpPopulationYearDTOToCountryData(gdpPopulationYearDTO, country);
        CountryData savedCountryData = countryDataService.savePopulationAndGdp(countryData);
        return  ResponseEntity.ok(savedCountryData);
    }

    @DeleteMapping("/iso_code/{iso_code}/data/{year}")
    public ResponseEntity<CountryData> deleteCountryDataByIsoCodeAndYear(@PathVariable String iso_code,
                                                                         @PathVariable Long year){
        final Country country = countryService.findCountryByIsoCode(iso_code);
        final CountryData countryData = countryDataService.findCountryDataByYearAndCountry(year, country);
        final CountryData deletedCountryData = countryDataService.delete(countryData);
        return ResponseEntity.ok(deletedCountryData);
    }

    @GetMapping("/name/{name}/data/{year}")
    public ResponseEntity<PopulationAndGdpCard> retrievePopulationAndGdpByName(@PathVariable String name,
                                                                         @PathVariable Long year) {
        final Country country = countryService.findCountryByName(name);
        final CountryData countryData = countryDataService.findCountryDataByYearAndCountry(year, country);
        final PopulationAndGdpCard populationAndGdpCard = countryDataMapper.CountryDataToPopulationAndGdpCard(countryData);
        return ResponseEntity.ok(populationAndGdpCard);
    }

    @PostMapping("/name/{name}/data")
    public ResponseEntity<CountryData> createPopulationAndGdpByName(@PathVariable String name,
                                                                    @RequestBody @Valid GdpPopulationYearDTO gdpPopulationYearDTO){
        final Country country = countryService.findCountryByName(name);
        final CountryData countryData = countryDataMapper.GdpPopulationYearDTOToCountryData(gdpPopulationYearDTO, country);
        CountryData savedCountryData = countryDataService.saveNew(countryData);
        return ResponseEntity.created(URI.create("/countries/name/" + country.getIsoCode() + "/data/" + savedCountryData.getYear())).body(savedCountryData);
    }

    @PutMapping("/name/{name}/data")
    public ResponseEntity<CountryData> updatePopulationAndGdpByName(@PathVariable String name,
                                                                    @RequestBody @Valid GdpPopulationYearDTO gdpPopulationYearDTO){
        final Country country = countryService.findCountryByName(name);
        final CountryData countryData = countryDataMapper.GdpPopulationYearDTOToCountryData(gdpPopulationYearDTO, country);
        CountryData savedCountryData = countryDataService.savePopulationAndGdp(countryData);
        return ResponseEntity.ok(savedCountryData);
    }

    @DeleteMapping("/name/{name}/data/{year}")
    public ResponseEntity<CountryData> deleteCountryDataByNameAndYear(@PathVariable String name,
                                                                      @PathVariable Long year){
        final Country country = countryService.findCountryByName(name);
        final CountryData countryData = countryDataService.findCountryDataByYearAndCountry(year, country);
        final CountryData deletedCountryData = countryDataService.delete(countryData);
        return ResponseEntity.ok(deletedCountryData);
    }

}
