package com.aritoncosmin.ProiectSpringJava.model;

import javax.persistence.*;


@Entity
@Table(name = "Trucks")
public class Truck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "brand")
    private String brand;

    @Column(name = "km")
    private Integer km;

    public Integer getId() {
        return id;
    }

    public void setId(Integer truckId) {
        this.id = truckId;
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