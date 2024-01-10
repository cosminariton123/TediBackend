package com.aritoncosmin.ProiectSpringJava.controller;

import com.aritoncosmin.ProiectSpringJava.dtos.DataCreate;
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

    @GetMapping("/{iso_code}/data/{year}")
    public ResponseEntity<PopulationAndGdpCard> retrievePopulationAndGdp(@PathVariable String iso_code,
                                                                         @PathVariable Integer year) {
        final CountryData countryData = countryDataService.findCountryDataByYearAndCountryIso(year, iso_code);
        final PopulationAndGdpCard populationAndGdpCard = countryDataMapper.CountryDataToPopulationAndGdpCard(countryData);
        return ResponseEntity.ok(populationAndGdpCard);
    }

    @PostMapping("/{iso_code}/data/{year}")
    public ResponseEntity<CountryData> createPopulationAndGdp(@PathVariable String iso_code,
                                                              @PathVariable Integer year,
                                                              @RequestBody @Valid DataCreate dataCreate) {
        final Country country = countryService.findCountryByIsoCode(iso_code);
        final CountryData countryData = countryDataMapper.GdpPopulationYearCreate(dataCreate, country);
        return ResponseEntity.created(URI.create("/countries/" + country.getIsoCode() + "/data/" + countryData.getYear())).body(countryData);
    }

}
