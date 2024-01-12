package com.aritoncosmin.ProiectSpringJava.repository;

import com.aritoncosmin.ProiectSpringJava.model.Continent;
import com.aritoncosmin.ProiectSpringJava.model.ContinentData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContinentDataRepository extends JpaRepository<ContinentData, Long> {

    ContinentData findContinentDataByYearAndContinent(Long year, Continent continent);

}
