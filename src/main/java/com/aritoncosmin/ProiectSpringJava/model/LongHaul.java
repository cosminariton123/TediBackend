package com.aritoncosmin.ProiectSpringJava.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Long_Hauls")
public class LongHaul {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "starting_address")
    private String startingAddress;

    @Column(name = "destination_address")
    private String destinationAddress;

    @ManyToOne
    @JoinColumn(name = "truck_id")
    private Truck truck;

    @ManyToMany
    @JoinTable
    private List<Hotel> hotelList = new ArrayList<>();

    public List<Hotel> getHotelList() {
        return hotelList;
    }

    public void setHotelList(List<Hotel> hotelList) {
        this.hotelList = hotelList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer longHaulId) {
        this.id = longHaulId;
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

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }
}
