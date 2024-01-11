package com.aritoncosmin.ProiectSpringJava.repository;

import com.aritoncosmin.ProiectSpringJava.model.Continent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContinentRepository extends JpaRepository<Continent, Long> {

    Continent findContinentByName(String name);

}
