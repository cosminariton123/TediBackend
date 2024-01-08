package com.aritoncosmin.ProiectSpringJava.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MenuCreateDTO {

    @NotNull(message = "soup weight in grams must not be null")
    @Min(value = 0, message = "soup weight in grams must be greater or equal to 0")
    private Integer soupWeightInGrams;

    @NotNull(message = "fries weight in grams must be not null")
    @Min(value = 0, message = "fries weight in grams must be greater or equal to 0")
    private Integer friesWeightInGrams;

    @NotNull(message = "steak weight in grams must be not null")
    @Min(value = 0, message = "streak weight in grams must be greater or equal to 0")
    private Integer steakWeightInGrams;

    @NotNull(message = "burger weight in grams must not be null")
    @Min(value = 0, message = "burger weight in grams must be greater or equal to 0")
    private Integer burgerWeightInGrams;

    @NotNull(message = "boiled eggs weight in grams must not be null")
    @Min(value = 0, message = "boiled eggs weight in grams ")
    private Integer boiledEggsWeightInGrams;

    @NotNull(message = "fried eggs weight in grams must not be null")
    @Min(value = 0, message = "fried eggs weight in grams must be greater or equal to 0")
    private Integer friedEggsWeightInGrams;

    public Integer getSoupWeightInGrams() {
        return soupWeightInGrams;
    }

    public void setSoupWeightInGrams(Integer soupWeightInGrams) {
        this.soupWeightInGrams = soupWeightInGrams;
    }

    public Integer getFriesWeightInGrams() {
        return friesWeightInGrams;
    }

    public void setFriesWeightInGrams(Integer friesWeightInGrams) {
        this.friesWeightInGrams = friesWeightInGrams;
    }

    public Integer getSteakWeightInGrams() {
        return steakWeightInGrams;
    }

    public void setSteakWeightInGrams(Integer steakWeightInGrams) {
        this.steakWeightInGrams = steakWeightInGrams;
    }

    public Integer getBurgerWeightInGrams() {
        return burgerWeightInGrams;
    }

    public void setBurgerWeightInGrams(Integer burgerWeightInGrams) {
        this.burgerWeightInGrams = burgerWeightInGrams;
    }

    public Integer getBoiledEggsWeightInGrams() {
        return boiledEggsWeightInGrams;
    }

    public void setBoiledEggsWeightInGrams(Integer boiledEggsWeightInGrams) {
        this.boiledEggsWeightInGrams = boiledEggsWeightInGrams;
    }

    public Integer getFriedEggsWeightInGrams() {
        return friedEggsWeightInGrams;
    }

    public void setFriedEggsWeightInGrams(Integer friedEggsWeightInGrams) {
        this.friedEggsWeightInGrams = friedEggsWeightInGrams;
    }
}
