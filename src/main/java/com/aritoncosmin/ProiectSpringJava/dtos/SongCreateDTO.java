package com.aritoncosmin.ProiectSpringJava.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SongCreateDTO {

    @NotNull(message = "name must not be null")
    @NotBlank(message = "name must not be blank")
    private String name;

    @NotNull(message = "duration must not be null")
    @Min(value = 0, message = "duration must be greater or equal to 0")
    private Integer duration;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
