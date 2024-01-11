package com.aritoncosmin.ProiectSpringJava.model;

import javax.persistence.*;

@MappedSuperclass
public abstract class Data {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "year")
    private Long year;

    @Column(name = "population")
    private Long population;

    @Column(name = "gdp")
    private Double gdp;

    @Column(name = "co2")
    private Float co2;

    @Column(name = "co2_per_capita")
    private Float co2_per_capita;

    @Column(name = "co2_per_gdp")
    private Float co2_per_gdp;

    @Column(name = "energy_per_capita")
    private Float energy_per_capita;

    @Column(name = "energy_per_gdp")
    private Float energy_per_gdp;

    @Column(name = "methane")
    private Float methane;

    @Column(name = "methane_per_capita")
    private Float methane_per_capita;

    @Column(name = "nitrous_oxide")
    private Float nitrous_oxide;

    @Column(name = "nitrous_oxide_per_capita")
    private Float nitrous_oxide_per_capita;

    @Column(name = "share_of_temperature_change_from_ghg")
    private Float share_of_temperature_change_from_ghg;

    @Column(name = "temperature_change_from_ch4")
    private Float temperature_change_from_ch4;

    @Column(name = "temperature_change_from_co2")
    private Float temperature_change_from_co2;

    @Column(name = "temperature_change_from_ghg")
    private Float temperature_change_from_ghg;

    @Column(name = "temperature_change_from_n2o")
    private Float temperature_change_from_n2o;

    @Column(name = "total_ghg")
    private Float total_ghg;

    public Data() {

    }

    public Data(Long year, Long population, Double gdp, Float co2, Float co2_per_capita, Float co2_per_gdp, Float energy_per_capita, Float energy_per_gdp, Float methane, Float methane_per_capita, Float nitrous_oxide, Float nitrous_oxide_per_capita, Float share_of_temperature_change_from_ghg, Float temperature_change_from_ch4, Float temperature_change_from_co2, Float temperature_change_from_ghg, Float temperature_change_from_n2o, Float total_ghg) {
        this.year = year;
        this.population = population;
        this.gdp = gdp;
        this.co2 = co2;
        this.co2_per_capita = co2_per_capita;
        this.co2_per_gdp = co2_per_gdp;
        this.energy_per_capita = energy_per_capita;
        this.energy_per_gdp = energy_per_gdp;
        this.methane = methane;
        this.methane_per_capita = methane_per_capita;
        this.nitrous_oxide = nitrous_oxide;
        this.nitrous_oxide_per_capita = nitrous_oxide_per_capita;
        this.share_of_temperature_change_from_ghg = share_of_temperature_change_from_ghg;
        this.temperature_change_from_ch4 = temperature_change_from_ch4;
        this.temperature_change_from_co2 = temperature_change_from_co2;
        this.temperature_change_from_ghg = temperature_change_from_ghg;
        this.temperature_change_from_n2o = temperature_change_from_n2o;
        this.total_ghg = total_ghg;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public Double getGdp() {
        return gdp;
    }

    public void setGdp(Double gdp) {
        this.gdp = gdp;
    }

    public Float getCo2() {
        return co2;
    }

    public void setCo2(Float co2) {
        this.co2 = co2;
    }

    public Float getCo2_per_capita() {
        return co2_per_capita;
    }

    public void setCo2_per_capita(Float co2_per_capita) {
        this.co2_per_capita = co2_per_capita;
    }

    public Float getCo2_per_gdp() {
        return co2_per_gdp;
    }

    public void setCo2_per_gdp(Float co2_per_gdp) {
        this.co2_per_gdp = co2_per_gdp;
    }

    public Float getEnergy_per_capita() {
        return energy_per_capita;
    }

    public void setEnergy_per_capita(Float energy_per_capita) {
        this.energy_per_capita = energy_per_capita;
    }

    public Float getEnergy_per_gdp() {
        return energy_per_gdp;
    }

    public void setEnergy_per_gdp(Float energy_per_gdp) {
        this.energy_per_gdp = energy_per_gdp;
    }

    public Float getMethane() {
        return methane;
    }

    public void setMethane(Float methane) {
        this.methane = methane;
    }

    public Float getMethane_per_capita() {
        return methane_per_capita;
    }

    public void setMethane_per_capita(Float methane_per_capita) {
        this.methane_per_capita = methane_per_capita;
    }

    public Float getNitrous_oxide() {
        return nitrous_oxide;
    }

    public void setNitrous_oxide(Float nitrous_oxide) {
        this.nitrous_oxide = nitrous_oxide;
    }

    public Float getNitrous_oxide_per_capita() {
        return nitrous_oxide_per_capita;
    }

    public void setNitrous_oxide_per_capita(Float nitrous_oxide_per_capita) {
        this.nitrous_oxide_per_capita = nitrous_oxide_per_capita;
    }

    public Float getShare_of_temperature_change_from_ghg() {
        return share_of_temperature_change_from_ghg;
    }

    public void setShare_of_temperature_change_from_ghg(Float share_of_temperature_change_from_ghg) {
        this.share_of_temperature_change_from_ghg = share_of_temperature_change_from_ghg;
    }

    public Float getTemperature_change_from_ch4() {
        return temperature_change_from_ch4;
    }

    public void setTemperature_change_from_ch4(Float temperature_change_from_ch4) {
        this.temperature_change_from_ch4 = temperature_change_from_ch4;
    }

    public Float getTemperature_change_from_co2() {
        return temperature_change_from_co2;
    }

    public void setTemperature_change_from_co2(Float temperature_change_from_co2) {
        this.temperature_change_from_co2 = temperature_change_from_co2;
    }

    public Float getTemperature_change_from_ghg() {
        return temperature_change_from_ghg;
    }

    public void setTemperature_change_from_ghg(Float temperature_change_from_ghg) {
        this.temperature_change_from_ghg = temperature_change_from_ghg;
    }

    public Float getTemperature_change_from_n2o() {
        return temperature_change_from_n2o;
    }

    public void setTemperature_change_from_n2o(Float temperature_change_from_n2o) {
        this.temperature_change_from_n2o = temperature_change_from_n2o;
    }

    public Float getTotal_ghg() {
        return total_ghg;
    }

    public void setTotal_ghg(Float total_ghg) {
        this.total_ghg = total_ghg;
    }
}
