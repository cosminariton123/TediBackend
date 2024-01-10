package com.aritoncosmin.ProiectSpringJava.service;

import com.aritoncosmin.ProiectSpringJava.exceptions.NotFoundException;
import com.aritoncosmin.ProiectSpringJava.model.Country;
import com.aritoncosmin.ProiectSpringJava.model.CountryData;
import com.aritoncosmin.ProiectSpringJava.model.Data;
import com.aritoncosmin.ProiectSpringJava.repository.CountryDataRepository;
import org.springframework.stereotype.Service;

@Service
public class CountryDataService {

    private final CountryService countryService;

    private final CountryDataRepository countryDataRepository;

    public CountryDataService(CountryService countryService, CountryDataRepository countryDataRepository) {
        this.countryService = countryService;
        this.countryDataRepository = countryDataRepository;
    }

    public CountryData findCountryDataByYearAndCountryName(Integer year, String countryName){
        final Country country = countryService.findCountryByName(countryName);
        final CountryData data = countryDataRepository.findCountryDataByYearAndCountry(year, country);

        if (data == null)
            throw new NotFoundException("Data from year " + year + " and country with name " + country.getName() + " not found");

        return data;
    }

    public CountryData findCountryDataByYearAndCountryIso(Integer year, String countryIso){
        final Country country = countryService.findCountryByIsoCode(countryIso);
        final CountryData data = countryDataRepository.findCountryDataByYearAndCountry(year, country);

        if (data == null)
            throw new NotFoundException("Data from year " + year + " and country with iso " + country.getIsoCode() + " not found");

        return data;
    }

}
