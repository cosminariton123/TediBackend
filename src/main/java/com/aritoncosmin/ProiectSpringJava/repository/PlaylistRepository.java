package com.aritoncosmin.ProiectSpringJava.repository;

import com.aritoncosmin.ProiectSpringJava.model.Playlist;
import com.aritoncosmin.ProiectSpringJava.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {

    Playlist findPlaylistById(Integer id);

    Integer deletePlaylistById(Integer id);

    List<Playlist> findPlaylistsBySongListContaining(Song song);
}
