package com.aritoncosmin.ProiectSpringJava.model;

import javax.persistence.*;

@Entity
@Table(name = "Countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "iso_code", unique = true)
    private String isoCode;

    @Column(name = "name", unique = true)
    private String name;

    public Country(String isoCode, String name) {
        this.isoCode = isoCode;
        this.name = name;
    }

    protected Country() {

    }

    public String getIsoCode() {
        return isoCode;
    }

    public String getName() {
        return name;
    }

}
