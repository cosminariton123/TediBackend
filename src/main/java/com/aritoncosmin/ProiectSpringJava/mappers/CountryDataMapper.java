package com.aritoncosmin.ProiectSpringJava.mappers;

import com.aritoncosmin.ProiectSpringJava.dtos.DataCreate;
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

    public CountryData GdpPopulationYearCreate(DataCreate dataCreate, Country country) {
        return new CountryData(
                dataCreate.getYear(),
                dataCreate.getPopulation(),
                dataCreate.getGDP(),
                dataCreate.getCo2(),
                dataCreate.getCo2_per_capita(),
                dataCreate.getCo2_per_GDP(),
                dataCreate.getShare_of_temperature_change_from_ghg(),
                dataCreate.getTemperature_change_from_ch4(),
                dataCreate.getTemperature_change_from_co2(),
                dataCreate.getTemperature_change_from_ghg(),
                dataCreate.getTemperature_change_from_n2o(),
                country
        );
    }

}
