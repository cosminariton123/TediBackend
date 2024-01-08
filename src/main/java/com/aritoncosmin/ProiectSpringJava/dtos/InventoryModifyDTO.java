package com.aritoncosmin.ProiectSpringJava.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class InventoryModifyDTO {

    @NotNull(message = "id must not be null")
    private Integer id;

    @NotNull(message = "radiator quantity must not be null")
    @Min(value = 0, message = "radiator quantity must be greater or equal to 0")
    private Integer radiatorQuantity;

    @NotNull(message = "engine quantity must not be null")
    @Min(value = 0, message = "engine quantity must be greater or equal to 0")
    private Integer engineQuantity;

    @NotNull(message = "fan quantity must not be null")
    @Min(value = 0, message = "fan quantity must be greater or equal to 0")
    private Integer fanQuantity;

    @NotNull(message = "windshield quantity must not be null")
    @Min(value = 0, message = "windshield quantity must be greater or equal to 0")
    private Integer windshieldQuantity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRadiatorQuantity() {
        return radiatorQuantity;
    }

    public void setRadiatorQuantity(Integer radiatorQuantity) {
        this.radiatorQuantity = radiatorQuantity;
    }

    public Integer getEngineQuantity() {
        return engineQuantity;
    }

    public void setEngineQuantity(Integer engineQuantity) {
        this.engineQuantity = engineQuantity;
    }

    public Integer getFanQuantity() {
        return fanQuantity;
    }

    public void setFanQuantity(Integer fanQuantity) {
        this.fanQuantity = fanQuantity;
    }

    public Integer getWindshieldQuantity() {
        return windshieldQuantity;
    }

    public void setWindshieldQuantity(Integer windshieldQuantity) {
        this.windshieldQuantity = windshieldQuantity;
    }
}
