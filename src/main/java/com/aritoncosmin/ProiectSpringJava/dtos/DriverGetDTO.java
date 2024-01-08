package com.aritoncosmin.ProiectSpringJava.dtos;

import com.aritoncosmin.ProiectSpringJava.model.Truck;

public class DriverGetDTO {

    private Integer id;

    private String firstName;

    private String lastName;

    private Integer age;

    private Truck truck;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }
}
