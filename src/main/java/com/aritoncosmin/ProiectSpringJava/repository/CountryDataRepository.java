package com.aritoncosmin.ProiectSpringJava.repository;

import com.aritoncosmin.ProiectSpringJava.model.Country;
import com.aritoncosmin.ProiectSpringJava.model.CountryData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryDataRepository extends JpaRepository<CountryData, Integer> {
    CountryData findCountryDataByYearAndCountry(Integer year, Country country);

}
