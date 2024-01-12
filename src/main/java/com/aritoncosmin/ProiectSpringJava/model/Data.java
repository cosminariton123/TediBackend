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
    private Double co2;

    @Column(name = "co2_per_capita")
    private Double co2_per_capita;

    @Column(name = "co2_per_gdp")
    private Double co2_per_gdp;

    @Column(name = "energy_per_capita")
    private Double energy_per_capita;

    @Column(name = "energy_per_gdp")
    private Double energy_per_gdp;

    @Column(name = "methane")
    private Double methane;

    @Column(name = "methane_per_capita")
    private Double methane_per_capita;

    @Column(name = "nitrous_oxide")
    private Double nitrous_oxide;

    @Column(name = "nitrous_oxide_per_capita")
    private Double nitrous_oxide_per_capita;

    @Column(name = "share_of_temperature_change_from_ghg")
    private Double share_of_temperature_change_from_ghg;

    @Column(name = "temperature_change_from_ch4")
    private Double temperature_change_from_ch4;

    @Column(name = "temperature_change_from_co2")
    private Double temperature_change_from_co2;

    @Column(name = "temperature_change_from_ghg")
    private Double temperature_change_from_ghg;

    @Column(name = "temperature_change_from_n2o")
    private Double temperature_change_from_n2o;

    @Column(name = "total_ghg")
    private Double total_ghg;

    public Data() {

    }

    public Data(Long year, Long population, Double gdp, Double co2, Double co2_per_capita, Double co2_per_gdp, Double energy_per_capita, Double energy_per_gdp, Double methane, Double methane_per_capita, Double nitrous_oxide, Double nitrous_oxide_per_capita, Double share_of_temperature_change_from_ghg, Double temperature_change_from_ch4, Double temperature_change_from_co2, Double temperature_change_from_ghg, Double temperature_change_from_n2o, Double total_ghg) {
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

    public Long getId() {
        return id;
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

    public Double getCo2() {
        return co2;
    }

    public void setCo2(Double co2) {
        this.co2 = co2;
    }

    public Double getCo2_per_capita() {
        return co2_per_capita;
    }

    public void setCo2_per_capita(Double co2_per_capita) {
        this.co2_per_capita = co2_per_capita;
    }

    public Double getCo2_per_gdp() {
        return co2_per_gdp;
    }

    public void setCo2_per_gdp(Double co2_per_gdp) {
        this.co2_per_gdp = co2_per_gdp;
    }

    public Double getEnergy_per_capita() {
        return energy_per_capita;
    }

    public void setEnergy_per_capita(Double energy_per_capita) {
        this.energy_per_capita = energy_per_capita;
    }

    public Double getEnergy_per_gdp() {
        return energy_per_gdp;
    }

    public void setEnergy_per_gdp(Double energy_per_gdp) {
        this.energy_per_gdp = energy_per_gdp;
    }

    public Double getMethane() {
        return methane;
    }

    public void setMethane(Double methane) {
        this.methane = methane;
    }

    public Double getMethane_per_capita() {
        return methane_per_capita;
    }

    public void setMethane_per_capita(Double methane_per_capita) {
        this.methane_per_capita = methane_per_capita;
    }

    public Double getNitrous_oxide() {
        return nitrous_oxide;
    }

    public void setNitrous_oxide(Double nitrous_oxide) {
        this.nitrous_oxide = nitrous_oxide;
    }

    public Double getNitrous_oxide_per_capita() {
        return nitrous_oxide_per_capita;
    }

    public void setNitrous_oxide_per_capita(Double nitrous_oxide_per_capita) {
        this.nitrous_oxide_per_capita = nitrous_oxide_per_capita;
    }

    public Double getShare_of_temperature_change_from_ghg() {
        return share_of_temperature_change_from_ghg;
    }

    public void setShare_of_temperature_change_from_ghg(Double share_of_temperature_change_from_ghg) {
        this.share_of_temperature_change_from_ghg = share_of_temperature_change_from_ghg;
    }

    public Double getTemperature_change_from_ch4() {
        return temperature_change_from_ch4;
    }

    public void setTemperature_change_from_ch4(Double temperature_change_from_ch4) {
        this.temperature_change_from_ch4 = temperature_change_from_ch4;
    }

    public Double getTemperature_change_from_co2() {
        return temperature_change_from_co2;
    }

    public void setTemperature_change_from_co2(Double temperature_change_from_co2) {
        this.temperature_change_from_co2 = temperature_change_from_co2;
    }

    public Double getTemperature_change_from_ghg() {
        return temperature_change_from_ghg;
    }

    public void setTemperature_change_from_ghg(Double temperature_change_from_ghg) {
        this.temperature_change_from_ghg = temperature_change_from_ghg;
    }

    public Double getTemperature_change_from_n2o() {
        return temperature_change_from_n2o;
    }

    public void setTemperature_change_from_n2o(Double temperature_change_from_n2o) {
        this.temperature_change_from_n2o = temperature_change_from_n2o;
    }

    public Double getTotal_ghg() {
        return total_ghg;
    }

    public void setTotal_ghg(Double total_ghg) {
        this.total_ghg = total_ghg;
    }
}
