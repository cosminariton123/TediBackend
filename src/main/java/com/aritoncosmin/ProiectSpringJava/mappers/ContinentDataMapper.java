package com.aritoncosmin.ProiectSpringJava.mappers;

import com.aritoncosmin.ProiectSpringJava.dtos.TemperatureChangeCard;
import com.aritoncosmin.ProiectSpringJava.model.ContinentData;
import org.springframework.stereotype.Component;

@Component
public class ContinentDataMapper {

    public TemperatureChangeCard ContinentDataToTemperatureChangeCard(ContinentData continentData) {
        return new TemperatureChangeCard(
                continentData.getShare_of_temperature_change_from_ghg(),
                continentData.getTemperature_change_from_ch4(),
                continentData.getTemperature_change_from_co2(),
                continentData.getTemperature_change_from_ghg(),
                continentData.getTemperature_change_from_n2o()
        );
    }

}
