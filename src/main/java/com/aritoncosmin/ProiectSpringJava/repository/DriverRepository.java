package com.aritoncosmin.ProiectSpringJava.repository;

import com.aritoncosmin.ProiectSpringJava.model.Driver;
import com.aritoncosmin.ProiectSpringJava.model.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DriverRepository extends JpaRepository<Driver, Integer> {

    Driver findDriverByTruckId(Integer id);

    Driver findDriverById(Integer id);

    Integer deleteDriverById(Integer id);

    List<Driver> findDriversByPlaylistsContaining(Playlist playlist);
}
