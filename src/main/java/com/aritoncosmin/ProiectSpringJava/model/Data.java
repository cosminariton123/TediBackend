package com.aritoncosmin.ProiectSpringJava.model;

import javax.persistence.*;

@MappedSuperclass
public abstract class Data {

    @Id
    @Column(name = "year")
    private Integer year;

    @Column(name = "population")
    private Integer population;

    @Column(name = "gdp")
    private Float gdp;

    @Column(name = "co2")
    private Float co2;

    @Column(name = "co2_per_capita")
    private Float co2PerCapita;

    @Column(name = "co2_per_gdp")
    private Float co2PerGdp;

    @Column(name = "share_of_temperature_change_from_ghg")
    private Float shareOfTemperatureChangeFromGhg;

    @Column(name = "temperature_change_from_ch4")
    private Float temperatureChangeFromCh4;

    @Column(name = "temperature_change_from_co2")
    private Float temperatureChangeFromCo2;

    @Column(name = "temperature_change_from_ghg")
    private Float temperatureChangeFromGhg;

    @Column(name = "temperature_change_from_n2o")
    private Float temperatureChangeFromn2o;

    public Data() {

    }

    public Data(Integer year, Integer population, Float gdp, Float co2, Float co2PerCapita, Float co2PerGdp, Float shareOfTemperatureChangeFromGhg, Float temperatureChangeFromCh4, Float temperatureChangeFromCo2, Float temperatureChangeFromGhg, Float temperatureChangeFromn2o) {
        this.year = year;
        this.population = population;
        this.gdp = gdp;
        this.co2 = co2;
        this.co2PerCapita = co2PerCapita;
        this.co2PerGdp = co2PerGdp;
        this.shareOfTemperatureChangeFromGhg = shareOfTemperatureChangeFromGhg;
        this.temperatureChangeFromCh4 = temperatureChangeFromCh4;
        this.temperatureChangeFromCo2 = temperatureChangeFromCo2;
        this.temperatureChangeFromGhg = temperatureChangeFromGhg;
        this.temperatureChangeFromn2o = temperatureChangeFromn2o;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getPopulation() {
        return population;
    }

    public Float getGdp() {
        return gdp;
    }

    public Float getCo2() {
        return co2;
    }

    public Float getCo2PerCapita() {
        return co2PerCapita;
    }

    public Float getCo2PerGdp() {
        return co2PerGdp;
    }

    public Float getShareOfTemperatureChangeFromGhg() {
        return shareOfTemperatureChangeFromGhg;
    }

    public Float getTemperatureChangeFromCh4() {
        return temperatureChangeFromCh4;
    }

    public Float getTemperatureChangeFromCo2() {
        return temperatureChangeFromCo2;
    }

    public Float getTemperatureChangeFromGhg() {
        return temperatureChangeFromGhg;
    }

    public Float getTemperatureChangeFromn2o() {
        return temperatureChangeFromn2o;
    }
}
