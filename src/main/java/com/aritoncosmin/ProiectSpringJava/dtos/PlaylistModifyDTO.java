package com.aritoncosmin.ProiectSpringJava.dtos;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Component
public class PlaylistModifyDTO {

    @NotNull(message = "id must not be null")
    private Integer id;

    @NotNull(message = "name must not be null")
    @NotBlank(message = "name must not be blank")
    private String name;

    private List<Integer> songListIds = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
