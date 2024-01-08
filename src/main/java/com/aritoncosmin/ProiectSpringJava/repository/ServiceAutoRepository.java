package com.aritoncosmin.ProiectSpringJava.repository;

import com.aritoncosmin.ProiectSpringJava.model.ServiceAuto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceAutoRepository extends JpaRepository<ServiceAuto, Integer> {

    ServiceAuto findServiceAutoById(Integer id);

    Integer deleteServiceAutoById(Integer id);

    ServiceAuto findServiceAutoByInventoryId(Integer id);

}
