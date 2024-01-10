package com.aritoncosmin.ProiectSpringJava.model;

import javax.persistence.*;

@Entity
@Table(name = "Continents_data")
public class ContinentData extends Data{

    @OneToOne
    @JoinColumn(name = "continent_id")
    private Continent continent;

    public ContinentData() {
    }

    public ContinentData(Integer year, Integer population, Float gdp, Float co2, Float co2PerCapita, Float co2PerGdp, Float shareOfTemperatureChangeFromGhg, Float temperatureChangeFromCh4, Float temperatureChangeFromCo2, Float temperatureChangeFromGhg, Float temperatureChangeFromn2o, Continent continent) {
        super(year, population, gdp, co2, co2PerCapita, co2PerGdp, shareOfTemperatureChangeFromGhg, temperatureChangeFromCh4, temperatureChangeFromCo2, temperatureChangeFromGhg, temperatureChangeFromn2o);
        this.continent = continent;
    }

    public Continent getContinent() {
        return continent;
    }
}
