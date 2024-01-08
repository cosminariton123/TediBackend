package com.aritoncosmin.ProiectSpringJava.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Drivers")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "driver_first_name")
    private String firstName;

    @Column(name = "driver_last_name")
    private String lastName;

    @Column(name = "driver_age")
    private Integer age;

    @OneToOne
    @JoinColumn(name = "truck_id")
    private Truck truck;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable
    private List<Playlist> playlists = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer driverId) {
        this.id = driverId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String driverFirstName) {
        this.firstName = driverFirstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String driverLastName) {
        this.lastName = driverLastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer driverAge) {
        this.age = driverAge;
    }

    public Truck getTruck() {
        return truck;
    }

    public void setTruck(Truck truck) {
        this.truck = truck;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }
}
