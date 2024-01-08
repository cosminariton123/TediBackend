package com.aritoncosmin.ProiectSpringJava.mappers;

import com.aritoncosmin.ProiectSpringJava.dtos.*;
import com.aritoncosmin.ProiectSpringJava.model.Hotel;
import com.aritoncosmin.ProiectSpringJava.model.Menu;
import com.aritoncosmin.ProiectSpringJava.model.Restaurant;
import com.aritoncosmin.ProiectSpringJava.service.HousingService;
import org.springframework.stereotype.Component;

@Component
public class HousingMapper {
    HousingService housingService;

    public HousingMapper(HousingService housingService){
        this.housingService = housingService;
    }

    public Hotel HotelCreateDTOToHotel(HotelCreateDTO hotelCreateDTO){
        Hotel hotel = new Hotel();
        hotel.setName(hotelCreateDTO.getName());
        hotel.setNumber_of_stars(hotelCreateDTO.getNumber_of_stars());

        Restaurant restaurant = housingService.findRestaurantById(hotelCreateDTO.getRestaurantId());
        hotel.setRestaurant(restaurant);
        return hotel;
    }

    public Hotel HotelModifyDTOToHotel(HotelModifyDTO hotelModifyDTO){
        Hotel hotel = new Hotel();
        hotel.setName(hotelModifyDTO.getName());
        hotel.setNumber_of_stars(hotelModifyDTO.getNumber_of_stars());

        Restaurant restaurant = housingService.findRestaurantById(hotelModifyDTO.getRestaurantId());
        hotel.setRestaurant(restaurant);
        return hotel;
    }

    public Restaurant RestaurantCreateDTOTOHotel(RestaurantCreateDTO restaurantCreateDTO){
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantCreateDTO.getName());
        restaurant.setRating(restaurantCreateDTO.getRating());

        Menu menu = housingService.findMenuById(restaurantCreateDTO.getMenuId());
        restaurant.setMenu(menu);
        return restaurant;
    }

    public Restaurant RestaurantModifyDTOToRestaurant(RestaurantModifyDTO restaurantModifyDTO){
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantModifyDTO.getName());
        restaurant.setRating(restaurantModifyDTO.getRating());

        Menu menu = housingService.findMenuById(restaurantModifyDTO.getMenuId());
        restaurant.setMenu(menu);
        return restaurant;
    }

    public Menu MenuCreateDTOToMenu(MenuCreateDTO menuCreateDTO){
        Menu menu = new Menu();
        menu.setSoupWeightInGrams(menuCreateDTO.getSoupWeightInGrams());
        menu.setFriesWeightInGrams(menuCreateDTO.getFriesWeightInGrams());
        menu.setSteakWeightInGrams(menuCreateDTO.getSteakWeightInGrams());
        menu.setBurgerWeightInGrams(menuCreateDTO.getBurgerWeightInGrams());
        menu.setBoiledEggsWeightInGrams(menuCreateDTO.getBoiledEggsWeightInGrams());
        menu.setFriedEggsWeightInGrams(menuCreateDTO.getFriedEggsWeightInGrams());

        return menu;
    }

    public Menu MenuModifyDTOToMenu(MenuModifyDTO menuModifyDTO){
        Menu menu = new Menu();
        menu.setSoupWeightInGrams(menuModifyDTO.getSoupWeightInGrams());
        menu.setFriesWeightInGrams(menuModifyDTO.getFriesWeightInGrams());
        menu.setSteakWeightInGrams(menuModifyDTO.getSteakWeightInGrams());
        menu.setBurgerWeightInGrams(menuModifyDTO.getBurgerWeightInGrams());
        menu.setBoiledEggsWeightInGrams(menuModifyDTO.getBoiledEggsWeightInGrams());
        menu.setFriedEggsWeightInGrams(menuModifyDTO.getFriedEggsWeightInGrams());

        return menu;
    }

}
