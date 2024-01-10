package com.aritoncosmin.ProiectSpringJava.service;

import com.aritoncosmin.ProiectSpringJava.exceptions.InternalServerError;
import com.aritoncosmin.ProiectSpringJava.repository.ContinentDataRepository;
import com.aritoncosmin.ProiectSpringJava.repository.ContinentRepository;
import com.aritoncosmin.ProiectSpringJava.repository.CountryDataRepository;
import com.aritoncosmin.ProiectSpringJava.repository.CountryRepository;
import com.aritoncosmin.ProiectSpringJava.util.SavedDatabaseContinent;
import com.aritoncosmin.ProiectSpringJava.util.SavedDatabaseCountry;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import static com.aritoncosmin.ProiectSpringJava.config.Paths.databaseContinentsCopyPath;
import static com.aritoncosmin.ProiectSpringJava.config.Paths.databaseCountriesCopyPath;

@Service
public class ScriptsService {

    private final ContinentDataRepository continentDataRepository;

    private final ContinentRepository continentRepository;

    private final CountryDataRepository countryDataRepository;

    private final CountryRepository countryRepository;

    public ScriptsService(ContinentDataRepository continentDataRepository, ContinentRepository continentRepository, CountryDataRepository countryDataRepository, CountryRepository countryRepository) {
        this.continentDataRepository = continentDataRepository;
        this.continentRepository = continentRepository;
        this.countryDataRepository = countryDataRepository;
        this.countryRepository = countryRepository;
    }

    private void loadDatabase() throws JsonProcessingException {
        final ObjectMapper objectMapper = new ObjectMapper();
        SavedDatabaseCountry savedDatabaseCountry = objectMapper.readValue(databaseCountriesCopyPath, SavedDatabaseCountry.class);
        SavedDatabaseContinent savedDatabaseContinent = objectMapper.readValue(databaseContinentsCopyPath, SavedDatabaseContinent.class);

    }

    private void destroyDatabase() {
        continentDataRepository.deleteAll();
        countryDataRepository.deleteAll();
        continentRepository.deleteAll();
        countryRepository.deleteAll();
    }

    public void resetDatabase() {
        destroyDatabase();

        try {
            loadDatabase();
        } catch (JsonProcessingException e) {
            throw new InternalServerError("Failed to process json when loading database copy");
        }
    }

}
