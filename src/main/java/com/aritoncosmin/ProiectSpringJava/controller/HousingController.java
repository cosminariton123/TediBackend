package com.aritoncosmin.ProiectSpringJava.controller;

import com.aritoncosmin.ProiectSpringJava.dtos.*;
import com.aritoncosmin.ProiectSpringJava.mappers.HousingMapper;
import com.aritoncosmin.ProiectSpringJava.model.Hotel;
import com.aritoncosmin.ProiectSpringJava.model.Menu;
import com.aritoncosmin.ProiectSpringJava.model.Restaurant;
import com.aritoncosmin.ProiectSpringJava.service.HousingService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@Validated
@RequestMapping("/housing")
public class HousingController {

    private final HousingService housingService;
    private final HousingMapper housingMapper;

    public HousingController(HousingService housingService, HousingMapper housingMapper){
        this.housingService = housingService;
        this.housingMapper = housingMapper;
    }

    @GetMapping("/hotel/{id}")
    public ResponseEntity<Hotel> findHotelById(@PathVariable Integer id){
        Hotel hotel = housingService.findHotelById(id);
        return ResponseEntity.ok().body(hotel);
    }

    @GetMapping("/hotel/driver/{id}")
    public ResponseEntity<List<Hotel>> findHotelOnDriverRoute(@PathVariable Integer id){
        List<Hotel> hotels = housingService.findHotelsOnDriverRoute(id);
        return ResponseEntity.ok().body(hotels);
    }

    @PostMapping("/hotel")
    public ResponseEntity<Hotel> saveNewHotel(@RequestBody @Valid HotelCreateDTO hotelCreateDTO){
        Hotel hotel = housingMapper.HotelCreateDTOToHotel(hotelCreateDTO);
        Hotel savedHotel = housingService.saveHotel(hotel);
        return ResponseEntity.created(URI.create("/housing/hotel/" + savedHotel.getId())).body(savedHotel);
    }

    @PutMapping("/hotel")
    public ResponseEntity<Hotel> modifyHotel(@RequestBody @Valid HotelModifyDTO hotelModifyDTO){
        Hotel hotel = housingMapper.HotelModifyDTOToHotel(hotelModifyDTO);
        Hotel savedHotel = housingService.modifyHotel(hotel);
        return ResponseEntity.ok().body(savedHotel);
    }

    @DeleteMapping("/hotel/{id}")
    public ResponseEntity<Hotel> deleteHotel(@PathVariable Integer id){
        Hotel deletedHotel = housingService.deleteHotelById(id);
        return ResponseEntity.ok().body(deletedHotel);
    }

    @GetMapping("/restaurant/{id}")
    public ResponseEntity<Restaurant> findRestaurantById(@PathVariable Integer id){
        Restaurant restaurant = housingService.findRestaurantById(id);
        return ResponseEntity.ok().body(restaurant);
    }

    @PostMapping("/restaurant")
    public ResponseEntity<Restaurant> saveNewRestaurant(@RequestBody @Valid RestaurantCreateDTO restaurantCreateDTO){
        Restaurant restaurant = housingMapper.RestaurantCreateDTOTOHotel(restaurantCreateDTO);
        Restaurant savedRestaurant = housingService.saveRestaurant(restaurant);
        return ResponseEntity.created(URI.create("/housing/restaurant/" + savedRestaurant.getId())).body(savedRestaurant);
    }

    @PutMapping("/restaurant")
    public ResponseEntity<Restaurant> modifyRestaurant(@RequestBody @Valid RestaurantModifyDTO restaurantModifyDTO){
        Restaurant restaurant = housingMapper.RestaurantModifyDTOToRestaurant(restaurantModifyDTO);
        Restaurant savedRestaurant = housingService.modifyRestaurant(restaurant);
        return ResponseEntity.ok().body(savedRestaurant);
    }

    @DeleteMapping("/restaurant/{id}")
    public ResponseEntity<Restaurant> deleteRestaurant(@PathVariable Integer id){
        Restaurant deletedRestaurant = housingService.deleteRestaurantById(id);
        return ResponseEntity.ok().body(deletedRestaurant);
    }

    @GetMapping("/menu/{id}")
    public ResponseEntity<Menu> findMenuById(@PathVariable Integer id){
        Menu menu = housingService.findMenuById(id);
        return ResponseEntity.ok().body(menu);
    }

    @PostMapping("/menu")
    public ResponseEntity<Menu> saveNewMenu(@RequestBody @Valid MenuCreateDTO menuCreateDTO){
        Menu menu = housingMapper.MenuCreateDTOToMenu(menuCreateDTO);
        Menu savedMenu = housingService.saveMenu(menu);
        return ResponseEntity.created(URI.create("/housing/menu/" + savedMenu.getId())).body(savedMenu);
    }

    @PutMapping("/menu")
    public ResponseEntity<Menu> modifyMenu(@RequestBody @Valid MenuModifyDTO menuModifyDTO){
        Menu menu = housingMapper.MenuModifyDTOToMenu(menuModifyDTO);
        Menu modifiedMenu = housingService.modifyMenu(menu);
        return ResponseEntity.ok().body(modifiedMenu);
    }

    @DeleteMapping("/menu/{id}")
    public ResponseEntity<Menu> deleteMenu(@PathVariable Integer id){
        Menu deletedMenu = housingService.deleteMenuById(id);
        return ResponseEntity.ok().body(deletedMenu);
    }
}
