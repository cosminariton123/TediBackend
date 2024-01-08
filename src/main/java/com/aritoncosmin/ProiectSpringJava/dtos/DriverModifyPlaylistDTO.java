package com.aritoncosmin.ProiectSpringJava.dtos;


import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class DriverModifyPlaylistDTO {

    @NotNull(message = "id must be not null")
    private Integer id;

    private List<Integer> playlistIds = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Integer> getPlaylistIds() {
        return playlistIds;
    }

    public void setPlaylistIds(List<Integer> playlistIds) {
        this.playlistIds = playlistIds;
    }
}
