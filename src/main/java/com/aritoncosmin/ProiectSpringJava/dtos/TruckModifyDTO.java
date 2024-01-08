package com.aritoncosmin.ProiectSpringJava.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TruckModifyDTO {

    @NotNull(message = "Id must not be null")
    private Integer id;

    @NotNull(message = "brand must not be null")
    @NotBlank(message = "brand must not be blank")
    private String brand;

    @NotNull(message = "km must not be null")
    @Min(value = 0, message = "km must be greater or equal to 0")
    private Integer km;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
