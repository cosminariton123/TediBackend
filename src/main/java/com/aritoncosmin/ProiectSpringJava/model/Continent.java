package com.aritoncosmin.ProiectSpringJava.model;

import javax.persistence.*;

@Entity
@Table(name = Continent.tableName)
public class Continent {

    public static final String tableName = "continents";
    public static final String nameField = "name";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = Continent.nameField, unique = true)
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
