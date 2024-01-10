package com.aritoncosmin.ProiectSpringJava.model;

import javax.persistence.*;

@Entity
@Table(name = "Countries_data")
public class CountryData extends Data{

    @OneToOne
    @JoinColumn(name = "country_id")
    private Country country;

    public CountryData() {
    }

    public CountryData(Integer year, Integer population, Float gdp, Float co2, Float co2PerCapita, Float co2PerGdp, Float shareOfTemperatureChangeFromGhg, Float temperatureChangeFromCh4, Float temperatureChangeFromCo2, Float temperatureChangeFromGhg, Float temperatureChangeFromn2o, Country country) {
        super(year, population, gdp, co2, co2PerCapita, co2PerGdp, shareOfTemperatureChangeFromGhg, temperatureChangeFromCh4, temperatureChangeFromCo2, temperatureChangeFromGhg, temperatureChangeFromn2o);
        this.country = country;
    }

    public Country getCountry() {
        return country;
    }
}
