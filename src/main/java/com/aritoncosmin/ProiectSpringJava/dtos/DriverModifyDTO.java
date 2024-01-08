package com.aritoncosmin.ProiectSpringJava.dtos;

import javax.validation.constraints.*;
import java.util.List;

public class DriverModifyDTO {

    @NotNull(message = "Id must not be null")
    private Integer id;

    @NotNull(message = "firstName must not be null")
    @NotBlank(message = "firstName must not be blank")
    private String firstName;

    @NotNull(message = "firstName must not be null")
    @NotBlank(message = "firstName must not be blank")
    private String lastName;

    @NotNull(message = "age must not be null")
    @Min(value = 18, message = "age must be greater or equal to 18")
    @Max(value = 150, message = "age must be lower or equal to 150")
    private Integer age;

    private Integer truckId;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getTruckId() {
        return truckId;
    }

    public void setTruckId(Integer truckId) {
        this.truckId = truckId;
    }

}
