package com.aritoncosmin.ProiectSpringJava.repository;

import com.aritoncosmin.ProiectSpringJava.model.Continent;
import com.aritoncosmin.ProiectSpringJava.model.ContinentData;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContinentDataRepository extends JpaRepository<ContinentData, Long> {

    ContinentData findContinentDataByYearAndContinent(Long year, Continent continent);

    List<ContinentData> findAllByYearGreaterThanEqualAndContinent(Long year, Continent continent, Sort by);

}
