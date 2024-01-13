package com.aritoncosmin.ProiectSpringJava.model;

import javax.persistence.*;

@Entity
@Table(name = Country.tableName)
public class Country {

    public static final String tableName = "countries";
    public static final String isoCodeField = "iso_code";
    public static final String nameField = "name";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = Country.isoCodeField, unique = true)
    private String isoCode;

    @Column(name = Country.nameField, unique = true)
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
