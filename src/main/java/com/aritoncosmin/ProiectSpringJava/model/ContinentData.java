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

    public ContinentData(Long year, Long population, Double gdp, Float co2, Float co2_per_capita, Float co2_per_gdp, Float energy_per_capita, Float energy_per_gdp, Float methane, Float methane_per_capita, Float nitrous_oxide, Float nitrous_oxide_per_capita, Float share_of_temperature_change_from_ghg, Float temperature_change_from_ch4, Float temperature_change_from_co2, Float temperature_change_from_ghg, Float temperature_change_from_n2o, Float total_ghg, Continent continent) {
        super(year, population, gdp, co2, co2_per_capita, co2_per_gdp, energy_per_capita, energy_per_gdp, methane, methane_per_capita, nitrous_oxide, nitrous_oxide_per_capita, share_of_temperature_change_from_ghg, temperature_change_from_ch4, temperature_change_from_co2, temperature_change_from_ghg, temperature_change_from_n2o, total_ghg);
        this.continent = continent;
    }

    public Continent getContinent() {
        return continent;
    }
}
