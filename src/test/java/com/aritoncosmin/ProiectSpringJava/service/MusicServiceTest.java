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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MusicServiceTest {

    @InjectMocks
    MusicService musicService;

    @Mock
    SongRepository songRepository;

    @Mock
    PlaylistRepository playlistRepository;

    @Mock
    ManagementService managementService;

    @Mock
    DriverRepository driverRepository;


    @Test
    void findPlaylistByIdHappyFlow(){
        Playlist playlist = new Playlist();
        playlist.setId(1);

        when(playlistRepository.findPlaylistById(playlist.getId())).thenReturn(playlist);

        Playlist result = musicService.findPlaylistById(playlist.getId());

        assertEquals(playlist.getId(), result.getId());

    }

    @Test
    void findPlaylistByIdSadFlow(){
        Playlist playlist = new Playlist();
        playlist.setId(1);

        when(playlistRepository.findPlaylistById(playlist.getId())).thenReturn(null);

        Exception exception = assertThrows(NotFoundException.class,
                () -> musicService.findPlaylistById(playlist.getId()));

        assertEquals("Playlist with id " + playlist.getId() + " not found", exception.getMessage());
    }

    @Test
    void findAllPlaylists(){
        Playlist p1 = new Playlist();
        p1.setId(1);
        Playlist p2 = new Playlist();
        p2.setId(2);

        List<Playlist> playlistList = new ArrayList<>();
        playlistList.add(p1);
        playlistList.add(p2);

        when(playlistRepository.findAll()).thenReturn(playlistList);

        List<Playlist> result = musicService.findAllPlaylists();

        assertEquals(playlistList.get(0).getId(), result.get(0).getId());
        assertEquals(playlistList.get(1).getId(), result.get(1).getId());
    }

    @Test
    void findDriverPlaylists(){
        Playlist p1 = new Playlist();
        Playlist p2 = new Playlist();

        p1.setId(1);
        p2.setId(2);

        List<Playlist> playlists = new ArrayList<>();
        playlists.add(p1);
        playlists.add(p2);

        Driver driver = new Driver();
        driver.setId(1);
        driver.setPlaylists(playlists);

        when(managementService.findDriverById(driver.getId())).thenReturn(driver);

        List<Playlist> result = musicService.findDriverPlaylists(driver.getId());

        assertEquals(playlists.get(0).getId(), result.get(0).getId());
        assertEquals(playlists.get(1).getId(), result.get(1).getId());

    }

    @Test
    void modifyDriverPlaylists(){
        Playlist p1 = new Playlist();
        Playlist p2 = new Playlist();

        p1.setId(1);
        p2.setId(2);

        List<Playlist> playlists = new ArrayList<>();
        playlists.add(p1);
        playlists.add(p2);

        Driver driver = new Driver();
        driver.setId(1);
        driver.setPlaylists(playlists);

        Driver dbDriver = new Driver();
        dbDriver.setId(1);

        when(managementService.findDriverById(driver.getId())).thenReturn(dbDriver);

        Driver result = musicService.modifyDriverPlaylists(driver);

        assertEquals(driver.getId(), result.getId());
        assertEquals(driver.getPlaylists().get(0).getId(), result.getPlaylists().get(0).getId());
        assertEquals(driver.getPlaylists().get(1).getId(), result.getPlaylists().get(1).getId());

    }

    @Test
    void savePlaylistHappyFlow(){
        Playlist playlist = new Playlist();
        playlist.setId(1);

        when(playlistRepository.save(playlist)).thenReturn(playlist);

        Playlist result = musicService.savePlaylist(playlist);

        assertEquals(playlist.getId(), result.getId());
    }

    @Test
    void savePlaylistSadFlow(){
        Song song = new Song();
        Playlist playlist = new Playlist();
        playlist.setId(1);
        playlist.getSongList().add(song);
        playlist.getSongList().add(song);

        Exception exception = assertThrows(BadRequest.class,
                () -> musicService.savePlaylist(playlist));

        assertEquals("Given song list contains duplicates. Remove the duplicates and try again.", exception.getMessage());
    }

    @Test
    void modifyPlaylist(){
        Song song = new Song();
        song.setId(1);
        List<Song> songList = new ArrayList<>();
        songList.add(song);

        Playlist playlist = new Playlist();
        playlist.setId(1);
        playlist.setName("a");
        playlist.setSongList(songList);

        Playlist anotherPlaylist = new Playlist();
        anotherPlaylist.setId(1);

        when(playlistRepository.findPlaylistById(playlist.getId())).thenReturn(anotherPlaylist);
        when(playlistRepository.save(anotherPlaylist)).thenReturn(anotherPlaylist);

        Playlist result = musicService.modifyPlaylist(playlist);

        assertEquals(playlist.getName(), result.getName());
        assertEquals(playlist.getId(), result.getId());
        assertEquals(playlist.getSongList().get(0).getId(), result.getSongList().get(0).getId());
    }

    @Test
    void deletePlayListByIdHappyFlow(){
        Playlist playlist = new Playlist();
        playlist.setId(1);

        Driver driver = new Driver();
        driver.setId(1);

        driver.getPlaylists().add(playlist);
        List<Driver> drivers = new ArrayList<>();
        drivers.add(driver);

        when(playlistRepository.findPlaylistById(playlist.getId())).thenReturn(playlist);
        when(driverRepository.findDriversByPlaylistsContaining(playlist)).thenReturn(drivers);
        when(managementService.saveDriver(driver)).thenReturn(driver);
        when(playlistRepository.deletePlaylistById(playlist.getId())).thenReturn(1);

        Playlist result = musicService.deletePlayListById(playlist.getId());

        assertEquals(playlist.getId(), result.getId());
    }

    @Test
    void deletePlaylistByIdSadFlow(){
        Playlist playlist = new Playlist();
        playlist.setId(1);

        Driver driver = new Driver();
        driver.setId(1);

        driver.getPlaylists().add(playlist);
        List<Driver> drivers = new ArrayList<>();
        drivers.add(driver);

        when(playlistRepository.findPlaylistById(playlist.getId())).thenReturn(playlist);
        when(driverRepository.findDriversByPlaylistsContaining(playlist)).thenReturn(drivers);
        when(managementService.saveDriver(driver)).thenReturn(driver);
        when(playlistRepository.deletePlaylistById(playlist.getId())).thenReturn(0);

        Exception exception = assertThrows(InternalServerError.class,
                () -> musicService.deletePlayListById(playlist.getId()));

        assertEquals("Deleted count <= 0, playlist with id " + playlist.getId() + " still exists", exception.getMessage());
    }

    @Test
    void findSongByIdHappyFlow(){
        Song song = new Song();
        song.setId(1);

        when(songRepository.findSongById(song.getId())).thenReturn(song);

        Song result = musicService.findSongById(song.getId());

        assertEquals(song.getId(), result.getId());
    }

    @Test
    void findSongByIdSadFlow(){
        Song song = new Song();
        song.setId(1);

        when(songRepository.findSongById(song.getId())).thenReturn(null);

        Exception exception = assertThrows(NotFoundException.class,
        () -> musicService.findSongById(song.getId()));

        assertEquals("Song with id " + song.getId() + " not found", exception.getMessage());
    }

    @Test
    void findSongByNameHappyFlow(){
        Song song = new Song();
        song.setId(1);
        song.setName("a");

        when(songRepository.findSongByName(song.getName())).thenReturn(song);

        Song result = musicService.findSongByName(song.getName());

        assertEquals(song.getId(), result.getId());
        assertEquals(song.getName(), result.getName());

    }

    @Test
    void findSongByNameSadFlow(){
        Song song = new Song();
        song.setId(1);
        song.setName("a");

        when(songRepository.findSongByName(song.getName())).thenReturn(null);

        Exception exception = assertThrows(NotFoundException.class,
                () -> musicService.findSongByName(song.getName()));

        assertEquals("Song with name " + song.getName() + " not found", exception.getMessage());
    }

    @Test
    void saveSong(){
        Song song = new Song();
        song.setId(1);

        when(songRepository.save(song)).thenReturn(song);

        Song result = musicService.saveSong(song);

        assertEquals(song.getId(), result.getId());
    }

    @Test
    void modifySong(){
        Song song = new Song();
        song.setId(1);
        song.setName("a");
        song.setDuration(1);

        Song dbSong = new Song();
        dbSong.setId(1);

        when(songRepository.findSongById(song.getId())).thenReturn(dbSong);
        when(songRepository.save(dbSong)).thenReturn(dbSong);

        Song result = musicService.modifySong(song);

        assertEquals(song.getId(), result.getId());
        assertEquals(song.getDuration(), result.getDuration());
        assertEquals(song.getName(), result.getName());
    }

    @Test
    void deleteSongByIdHappyFlow(){
        Song song = new Song();
        song.setId(1);

        Playlist playlist = new Playlist();
        playlist.setId(1);
        playlist.getSongList().add(song);
        List<Playlist> playlists = new ArrayList<>();
        playlists.add(playlist);
        when(songRepository.findSongById(song.getId())).thenReturn(song);
        when(playlistRepository.findPlaylistsBySongListContaining(song)).thenReturn(playlists);
        when(playlistRepository.save(playlist)).thenReturn(playlist);
        when(songRepository.deleteSongById(song.getId())).thenReturn(1);

        Song result = musicService.deleteSongById(song.getId());

        assertEquals(song.getId(), result.getId());
    }

    @Test
    void deleteSongByIdSadFlow(){
        Song song = new Song();
        song.setId(1);

        Playlist playlist = new Playlist();
        playlist.setId(1);
        playlist.getSongList().add(song);
        List<Playlist> playlists = new ArrayList<>();
        playlists.add(playlist);
        when(songRepository.findSongById(song.getId())).thenReturn(song);
        when(playlistRepository.findPlaylistsBySongListContaining(song)).thenReturn(playlists);
        when(playlistRepository.save(playlist)).thenReturn(playlist);
        when(songRepository.deleteSongById(song.getId())).thenReturn(0);

        Exception exception = assertThrows(InternalServerError.class,
                () -> musicService.deleteSongById(song.getId()));

        assertEquals("Deleted count <= 0, song with id " + song.getId() + " still exists", exception.getMessage());
    }
}
