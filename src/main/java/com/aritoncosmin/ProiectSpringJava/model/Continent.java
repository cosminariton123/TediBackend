package com.aritoncosmin.ProiectSpringJava.model;

import javax.persistence.*;

@Entity
@Table(name = "Continents")
public class Continent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    public Continent(String name) {
        this.name = name;
    }

    public Continent() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
