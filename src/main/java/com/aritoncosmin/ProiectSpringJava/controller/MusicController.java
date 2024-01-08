package com.aritoncosmin.ProiectSpringJava.controller;

import com.aritoncosmin.ProiectSpringJava.dtos.*;
import com.aritoncosmin.ProiectSpringJava.mappers.MusicMapper;
import com.aritoncosmin.ProiectSpringJava.model.Driver;
import com.aritoncosmin.ProiectSpringJava.model.Playlist;
import com.aritoncosmin.ProiectSpringJava.model.Song;
import com.aritoncosmin.ProiectSpringJava.service.MusicService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@Validated
@RequestMapping("/music")
public class MusicController {

    private final MusicService musicService;
    private final MusicMapper musicMapper;

    public MusicController(MusicService musicService, MusicMapper musicMapper){
        this.musicService = musicService;
        this.musicMapper = musicMapper;
    }

    @GetMapping("/playlist/{id}")
    public ResponseEntity<Playlist> findPlaylistById(@PathVariable Integer id){
        Playlist foundPlaylist = musicService.findPlaylistById(id);
        return ResponseEntity.ok().body(foundPlaylist);
    }

    @GetMapping("/playlist")
    public ResponseEntity<List<Playlist>> findPlaylists(){
        List<Playlist> foundPlaylists = musicService.findAllPlaylists();
        return ResponseEntity.ok().body(foundPlaylists);
    }

    @GetMapping("/playlist/driver/{id}")
    public ResponseEntity<List<Playlist>> findDriverPlaylist(@PathVariable Integer id){
        List<Playlist> foundPlaylists = musicService.findDriverPlaylists(id);
        return ResponseEntity.ok().body(foundPlaylists);
    }

    @PutMapping("/playlist/driver")
    public ResponseEntity<DriverModifyPlaylistDTO> modifyDriverPlaylist(@RequestBody @Valid DriverModifyPlaylistDTO driverModifyPlaylistDTO){
        Driver driver = musicMapper.DriverModifyPlaylistDTOToDriver(driverModifyPlaylistDTO);
        Driver savedDriver = musicService.modifyDriverPlaylists(driver);
        DriverModifyPlaylistDTO savedDriverModifyPlaylistDTO = musicMapper.DriverToDriverModifyPlaylist(savedDriver);
        return ResponseEntity.ok().body(savedDriverModifyPlaylistDTO);
    }

    @PostMapping("/playlist")
    public ResponseEntity<Playlist> saveNewPlaylist(@RequestBody @Valid PlaylistCreateDTO playlistCreateDTO){
        Playlist playlist = musicMapper.PlaylistCreateDTOToPlaylist(playlistCreateDTO);
        Playlist savedPlaylist = musicService.savePlaylist(playlist);
        return ResponseEntity.created(URI.create("/music/playlist/" + savedPlaylist.getId())).body(savedPlaylist);
    }

    @PutMapping("/playlist")
    public ResponseEntity<Playlist> modifyPlaylist(@RequestBody @Valid PlaylistModifyDTO playlistModifyDTO){
        Playlist playlist = musicMapper.PlaylistModifyDTOToPlaylist(playlistModifyDTO);
        Playlist modifiedPlaylist = musicService.modifyPlaylist(playlist);
        return ResponseEntity.ok().body(modifiedPlaylist);
    }

    @DeleteMapping("/playlist/{id}")
    public ResponseEntity<Playlist> deletePlaylistById(@PathVariable Integer id){
        Playlist deletedPlaylist = musicService.deletePlayListById(id);
        return ResponseEntity.ok().body(deletedPlaylist);
    }

    @GetMapping("/song/{id}")
    public ResponseEntity<Song> findSongById(@PathVariable Integer id){
        Song foundSong = musicService.findSongById(id);
        return ResponseEntity.ok().body(foundSong);
    }

    @GetMapping("/song/by_name/{name}")
    public ResponseEntity<Song> findSongByName(@PathVariable String name){
        Song foundSong = musicService.findSongByName(name);
        return ResponseEntity.ok().body(foundSong);
    }

    @PostMapping("/song")
    public ResponseEntity<Song> saveNewSong(@RequestBody @Valid SongCreateDTO songCreateDTO){
        Song song = musicMapper.SongCreateDTOToSong(songCreateDTO);
        Song savedSong = musicService.saveSong(song);
        return ResponseEntity.created(URI.create("/music/song/" + savedSong.getId())).body(savedSong);
    }

    @PutMapping("/song")
    public ResponseEntity<Song> modifySong(@RequestBody @Valid SongModifyDTO songModifyDTO){
        Song song = musicMapper.SongModifyDTOToSong(songModifyDTO);
        Song modifiedSong = musicService.modifySong(song);
        return ResponseEntity.ok().body(modifiedSong);
    }

    @DeleteMapping("/song/{id}")
    public ResponseEntity<Song> deleteSongById(@PathVariable Integer id){
        Song deletedSong = musicService.deleteSongById(id);
        return ResponseEntity.ok().body(deletedSong);
    }
}
