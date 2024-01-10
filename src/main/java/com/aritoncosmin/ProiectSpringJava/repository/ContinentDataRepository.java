package com.aritoncosmin.ProiectSpringJava.repository;

import com.aritoncosmin.ProiectSpringJava.model.Continent;
import com.aritoncosmin.ProiectSpringJava.model.ContinentData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContinentDataRepository extends JpaRepository<ContinentData, Integer> {

    ContinentData findContinentDataByYearAndContinent(Integer year, Continent continent);

}
