package com.aritoncosmin.ProiectSpringJava.repository;

import com.aritoncosmin.ProiectSpringJava.model.Truck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TruckRepository extends JpaRepository<Truck, Integer> {

    Truck findTruckById(Integer id);

    Integer deleteTruckById(Integer id);
}
