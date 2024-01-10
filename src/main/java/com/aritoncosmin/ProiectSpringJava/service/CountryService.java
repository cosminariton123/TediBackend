package com.aritoncosmin.ProiectSpringJava.service;

import com.aritoncosmin.ProiectSpringJava.exceptions.NotFoundException;
import com.aritoncosmin.ProiectSpringJava.model.Country;
import com.aritoncosmin.ProiectSpringJava.repository.CountryRepository;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository){
        this.countryRepository = countryRepository;
    }

    public Country findCountryByName(String name){
        final Country country = countryRepository.findCountryByName(name);

        if (country == null)
            throw new NotFoundException("Country with name " + name + " not found");

        return  country;
    }

    public Country findCountryByIsoCode(String isoCode){
        final Country country = countryRepository.findCountryByIsoCode(isoCode);

        if (country == null)
            throw new NotFoundException("Country with iso code " + isoCode + " not found");

        return country;
    }

}
