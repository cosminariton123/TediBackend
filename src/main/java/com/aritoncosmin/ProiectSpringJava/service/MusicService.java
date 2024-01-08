package com.aritoncosmin.ProiectSpringJava.service;


import com.aritoncosmin.ProiectSpringJava.exceptions.BadRequest;
import com.aritoncosmin.ProiectSpringJava.exceptions.InternalServerError;
import com.aritoncosmin.ProiectSpringJava.exceptions.NotFoundException;
import com.aritoncosmin.ProiectSpringJava.model.Driver;
import com.aritoncosmin.ProiectSpringJava.model.Playlist;
import com.aritoncosmin.ProiectSpringJava.model.Song;
import com.aritoncosmin.ProiectSpringJava.repository.DriverRepository;
import com.aritoncosmin.ProiectSpringJava.repository.PlaylistRepository;
import com.aritoncosmin.ProiectSpringJava.repository.SongRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MusicService {
    SongRepository songRepository;
    PlaylistRepository playlistRepository;
    ManagementService managementService;
    DriverRepository driverRepository;

    public MusicService(SongRepository songRepository, PlaylistRepository playlistRepository, ManagementService managementService, DriverRepository driverRepository){
        this.songRepository = songRepository;
        this.playlistRepository = playlistRepository;
        this.managementService = managementService;
        this.driverRepository = driverRepository;
    }

    public Playlist findPlaylistById(Integer id){
        Playlist foundPlaylist = playlistRepository.findPlaylistById(id);

        if (foundPlaylist == null)
            throw new NotFoundException("Playlist with id " + id + " not found");

        return foundPlaylist;
    }

    public List<Playlist> findAllPlaylists(){
        return playlistRepository.findAll();
    }

    public List<Playlist> findDriverPlaylists(Integer id){
        Driver driver = managementService.findDriverById(id);
        return driver.getPlaylists();
    }

    public Driver modifyDriverPlaylists(Driver driver){
        Driver foundDriver = managementService.findDriverById(driver.getId());
        foundDriver.setPlaylists(driver.getPlaylists());
        managementService.saveDriver(foundDriver);
        return foundDriver;
    }

    public Playlist savePlaylist(Playlist playlist){
        Set<Song> set = new HashSet<Song>(playlist.getSongList());
        if(set.size() < playlist.getSongList().size())
            throw new BadRequest("Given song list contains duplicates. Remove the duplicates and try again.");
        return playlistRepository.save(playlist);
    }

    public Playlist modifyPlaylist(Playlist playlist){
        Playlist foundPlaylist = findPlaylistById(playlist.getId());
        foundPlaylist.setName(playlist.getName());
        foundPlaylist.setSongList(playlist.getSongList());
        return  savePlaylist(foundPlaylist);
    }

    @Transactional
    public Playlist deletePlayListById(Integer id){
        Playlist foundPlaylist = findPlaylistById(id);
        
        List<Driver> drivers = driverRepository.findDriversByPlaylistsContaining(foundPlaylist);

        for (Driver driver:
             drivers) {
            driver.setPlaylists(driver.getPlaylists().stream()
                    .filter(p -> p.getId() != foundPlaylist.getId())
                    .collect(Collectors.toList()));
            managementService.saveDriver(driver);
        }

        Integer deletedCount = playlistRepository.deletePlaylistById(foundPlaylist.getId());

        if (deletedCount > 0)
            return  foundPlaylist;

        throw new InternalServerError("Deleted count <= 0, playlist with id " + foundPlaylist.getId() + " still exists");
    }

    public Song findSongById(Integer id){
        Song foundSong = songRepository.findSongById(id);

        if (foundSong == null)
            throw new NotFoundException("Song with id " + id + " not found");

        return foundSong;
    }

    public Song findSongByName(String name){
        Song foundSong = songRepository.findSongByName(name);

        if (foundSong == null)
            throw new NotFoundException("Song with name " + name + " not found");

        return foundSong;
    }

    public Song saveSong(Song song){
        return songRepository.save(song);
    }

    public Song modifySong(Song song){
        Song foundSong = findSongById(song.getId());
        foundSong.setName(song.getName());
        foundSong.setDuration(song.getDuration());
        return saveSong(foundSong);
    }

    @Transactional
    public Song deleteSongById(Integer id){
        Song foundSong = findSongById(id);

        List<Playlist> playlists = playlistRepository.findPlaylistsBySongListContaining(foundSong);

        for (Playlist playlist:
             playlists) {
             playlist.setSongList(playlist.getSongList().stream()
                    .filter(s -> s.getId() != foundSong.getId())
                    .collect(Collectors.toList()));
             savePlaylist(playlist);
        }

        Integer deleteCount = songRepository.deleteSongById(foundSong.getId());

        if(deleteCount > 0)
            return foundSong;

        throw new InternalServerError("Deleted count <= 0, song with id " + foundSong.getId() + " still exists");
    }

}
