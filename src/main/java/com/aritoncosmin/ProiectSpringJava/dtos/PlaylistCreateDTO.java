package com.aritoncosmin.ProiectSpringJava.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


public class PlaylistCreateDTO {

    @NotNull(message = "name must not be null")
    @NotBlank(message = "name must not be blank")
    private String name;

    private List<Integer> songListIds = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Integer> getSongListIds() {
        return songListIds;
    }

    public void setSongListIds(List<Integer> songListIds) {
        this.songListIds = songListIds;
    }
}
