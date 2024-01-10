package com.aritoncosmin.ProiectSpringJava.model;

import javax.persistence.*;

@Entity
@Table(name = "Continents")
public class Continent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    public Continent(String name) {
        this.name = name;
    }

    public Continent() {

    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
