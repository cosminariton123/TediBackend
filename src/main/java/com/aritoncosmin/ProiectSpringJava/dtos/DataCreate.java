package com.aritoncosmin.ProiectSpringJava.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class DataCreate {

    @NotNull(message = "GDP must not be null")
    @Min(message = "GDP must be bigger or equal to 0", value = 0)
    private final Float GDP;

    @NotNull(message = "population must not be null")
    @Min(message = "population must be bigger or equal to 0", value = 0)
    private final Integer population;

    @NotNull(message = "year must not be null")
    private final Integer year;

    @Null(message = "co2 must be null")
    private final Float co2;

    @Null(message = "co2_per_capita must be null")
    private final Float co2_per_capita;

    @Null(message = "co2_per_GDP must be null")
    private final Float co2_per_GDP;

    @Null(message = "share_of_temperature_change_from_ghg must be null")
    private final Float share_of_temperature_change_from_ghg;

    @Null(message = "temperature_change_from_ch4 must be null")
    private final Float temperature_change_from_ch4;

    @Null(message = "temperature_change_from_co2 must be null")
    private final Float temperature_change_from_co2;

    @Null(message = "temperature_change_from_ghg must be null")
    private final Float temperature_change_from_ghg;

    @Null(message = "temperature_change_from_n2o must be null")
    private final Float temperature_change_from_n2o;

    public DataCreate(Float GDP, Integer population, Integer year, Float co2, Float co2_per_capita, Float co2_per_GDP, Float share_of_temperature_change_from_ghg, Float temperature_change_from_ch4, Float temperature_change_from_co2, Float temperature_change_from_ghg, Float temperature_change_from_n2o) {
        this.GDP = GDP;
        this.population = population;
        this.year = year;
        this.co2 = co2;
        this.co2_per_capita = co2_per_capita;
        this.co2_per_GDP = co2_per_GDP;
        this.share_of_temperature_change_from_ghg = share_of_temperature_change_from_ghg;
        this.temperature_change_from_ch4 = temperature_change_from_ch4;
        this.temperature_change_from_co2 = temperature_change_from_co2;
        this.temperature_change_from_ghg = temperature_change_from_ghg;
        this.temperature_change_from_n2o = temperature_change_from_n2o;
    }

    public Float getGDP() {
        return GDP;
    }

    public Integer getPopulation() {
        return population;
    }

    public Integer getYear() {
        return year;
    }

    public Float getCo2() {
        return co2;
    }

    public Float getCo2_per_capita() {
        return co2_per_capita;
    }

    public Float getCo2_per_GDP() {
        return co2_per_GDP;
    }

    public Float getShare_of_temperature_change_from_ghg() {
        return share_of_temperature_change_from_ghg;
    }

    public Float getTemperature_change_from_ch4() {
        return temperature_change_from_ch4;
    }

    public Float getTemperature_change_from_co2() {
        return temperature_change_from_co2;
    }

    public Float getTemperature_change_from_ghg() {
        return temperature_change_from_ghg;
    }

    public Float getTemperature_change_from_n2o() {
        return temperature_change_from_n2o;
    }
}
