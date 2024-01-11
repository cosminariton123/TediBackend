package com.aritoncosmin.ProiectSpringJava.mappers;

import com.aritoncosmin.ProiectSpringJava.dtos.GdpPopulationYearDTO;
import com.aritoncosmin.ProiectSpringJava.dtos.PopulationAndGdpCard;
import com.aritoncosmin.ProiectSpringJava.model.Country;
import com.aritoncosmin.ProiectSpringJava.model.CountryData;
import com.aritoncosmin.ProiectSpringJava.service.CountryDataService;
import org.springframework.stereotype.Component;

@Component
public class CountryDataMapper {

    CountryDataService countryDataService;

    public CountryDataMapper(CountryDataService countryDataService) {
        this.countryDataService = countryDataService;
    }

    public PopulationAndGdpCard CountryDataToPopulationAndGdpCard(CountryData countryData){
        return new PopulationAndGdpCard(
                countryData.getPopulation(),
                countryData.getGdp()
        );
    }

    public CountryData GdpPopulationYearDTOToCountryData(GdpPopulationYearDTO gdpPopulationYearDTO, Country country) {
        return new CountryData(
               gdpPopulationYearDTO.getYear(),
                gdpPopulationYearDTO.getPopulation(),
                gdpPopulationYearDTO.getGdp(),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                country
        );
    }

}
