package com.aritoncosmin.ProiectSpringJava.repository;

import com.aritoncosmin.ProiectSpringJava.model.Country;
import com.aritoncosmin.ProiectSpringJava.model.CountryData;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryDataRepository extends JpaRepository<CountryData, Long> {
    CountryData findCountryDataByYearAndCountry(Long year, Country country);

    List<CountryData> findAllByYearGreaterThanEqualAndCountry(Long year, Country country, Sort by);

}
