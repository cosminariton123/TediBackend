package com.aritoncosmin.ProiectSpringJava.dtos;

public class PopulationAndGdpCard {

    private final Long population;

    private final Double gdp;

    public PopulationAndGdpCard(Long population, Double gdp){
        this.population = population;
        this.gdp = gdp;
    }

    public Long getPopulation() {
        return population;
    }

    public Double getGdp() {
        return gdp;
    }
}
