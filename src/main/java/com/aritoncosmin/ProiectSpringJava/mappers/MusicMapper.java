package com.aritoncosmin.ProiectSpringJava.mappers;

import com.aritoncosmin.ProiectSpringJava.dtos.*;
import com.aritoncosmin.ProiectSpringJava.model.Driver;
import com.aritoncosmin.ProiectSpringJava.model.Playlist;
import com.aritoncosmin.ProiectSpringJava.model.Song;
import com.aritoncosmin.ProiectSpringJava.service.MusicService;
import org.springframework.stereotype.Component;

@Component
public class MusicMapper {

    MusicService musicService;

    public MusicMapper(MusicService musicService){
        this.musicService = musicService;
    }

    public Playlist PlaylistCreateDTOToPlaylist(PlaylistCreateDTO playlistCreateDTO){
        Playlist playlist = new Playlist();
        playlist.setName(playlistCreateDTO.getName());

        for (Integer songId:
             playlistCreateDTO.getSongListIds()) {
            playlist.getSongList().add(musicService.findSongById(songId));
        }
        return playlist;
    }

    public Playlist PlaylistModifyDTOToPlaylist(PlaylistModifyDTO playlistModifyDTO){
        Playlist playlist = new Playlist();
        playlist.setId(playlistModifyDTO.getId());
        playlist.setName(playlistModifyDTO.getName());

        for (Integer songId:
             playlistModifyDTO.getSongListIds()) {
            playlist.getSongList().add(musicService.findSongById(songId));
        }
        return playlist;
    }

    public Driver DriverModifyPlaylistDTOToDriver(DriverModifyPlaylistDTO driverModifyPlaylistDTO){
        Driver driver = new Driver();
        driver.setId(driverModifyPlaylistDTO.getId());

        for (Integer playlistId:
                driverModifyPlaylistDTO.getPlaylistIds()) {
            driver.getPlaylists().add(musicService.findPlaylistById(playlistId));
        }
        return driver;
    }

    public DriverModifyPlaylistDTO DriverToDriverModifyPlaylist(Driver driver){
        DriverModifyPlaylistDTO driverModifyPlaylistDTO = new DriverModifyPlaylistDTO();
        driverModifyPlaylistDTO.setId(driver.getId());

        for (Playlist playlist:
             driver.getPlaylists()) {
            driverModifyPlaylistDTO.getPlaylistIds().add(playlist.getId());
        }
        return driverModifyPlaylistDTO;
    }

    public Song SongCreateDTOToSong(SongCreateDTO songCreateDTO){
        Song song = new Song();
        song.setName(songCreateDTO.getName());
        song.setDuration(songCreateDTO.getDuration());
        return song;
    }

    public Song SongModifyDTOToSong(SongModifyDTO songModifyDTO){
        Song song = new Song();
        song.setName(songModifyDTO.getName());
        song.setDuration(songModifyDTO.getDuration());
        return song;
    }
}
