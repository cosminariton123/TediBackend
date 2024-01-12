package com.aritoncosmin.ProiectSpringJava.service;

import com.aritoncosmin.ProiectSpringJava.exceptions.InternalServerError;
import com.aritoncosmin.ProiectSpringJava.model.Continent;
import com.aritoncosmin.ProiectSpringJava.model.ContinentData;
import com.aritoncosmin.ProiectSpringJava.model.Country;
import com.aritoncosmin.ProiectSpringJava.model.CountryData;
import com.aritoncosmin.ProiectSpringJava.repository.ContinentDataRepository;
import com.aritoncosmin.ProiectSpringJava.repository.ContinentRepository;
import com.aritoncosmin.ProiectSpringJava.repository.CountryDataRepository;
import com.aritoncosmin.ProiectSpringJava.repository.CountryRepository;
import com.aritoncosmin.ProiectSpringJava.util.SavedDatabaseContinents;
import com.aritoncosmin.ProiectSpringJava.util.SavedDatabaseCountries;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    private void loadDatabase() throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        SavedDatabaseCountries savedDatabaseCountries = objectMapper.readValue(new File(databaseCountriesCopyPath), SavedDatabaseCountries.class);
        SavedDatabaseContinents savedDatabaseContinents = objectMapper.readValue(new File(databaseContinentsCopyPath), SavedDatabaseContinents.class);

        List<Country> countries = savedDatabaseCountries.getCountries()
                .stream()
                .map(country -> new Country(
                        country.getIso_code(),
                        country.getName()))
                .collect(Collectors.toList());

        countryRepository.saveAll(countries);


        List<CountryData> countryDataList = savedDatabaseCountries.getCountries()
                .stream()
                .map(country -> country.getData()
                        .stream()
                        .map(data -> new CountryData(
                                data.getYear(),
                                data.getPopulation(),
                                data.getGdp(),
                                data.getCo2(),
                                data.getCo2_per_capita(),
                                data.getCo2_per_gdp(),
                                data.getEnergy_per_capita(),
                                data.getEnergy_per_gdp(),
                                data.getMethane(),
                                data.getMethane_per_capita(),
                                data.getNitrous_oxide(),
                                data.getNitrous_oxide_per_capita(),
                                data.getShare_of_temperature_change_from_ghg(),
                                data.getTemperature_change_from_ch4(),
                                data.getTemperature_change_from_co2(),
                                data.getTemperature_change_from_ghg(),
                                data.getTemperature_change_from_n2o(),
                                data.getTotal_ghg(),
                                countries.stream().filter(elem -> Objects.equals(elem.getName(), country.getName())).collect(Collectors.toList()).get(0)
                                //countryRepository.findCountryByIsoCode(country.getIso_code())
                        )).collect(Collectors.toList())
                ).flatMap(List::stream)
                .collect(Collectors.toList());

        countryDataRepository.saveAll(countryDataList);


        List<Continent> continents = savedDatabaseContinents.getContinents()
                .stream()
                .map(continent -> new Continent(continent.getName()))
                .collect(Collectors.toList());

        continentRepository.saveAll(continents);


        List<ContinentData> continentDataList = savedDatabaseContinents.getContinents()
                .stream()
                .map(continent -> continent.getData()
                        .stream()
                        .map(data -> new ContinentData(
                                data.getYear(),
                                data.getPopulation(),
                                data.getGdp(),
                                data.getCo2(),
                                data.getCo2_per_capita(),
                                data.getCo2_per_gdp(),
                                data.getEnergy_per_capita(),
                                data.getEnergy_per_gdp(),
                                data.getMethane(),
                                data.getMethane_per_capita(),
                                data.getNitrous_oxide(),
                                data.getNitrous_oxide_per_capita(),
                                data.getShare_of_temperature_change_from_ghg(),
                                data.getTemperature_change_from_ch4(),
                                data.getTemperature_change_from_co2(),
                                data.getTemperature_change_from_ghg(),
                                data.getTemperature_change_from_n2o(),
                                data.getTotal_ghg(),
                                continents.stream().filter(elem -> Objects.equals(elem.getName(), continent.getName())).collect(Collectors.toList()).get(0)
                                //continentRepository.findContinentByName(continent.getName())
                        )).collect(Collectors.toList())
                ).flatMap(List::stream)
                .collect(Collectors.toList());

        continentDataRepository.saveAll(continentDataList);

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
            throw new InternalServerError(e.toString());
            //throw new InternalServerError("Failed to process json when loading database copy");
        } catch (IOException e) {
            throw new InternalServerError(e.toString());
            //throw new InternalServerError("Database save file is missing or couldn't be opened");
        }
    }

}
