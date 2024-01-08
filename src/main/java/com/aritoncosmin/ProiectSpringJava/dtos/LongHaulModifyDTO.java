package com.aritoncosmin.ProiectSpringJava.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class LongHaulModifyDTO {

    @NotNull(message = "Id must not be null")
    private Integer id;

    @NotNull(message = "startingAddress must not be null")
    @NotBlank(message = "startingAddress must not be blank")
    private String startingAddress;

    @NotNull(message = "destinationAddress must not be null")
    @NotBlank(message = "destinationAddress must not be blank")
    private String destinationAddress;

    private Integer truckId;

    private List<Integer> hotelListIds = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStartingAddress() {
        return startingAddress;
    }

    public void setStartingAddress(String startingAddress) {
        this.startingAddress = startingAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public Integer getTruckId() {
        return truckId;
    }

    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
    }

    public List<Integer> getHotelListIds() {
        return hotelListIds;
    }

    public void setHotelListIds(List<Integer> hotelListIds) {
        this.hotelListIds = hotelListIds;
    }
}
