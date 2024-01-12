package com.aritoncosmin.ProiectSpringJava.dtos;


public class TemperatureChangeCard {

    private final Double share_of_temperature_change_from_ghg;
    private final Double temperature_change_from_ch4;

    private final Double temperature_change_from_co2;

    private final Double temperature_change_from_ghg;

    private final Double temperature_change_from_n2o;

    public TemperatureChangeCard(Double share_of_temperature_change_from_ghg, Double temperature_change_from_ch4, Double temperature_change_from_co2, Double temperature_change_from_ghg, Double temperature_change_from_n2o) {
        this.share_of_temperature_change_from_ghg = share_of_temperature_change_from_ghg;
        this.temperature_change_from_ch4 = temperature_change_from_ch4;
        this.temperature_change_from_co2 = temperature_change_from_co2;
        this.temperature_change_from_ghg = temperature_change_from_ghg;
        this.temperature_change_from_n2o = temperature_change_from_n2o;
    }

    public Double getShare_of_temperature_change_from_ghg() {
        return share_of_temperature_change_from_ghg;
    }

    public Double getTemperature_change_from_ch4() {
        return temperature_change_from_ch4;
    }

    public Double getTemperature_change_from_co2() {
        return temperature_change_from_co2;
    }

    public Double getTemperature_change_from_ghg() {
        return temperature_change_from_ghg;
    }

    public Double getTemperature_change_from_n2o() {
        return temperature_change_from_n2o;
    }
}
