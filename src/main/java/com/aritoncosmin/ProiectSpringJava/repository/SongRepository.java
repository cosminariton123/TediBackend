package com.aritoncosmin.ProiectSpringJava.repository;

import com.aritoncosmin.ProiectSpringJava.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SongRepository extends JpaRepository<Song, Integer> {

    Song findSongById(Integer id);

    Song findSongByName(String name);

    Integer deleteSongById(Integer id);
}
