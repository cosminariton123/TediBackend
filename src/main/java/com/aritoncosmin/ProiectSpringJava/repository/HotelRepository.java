package com.aritoncosmin.ProiectSpringJava.repository;

import com.aritoncosmin.ProiectSpringJava.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {

    Hotel findHotelById(Integer id);

    Integer deleteHotelById(Integer id);

    Hotel findHotelByRestaurantId(Integer id);

}
