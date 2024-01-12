package com.aritoncosmin.ProiectSpringJava.controller;

import com.aritoncosmin.ProiectSpringJava.dtos.EmissionsCard;
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
import java.util.List;
import java.util.stream.Collectors;

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

    //BY ISO_CODE

    @GetMapping("/iso_code/{iso_code}/data/{year}")
    public ResponseEntity<PopulationAndGdpCard> getPopulationAndGdpByIsoCode(@PathVariable String iso_code,
                                                                             @PathVariable Long year) {
        final Country country = countryService.findCountryByIsoCode(iso_code);
        return getPopulationAndGdp(country, year);
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
        return updatePopulationAndGdp(gdpPopulationYearDTO, country);
    }

    @DeleteMapping("/iso_code/{iso_code}/data/{year}")
    public ResponseEntity<CountryData> deleteCountryDataByIsoCodeAndYear(@PathVariable String iso_code,
                                                                         @PathVariable Long year){
        final Country country = countryService.findCountryByIsoCode(iso_code);
        return deleteCountryData(year, country);
    }


    @GetMapping("/iso_code/{iso_code}/emissions")
    public ResponseEntity<List<EmissionsCard>> getEmissionsDataByIsoCode(@PathVariable String iso_code,
                                            @RequestParam Long year){
        final Country country = countryService.findCountryByIsoCode(iso_code);
        return getListEmissionCards(year, country);

    }


    //BY NAME

    @GetMapping("/name/{name}/data/{year}")
    public ResponseEntity<PopulationAndGdpCard> getPopulationAndGdpByName(@PathVariable String name,
                                                                          @PathVariable Long year) {
        final Country country = countryService.findCountryByName(name);
        return getPopulationAndGdp(country, year);
    }

    @PostMapping("/name/{name}/data")
    public ResponseEntity<CountryData> createPopulationAndGdpByName(@PathVariable String name,
                                                                    @RequestBody @Valid GdpPopulationYearDTO gdpPopulationYearDTO){
        final Country country = countryService.findCountryByName(name);
        final CountryData countryData = countryDataMapper.GdpPopulationYearDTOToCountryData(gdpPopulationYearDTO, country);
        CountryData savedCountryData = countryDataService.saveNew(countryData);
        return ResponseEntity.created(URI.create("/countries/name/" + country.getName() + "/data/" + savedCountryData.getYear())).body(savedCountryData);
    }

    @PutMapping("/name/{name}/data")
    public ResponseEntity<CountryData> updatePopulationAndGdpByName(@PathVariable String name,
                                                                    @RequestBody @Valid GdpPopulationYearDTO gdpPopulationYearDTO){
        final Country country = countryService.findCountryByName(name);
        return updatePopulationAndGdp(gdpPopulationYearDTO, country);
    }

    @DeleteMapping("/name/{name}/data/{year}")
    public ResponseEntity<CountryData> deleteCountryDataByNameAndYear(@PathVariable String name,
                                                                      @PathVariable Long year){
        final Country country = countryService.findCountryByName(name);
        return deleteCountryData(year, country);
    }


    @GetMapping("/name/{name}/emissions")
    public ResponseEntity<List<EmissionsCard>> getEmissionsByName(@PathVariable String name,
                                                                  @RequestParam Long year){
        final Country country = countryService.findCountryByName(name);
        return getListEmissionCards(year, country);

    }

    //Common

    private ResponseEntity<PopulationAndGdpCard> getPopulationAndGdp(Country country, Long year){
        final CountryData countryData = countryDataService.findCountryDataByYearAndCountry(year, country);
        final PopulationAndGdpCard populationAndGdpCard = countryDataMapper.CountryDataToPopulationAndGdpCard(countryData);
        return ResponseEntity.ok(populationAndGdpCard);
    }

    private ResponseEntity<CountryData> deleteCountryData(Long year, Country country){
        final CountryData countryData = countryDataService.findCountryDataByYearAndCountry(year, country);
        final CountryData deletedCountryData = countryDataService.delete(countryData);
        return ResponseEntity.ok(deletedCountryData);
    }

    private ResponseEntity<CountryData> updatePopulationAndGdp(GdpPopulationYearDTO gdpPopulationYearDTO, Country country){
        final CountryData countryData = countryDataMapper.GdpPopulationYearDTOToCountryData(gdpPopulationYearDTO, country);
        CountryData savedCountryData = countryDataService.savePopulationAndGdp(countryData);
        return ResponseEntity.ok(savedCountryData);
    }

    private ResponseEntity<List<EmissionsCard>> getListEmissionCards(Long year, Country country) {
        final List<CountryData> countryDataList = countryDataService.findAllByYearGreaterThanEqualAndCountry(year, country);
        final List<EmissionsCard> emissionsCardList = countryDataList
                .stream()
                .map(countryDataMapper::CountryDataToEmissionsCard)
                .collect(Collectors.toList());

        if (emissionsCardList.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(emissionsCardList);
    }

}
