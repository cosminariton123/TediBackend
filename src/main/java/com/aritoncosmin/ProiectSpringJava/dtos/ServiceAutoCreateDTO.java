package com.aritoncosmin.ProiectSpringJava.dtos;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class ServiceAutoCreateDTO {

    @NotNull(message = "name must not be null")
    @NotBlank(message = "name must not be blank")
    private String name;

    @NotNull(message = "nrOfWorkers must not be null")
    @Min(value = 0, message = "nrOfWorkers must be greater or equal to 0")
    private Integer nrOfWorkers;

    private Integer inventoryId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNrOfWorkers() {
        return nrOfWorkers;
    }

    public void setNrOfWorkers(Integer nrOfWorkers) {
        this.nrOfWorkers = nrOfWorkers;
    }

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }
}
