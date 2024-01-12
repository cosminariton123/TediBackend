package com.aritoncosmin.ProiectSpringJava.service;

import com.aritoncosmin.ProiectSpringJava.exceptions.NotFoundException;
import com.aritoncosmin.ProiectSpringJava.model.Continent;
import com.aritoncosmin.ProiectSpringJava.model.ContinentData;
import com.aritoncosmin.ProiectSpringJava.repository.ContinentDataRepository;
import com.aritoncosmin.ProiectSpringJava.repository.ContinentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContinentDataService {

    private final ContinentDataRepository continentDataRepository;

    private final ContinentRepository continentRepository;

    public ContinentDataService(ContinentDataRepository continentDataRepository, ContinentRepository continentRepository) {
        this.continentDataRepository = continentDataRepository;
        this.continentRepository = continentRepository;
    }

    public ContinentData findContinentDataByYearAndContinent(Long year, Continent continent){
        final ContinentData continentData = continentDataRepository.findContinentDataByYearAndContinent(year, continent);

        if (continentData == null)
            throw new NotFoundException("The continent has no data in year " + year);

        return continentData;
    }

    public List<ContinentData> findAllByYearGreaterThanEqualAndContinent(Long year, Continent continent) {
        return continentDataRepository.findAllByYearGreaterThanEqualAndContinent(year, continent);
    }

}
