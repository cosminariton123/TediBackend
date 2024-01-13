package com.aritoncosmin.ProiectSpringJava.service;

import com.aritoncosmin.ProiectSpringJava.exceptions.BadRequest;
import com.aritoncosmin.ProiectSpringJava.exceptions.NotFoundException;
import com.aritoncosmin.ProiectSpringJava.model.Country;
import com.aritoncosmin.ProiectSpringJava.model.CountryData;
import com.aritoncosmin.ProiectSpringJava.repository.CountryDataRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryDataService {

    private final CountryService countryService;

    private final CountryDataRepository countryDataRepository;

    public CountryDataService(CountryService countryService, CountryDataRepository countryDataRepository) {
        this.countryService = countryService;
        this.countryDataRepository = countryDataRepository;
    }

    public CountryData findCountryDataByYearAndCountry(Long year, Country country){
        final CountryData countryData = countryDataRepository.findCountryDataByYearAndCountry(year, country);

        if (countryData == null)
            throw new NotFoundException("The country has no data in year " + year);

        return countryData;
    }

    public List<CountryData> findAllByYearGreaterThanEqualAndCountry(Long year, Country country){
        return countryDataRepository.findAllByYearGreaterThanEqualAndCountry(year, country, Sort.by(Sort.Direction.ASC, CountryData.yearField));
    }

    public CountryData saveNew(CountryData countryData){
        CountryData alreadyExisting = countryDataRepository.findCountryDataByYearAndCountry(countryData.getYear(), countryData.getCountry());

        if (alreadyExisting != null)
            throw new BadRequest("Data with year " + countryData.getYear() + " for country " + countryData.getCountry().getName() + " already exists");

        return countryDataRepository.save(countryData);
    }

    public CountryData save(CountryData countryData){
        return countryDataRepository.save(countryData);
    }

    public CountryData savePopulationAndGdp(CountryData countryData){
        CountryData alreadyExisting = countryDataRepository.findCountryDataByYearAndCountry(countryData.getYear(), countryData.getCountry());

        if (alreadyExisting == null)
            throw new BadRequest("Data with year " + countryData.getYear() + " for country " + countryData.getCountry().getName() + " doesn't exist. Can't be updated");

        alreadyExisting.setGdp(countryData.getGdp());
        alreadyExisting.setPopulation(countryData.getPopulation());

        return save(alreadyExisting);
    }

    public CountryData delete(CountryData countryData){
        countryDataRepository.delete(countryData);
        return countryData;
    }

}
