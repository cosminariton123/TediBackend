package com.aritoncosmin.ProiectSpringJava.service;

import com.aritoncosmin.ProiectSpringJava.exceptions.BadRequest;
import com.aritoncosmin.ProiectSpringJava.exceptions.InternalServerError;
import com.aritoncosmin.ProiectSpringJava.exceptions.NotFoundException;
import com.aritoncosmin.ProiectSpringJava.model.*;
import com.aritoncosmin.ProiectSpringJava.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HousingService {

    HotelRepository hotelRepository;
    RestaurantRepository restaurantRepository;
    LongHaulRepository longHaulRepository;
    MenuRepository menuRepository;
    ManagementService managementService;
    TruckRepository truckRepository;
    DriverRepository driverRepository;

    public HousingService(HotelRepository hotelRepository, RestaurantRepository restaurantRepository, LongHaulRepository longHaulRepository, MenuRepository menuRepository, ManagementService managementService, TruckRepository truckRepository, DriverRepository driverRepository){
        this.hotelRepository = hotelRepository;
        this.restaurantRepository = restaurantRepository;
        this.longHaulRepository = longHaulRepository;
        this.menuRepository = menuRepository;
        this.managementService = managementService;
        this. truckRepository = truckRepository;
        this. driverRepository = driverRepository;
    }

    public Hotel findHotelById(Integer id){
        Hotel hotel = hotelRepository.findHotelById(id);

        if (hotel == null)
            throw new NotFoundException("Hotel with id " + id + " not found");
        return hotel;
    }

    public Hotel saveHotel(Hotel hotel){
        Hotel hotelThatAlreadyHasAsignedGivenRestaurant = null;
        if (hotel.getRestaurant() != null)
            hotelThatAlreadyHasAsignedGivenRestaurant = hotelRepository.findHotelByRestaurantId(hotel.getRestaurant().getId());

        if (hotelThatAlreadyHasAsignedGivenRestaurant != null && hotelThatAlreadyHasAsignedGivenRestaurant != hotel)
            throw new BadRequest("Given restaurant is already assigned to the hotel with id " + hotelThatAlreadyHasAsignedGivenRestaurant.getId());

        return hotelRepository.save(hotel);
    }

    public Hotel modifyHotel(Hotel hotel){
        Hotel foundHotel = findHotelById(hotel.getId());
        foundHotel.setName(hotel.getName());
        foundHotel.setNumber_of_stars(hotel.getNumber_of_stars());
        foundHotel.setRestaurant(hotel.getRestaurant());

        return saveHotel(foundHotel);
    }

    @Transactional
    public Hotel deleteHotelById(Integer id){
        Hotel foundHotel = findHotelById(id);

        List<LongHaul> longHauls = longHaulRepository.findLongHaulsByHotelListContaining(foundHotel);

        for (LongHaul longHaul:
             longHauls) {
            longHaul.setHotelList(longHaul.getHotelList().stream()
                    .filter(h -> h.getId() != foundHotel.getId())
                    .collect(Collectors.toList()));
            managementService.saveLongHaul(longHaul);
        }

        Integer deletedCount = hotelRepository.deleteHotelById(foundHotel.getId());

        if(deletedCount > 0)
            return foundHotel;

        throw new InternalServerError("Deleted count <= 0, hotel with id " + foundHotel.getId() + " still exists");
    }

    public Restaurant findRestaurantById(Integer id){
        Restaurant restaurant = restaurantRepository.findRestaurantById(id);

        if(restaurant == null)
            throw new NotFoundException("Restaurant with id " + id + " not found");
        return restaurant;
    }

    public Restaurant saveRestaurant(Restaurant restaurant){
        Restaurant restaurantThatAlreadyHasAsignedGivenMenu = null;
        if (restaurant.getMenu() != null)
            restaurantThatAlreadyHasAsignedGivenMenu = restaurantRepository.findRestaurantByMenuId(restaurant.getMenu().getId());

        if(restaurantThatAlreadyHasAsignedGivenMenu != null && restaurantThatAlreadyHasAsignedGivenMenu != restaurant)
            throw new BadRequest("Given menu is already assigned to the restaurant with id " + restaurantThatAlreadyHasAsignedGivenMenu.getId());
        return restaurantRepository.save(restaurant);
    }

    public Restaurant modifyRestaurant(Restaurant restaurant){
        Restaurant foundRestaurant = findRestaurantById(restaurant.getId());
        foundRestaurant.setName(restaurant.getName());
        foundRestaurant.setRating(restaurant.getRating());
        foundRestaurant.setMenu(restaurant.getMenu());

        return saveRestaurant(foundRestaurant);
    }

    @Transactional
    public Restaurant deleteRestaurantById(Integer id){
        Restaurant restaurant = findRestaurantById(id);

        Hotel hotel = hotelRepository.findHotelByRestaurantId(restaurant.getId());

        if (hotel != null){
            hotel.setRestaurant(null);
            saveHotel(hotel);
        }

        Integer deletedCount = restaurantRepository.deleteRestaurantById(restaurant.getId());

        if (deletedCount > 0)
            return restaurant;

        throw new InternalServerError("Deleted count <=0, restaurant with id " + restaurant.getId() + " still exists");
    }

    public Menu findMenuById(Integer id){
        Menu menu = menuRepository.findMenuById(id);

        if(menu == null)
            throw new NotFoundException("Menu with id " + id + " not found");
        return menu;
    }

    public Menu saveMenu(Menu menu){
        return menuRepository.save(menu);
    }

    public Menu modifyMenu(Menu menu){
        Menu foundMenu = findMenuById(menu.getId());
        foundMenu.setSoupWeightInGrams(menu.getSoupWeightInGrams());
        foundMenu.setFriesWeightInGrams(menu.getFriesWeightInGrams());
        foundMenu.setSteakWeightInGrams(menu.getSteakWeightInGrams());
        foundMenu.setBurgerWeightInGrams(menu.getBurgerWeightInGrams());
        foundMenu.setBoiledEggsWeightInGrams(menu.getBoiledEggsWeightInGrams());
        foundMenu.setFriedEggsWeightInGrams(menu.getFriedEggsWeightInGrams());

        return saveMenu(foundMenu);
    }

    @Transactional
    public Menu deleteMenuById(Integer id){
        Menu menu = findMenuById(id);

        Restaurant restaurant = restaurantRepository.findRestaurantByMenuId(menu.getId());

        if (restaurant != null){
            restaurant.setMenu(null);
            saveRestaurant(restaurant);
        }

        Integer deletedCount = menuRepository.deleteMenuById(menu.getId());

        if(deletedCount > 0)
            return menu;

        throw new InternalServerError("Deleted count <=0, menu with id " + menu.getId() + " still exists");
    }

    public List<Hotel> findHotelsOnDriverRoute(Integer id){
        Driver driver = managementService.findDriverById(id);

        Truck truck = driver.getTruck();

        if (truck == null)
            throw new NotFoundException("Driver with id " + driver.getId() + " has no truck");

        List<LongHaul> longHauls = longHaulRepository.findLongHaulsByTruckId(truck.getId());

        List<Hotel> hotels = new ArrayList<>();

        for (LongHaul longHaul:
             longHauls) {
            for (Hotel hotel:
                 longHaul.getHotelList()) {
                hotels.add(hotel);
            }
        }
        return hotels;
    }

}
