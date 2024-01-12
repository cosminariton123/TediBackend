package com.aritoncosmin.ProiectSpringJava.mappers;

import com.aritoncosmin.ProiectSpringJava.dtos.EmissionsCard;
import com.aritoncosmin.ProiectSpringJava.dtos.GdpPopulationYearDTO;
import com.aritoncosmin.ProiectSpringJava.dtos.PopulationAndGdpCard;
import com.aritoncosmin.ProiectSpringJava.model.Country;
import com.aritoncosmin.ProiectSpringJava.model.CountryData;
import com.aritoncosmin.ProiectSpringJava.service.CountryDataService;
import org.springframework.stereotype.Component;

@Component
public class CountryDataMapper {

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

    public EmissionsCard CountryDataToEmissionsCard(CountryData countryData){
        return new EmissionsCard(
                countryData.getCo2(),
                countryData.getCo2_per_capita(),
                countryData.getCo2_per_gdp(),
                countryData.getMethane(),
                countryData.getMethane_per_capita(),
                countryData.getNitrous_oxide(),
                countryData.getNitrous_oxide_per_capita(),
                countryData.getTotal_ghg()
        );
    }

}
