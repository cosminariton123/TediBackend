package com.aritoncosmin.ProiectSpringJava.model;

import javax.persistence.*;

@MappedSuperclass
public abstract class Data {

    public static final String yearField = "year";
    public static final String populationField = "population";
    public static final String gdpField = "gdp";
    public static final String co2Field = "co2";
    public static final String co2_per_capitaField = "co2_per_capita";
    public static final String co2_per_gdpField = "co2_per_gdp";
    public static final String energy_per_capitaField = "energy_per_capita";
    public static final String energy_per_gdpField = "energy_per_gdp";
    public static final String methaneField = "methane";
    public static final String methane_per_capitaField = "methane_per_capita";
    public static final String nitrous_oxideField = "nitrous_oxide";
    public static final String nitrous_oxide_per_capitaField = "nitrous_oxide_per_capita";
    public static final String share_of_temperature_change_from_ghgField = "share_of_temperature_change_from_ghg";
    public static final String temperature_change_from_ch4Field = "temperature_change_from_ch4";
    public static final String temperature_change_from_co2Field = "temperature_change_from_co2";
    public static final String temperature_change_from_ghgField = "temperature_change_from_ghg";
    public static final String temperature_change_from_n2oField = "temperature_change_from_n2o";
    public static final String total_ghgField = "total_ghg";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = Data.yearField)
    private Long year;

    @Column(name = Data.populationField)
    private Long population;

    @Column(name = Data.gdpField)
    private Double gdp;

    @Column(name = Data.co2Field)
    private Double co2;

    @Column(name = Data.co2_per_capitaField)
    private Double co2_per_capita;

    @Column(name = Data.co2_per_gdpField)
    private Double co2_per_gdp;

    @Column(name = Data.energy_per_capitaField)
    private Double energy_per_capita;

    @Column(name = Data.energy_per_gdpField)
    private Double energy_per_gdp;

    @Column(name = Data.methaneField)
    private Double methane;

    @Column(name = Data.methane_per_capitaField)
    private Double methane_per_capita;

    @Column(name = Data.nitrous_oxideField)
    private Double nitrous_oxide;

    @Column(name = Data.nitrous_oxide_per_capitaField)
    private Double nitrous_oxide_per_capita;

    @Column(name = Data.share_of_temperature_change_from_ghgField)
    private Double share_of_temperature_change_from_ghg;

    @Column(name = Data.temperature_change_from_ch4Field)
    private Double temperature_change_from_ch4;

    @Column(name = Data.temperature_change_from_co2Field)
    private Double temperature_change_from_co2;

    @Column(name = Data.temperature_change_from_ghgField)
    private Double temperature_change_from_ghg;

    @Column(name = Data.temperature_change_from_n2oField)
    private Double temperature_change_from_n2o;

    @Column(name = Data.total_ghgField)
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
