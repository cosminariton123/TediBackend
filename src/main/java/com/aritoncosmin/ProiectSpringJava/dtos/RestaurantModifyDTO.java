package com.aritoncosmin.ProiectSpringJava.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RestaurantModifyDTO {

    @NotNull(message = "id must not be null")
    private Integer id;

    @NotNull(message = "name must not be null")
    @NotBlank(message = "name must not be blank")
    private String name;

    @NotNull(message = "rating must not be null")
    @Min(value = 1, message = "rating must be greater or equal to 1")
    @Max(value = 3, message = "rating must be smaller or equal to 3")
    private Integer rating;

    private Integer menuId;

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

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }
}
