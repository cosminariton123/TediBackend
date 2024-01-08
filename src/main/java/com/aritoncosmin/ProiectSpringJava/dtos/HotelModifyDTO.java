package com.aritoncosmin.ProiectSpringJava.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class HotelModifyDTO {

    @NotNull(message = "id must not be null")
    private Integer id;

    @NotNull(message = "name must not be null")
    @NotBlank(message = "name must not be blank")
    private String name;

    @NotNull(message = "number of stars must not be null")
    @Min(value = 2, message = "number of stars must be greater or equal to 2")
    @Max(value = 7, message = "number of stars must be smaller or equal to 7")
    private Integer number_of_stars;

    private Integer restaurantId;

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

    public Integer getNumber_of_stars() {
        return number_of_stars;
    }

    public void setNumber_of_stars(Integer number_of_stars) {
        this.number_of_stars = number_of_stars;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }
}
