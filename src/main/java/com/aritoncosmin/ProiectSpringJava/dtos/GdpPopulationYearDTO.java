package com.aritoncosmin.ProiectSpringJava.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class GdpPopulationYearDTO {

    @NotNull(message = "year must not be null")
    private final Long year;

    @NotNull(message = "population must not be null")
    @Min(message = "population must be bigger or equal to 0", value = 0)
    private final Long population;

    @NotNull(message = "gdp must not be null")
    @Min(message = "gdp must be bigger or equal to 0", value = 0)
    private final Double gdp;

    public GdpPopulationYearDTO(Long year, Long population, Double gdp) {
        this.year = year;
        this.population = population;
        this.gdp = gdp;
    }

    public Long getYear() {
        return year;
    }

    public Long getPopulation() {
        return population;
    }

    public Double getGdp() {
        return gdp;
    }
}
