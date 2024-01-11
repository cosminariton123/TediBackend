package com.aritoncosmin.ProiectSpringJava.util;

import java.util.ArrayList;
import java.util.List;

public class SavedDatabaseCountry {

    private String name;

    private String iso_code;

    private final List<SavedDatabaseData> data = new ArrayList<>();


    public void setName(String name) {
        this.name = name;
    }

    public void setIso_code(String iso_code) {
        this.iso_code = iso_code;
    }

    public String getName() {
        return name;
    }

    public String getIso_code() {
        return iso_code;
    }

    public List<SavedDatabaseData> getData() {
        return data;
    }
}
