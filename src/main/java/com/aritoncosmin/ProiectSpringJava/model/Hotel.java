package com.aritoncosmin.ProiectSpringJava.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Hotels")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "number_of_stars")
    private Integer number_of_stars;

    @OneToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;


    public Integer getId() {
        return id;
    }

    public void setId(Integer hotelId) {
        this.id = hotelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber_of_stars() {
        return number_of_stars;
    }

    public void setNumber_of_stars(Integer number_of_stars) {
        this.number_of_stars = number_of_stars;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

}
