package com.aritoncosmin.ProiectSpringJava.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TruckCreateDTO {

    @NotNull(message = "brand must not be null")
    @NotBlank(message = "brand must not be blank")
    private String brand;

    @NotNull(message = "km must not be null")
    @NotBlank(message = "km must not be blank")
    private Integer km;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getKm() {
        return km;
    }

    public void setKm(Integer km) {
        this.km = km;
    }
}
