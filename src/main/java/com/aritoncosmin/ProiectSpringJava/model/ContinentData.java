package com.aritoncosmin.ProiectSpringJava.model;

import javax.persistence.*;

@Entity
@Table(name = ContinentData.tableName)
public class ContinentData extends Data{

    public static final String tableName = "continents_data";
    public static final String continentField = "continent_id";

    @OneToOne
    @JoinColumn(name = continentField)
    private Continent continent;

    public ContinentData() {
    }

    public ContinentData(Long year, Long population, Double gdp, Double co2, Double co2_per_capita, Double co2_per_gdp, Double energy_per_capita, Double energy_per_gdp, Double methane, Double methane_per_capita, Double nitrous_oxide, Double nitrous_oxide_per_capita, Double share_of_temperature_change_from_ghg, Double temperature_change_from_ch4, Double temperature_change_from_co2, Double temperature_change_from_ghg, Double temperature_change_from_n2o, Double total_ghg, Continent continent) {
        super(year, population, gdp, co2, co2_per_capita, co2_per_gdp, energy_per_capita, energy_per_gdp, methane, methane_per_capita, nitrous_oxide, nitrous_oxide_per_capita, share_of_temperature_change_from_ghg, temperature_change_from_ch4, temperature_change_from_co2, temperature_change_from_ghg, temperature_change_from_n2o, total_ghg);
        this.continent = continent;
    }

    public Continent getContinent() {
        return continent;
    }
}
