package com.aritoncosmin.ProiectSpringJava.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SavedDatabaseContinent {
    private String name;

    private final List<SavedDatabaseData> data = new ArrayList<>();

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<SavedDatabaseData> getData() {
        return data;
    }
}
