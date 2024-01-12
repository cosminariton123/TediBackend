package com.aritoncosmin.ProiectSpringJava.dtos;

public class EmissionsCard {

    private final Double co2;

    private final Double co2_per_capita;

    private final Double co2_per_gdp;

    private final Double methane;

    private final Double methane_per_capita;

    private final Double nitrous_oxide;

    private final Double nitrous_oxide_per_capita;

    private final Double total_ghg;

    public EmissionsCard(Double co2, Double co2_per_capita, Double co2_per_gdp, Double methane, Double methane_per_capita, Double nitrous_oxide, Double nitrous_oxide_per_capita, Double total_ghg) {
        this.co2 = co2;
        this.co2_per_capita = co2_per_capita;
        this.co2_per_gdp = co2_per_gdp;
        this.methane = methane;
        this.methane_per_capita = methane_per_capita;
        this.nitrous_oxide = nitrous_oxide;
        this.nitrous_oxide_per_capita = nitrous_oxide_per_capita;
        this.total_ghg = total_ghg;
    }

    public Double getCo2() {
        return co2;
    }

    public Double getCo2_per_capita() {
        return co2_per_capita;
    }

    public Double getCo2_per_gdp() {
        return co2_per_gdp;
    }

    public Double getMethane() {
        return methane;
    }

    public Double getMethane_per_capita() {
        return methane_per_capita;
    }

    public Double getNitrous_oxide() {
        return nitrous_oxide;
    }

    public Double getNitrous_oxide_per_capita() {
        return nitrous_oxide_per_capita;
    }

    public Double getTotal_ghg() {
        return total_ghg;
    }
}
