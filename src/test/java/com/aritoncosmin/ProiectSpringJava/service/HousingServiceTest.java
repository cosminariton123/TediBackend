package com.aritoncosmin.ProiectSpringJava.service;

import com.aritoncosmin.ProiectSpringJava.exceptions.BadRequest;
import com.aritoncosmin.ProiectSpringJava.exceptions.InternalServerError;
import com.aritoncosmin.ProiectSpringJava.exceptions.NotFoundException;
import com.aritoncosmin.ProiectSpringJava.model.*;
import com.aritoncosmin.ProiectSpringJava.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HousingServiceTest {

    @InjectMocks
    HousingService housingService;

    @Mock
    HotelRepository hotelRepository;

    @Mock
    RestaurantRepository restaurantRepository;

    @Mock
    LongHaulRepository longHaulRepository;

    @Mock
    MenuRepository menuRepository;

    @Mock
    ManagementService managementService;

    @Mock
    TruckRepository truckRepository;

    @Mock
    DriverRepository driverRepository;

    @Test
    void findHotelByIdHappyFlow(){
        Hotel hotel = new Hotel();
        hotel.setId(1);

        when(hotelRepository.findHotelById(hotel.getId())).thenReturn(hotel);

        Hotel result = housingService.findHotelById(hotel.getId());

        assertEquals(hotel.getId(), result.getId());
    }

    @Test
    void findHotelByIdSadFlow(){
        Hotel hotel = new Hotel();
        hotel.setId(1);

        when(hotelRepository.findHotelById(hotel.getId())).thenReturn(null);

        Exception exception = assertThrows(NotFoundException.class,
                () -> housingService.findHotelById(hotel.getId()));

        assertEquals("Hotel with id " + hotel.getId() + " not found", exception.getMessage());
    }

    @Test
    void saveHotelHappyFlow(){
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1);

        Hotel hotel = new Hotel();
        hotel.setId(1);
        hotel.setRestaurant(restaurant);

        when(hotelRepository.findHotelByRestaurantId(hotel.getRestaurant().getId())).thenReturn(hotel);
        when(hotelRepository.save(hotel)).thenReturn(hotel);

        Hotel result = housingService.saveHotel(hotel);

        assertEquals(hotel.getId(), result.getId());
    }

    @Test
    void saveHotelSadFlow(){
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1);

        Hotel hotel = new Hotel();
        hotel.setId(1);
        hotel.setRestaurant(restaurant);

        Hotel anotherHotel = new Hotel();
        anotherHotel.setId(2);
        anotherHotel.setRestaurant(restaurant);

        when(hotelRepository.findHotelByRestaurantId(hotel.getRestaurant().getId())).thenReturn(anotherHotel);

        Exception exception = assertThrows(BadRequest.class,
                () -> housingService.saveHotel(hotel));

        assertEquals("Given restaurant is already assigned to the hotel with id " + anotherHotel.getId(), exception.getMessage());
    }

    @Test
    void modifyHotel(){
        Restaurant restaurant = new Restaurant();
        Hotel hotel = new Hotel();
        hotel.setId(1);
        hotel.setName("a");
        hotel.setNumber_of_stars(1);
        hotel.setRestaurant(restaurant);

        Hotel anotherHotel = new Hotel();
        anotherHotel.setId(1);

        when(hotelRepository.findHotelById(hotel.getId())).thenReturn(anotherHotel);
        when(hotelRepository.save(anotherHotel)).thenReturn(anotherHotel);

        Hotel result = housingService.modifyHotel(hotel);

        assertEquals(hotel.getId(), result.getId());
        assertEquals(hotel.getName(), result.getName());
        assertEquals(hotel.getRestaurant(), result.getRestaurant());
        assertEquals(hotel.getNumber_of_stars(), result.getNumber_of_stars());

    }

    @Test
    void deleteHotelByIdHappyFlow(){
        Hotel hotel = new Hotel();
        hotel.setId(1);

        LongHaul longHaul = new LongHaul();
        longHaul.getHotelList().add(hotel);
        longHaul.setId(1);
        List<LongHaul> longHauls = new ArrayList<>();
        longHauls.add(longHaul);

        when(hotelRepository.findHotelById(hotel.getId())).thenReturn(hotel);
        when(longHaulRepository.findLongHaulsByHotelListContaining(hotel)).thenReturn(longHauls);
        when(hotelRepository.deleteHotelById(hotel.getId())).thenReturn(1);

        Hotel result = housingService.deleteHotelById(hotel.getId());

        assertEquals(hotel.getId(), result.getId());
    }

    @Test
    void deleteHotelByIdSadFlow(){
        Hotel hotel = new Hotel();
        hotel.setId(1);

        LongHaul longHaul = new LongHaul();
        longHaul.getHotelList().add(hotel);
        longHaul.setId(1);
        List<LongHaul> longHauls = new ArrayList<>();
        longHauls.add(longHaul);

        when(hotelRepository.findHotelById(hotel.getId())).thenReturn(hotel);
        when(longHaulRepository.findLongHaulsByHotelListContaining(hotel)).thenReturn(longHauls);
        when(hotelRepository.deleteHotelById(hotel.getId())).thenReturn(0);

        Exception exception = assertThrows(InternalServerError.class,
                () -> housingService.deleteHotelById(hotel.getId()));

        assertEquals("Deleted count <= 0, hotel with id " + hotel.getId() + " still exists", exception.getMessage());

    }

    @Test
    void findRestaurantByIdHappyFlow(){
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1);

        when(restaurantRepository.findRestaurantById(restaurant.getId())).thenReturn(restaurant);

        Restaurant result = housingService.findRestaurantById(restaurant.getId());

        assertEquals(restaurant.getId(), result.getId());
    }

    @Test
    void findRestaurantByIdSadFlow(){
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1);

        when(restaurantRepository.findRestaurantById(restaurant.getId())).thenReturn(null);

        Exception exception = assertThrows(NotFoundException.class,
                () -> housingService.findRestaurantById(restaurant.getId()));

        assertEquals("Restaurant with id " + restaurant.getId() + " not found", exception.getMessage());
    }

    @Test
    void saveRestaurantHappyFlow(){
        Menu menu = new Menu();
        menu.setId(1);

        Restaurant restaurant = new Restaurant();
        restaurant.setId(1);
        restaurant.setMenu(menu);

        Restaurant anotherRestaurant = new Restaurant();
        anotherRestaurant.setId(2);

        when(restaurantRepository.findRestaurantByMenuId(restaurant.getMenu().getId())).thenReturn(restaurant);
        when(restaurantRepository.save(restaurant)).thenReturn(restaurant);

        Restaurant result = housingService.saveRestaurant(restaurant);

        assertEquals(restaurant.getId(), result.getId());

    }

    @Test
    void saveRestaurantSadFlow(){
        Menu menu = new Menu();
        menu.setId(1);

        Restaurant restaurant = new Restaurant();
        restaurant.setId(1);
        restaurant.setMenu(menu);

        Restaurant anotherRestaurant = new Restaurant();
        anotherRestaurant.setId(2);

        when(restaurantRepository.findRestaurantByMenuId(restaurant.getMenu().getId())).thenReturn(anotherRestaurant);

        Exception exception = assertThrows(BadRequest.class,
                () -> housingService.saveRestaurant(restaurant));

        assertEquals("Given menu is already assigned to the restaurant with id " + anotherRestaurant.getId(), exception.getMessage());
    }

    @Test
    void modifyRestaurantHappyFlow(){
        Menu menu = new Menu();
        menu.setId(1);

        Restaurant restaurant = new Restaurant();
        restaurant.setId(1);
        restaurant.setName("a");
        restaurant.setRating(1);
        restaurant.setMenu(menu);

        when(restaurantRepository.findRestaurantById(restaurant.getId())).thenReturn(restaurant);
        when(restaurantRepository.save(restaurant)).thenReturn(restaurant);

        Restaurant result = housingService.modifyRestaurant(restaurant);

        assertEquals(restaurant.getId(), result.getId());
    }

    @Test
    void deleteRestaurantByIdHappyFlow(){
        Hotel hotel = new Hotel();
        hotel.setId(1);

        Restaurant restaurant = new Restaurant();
        restaurant.setId(1);

        hotel.setRestaurant(restaurant);

        when(restaurantRepository.findRestaurantById(restaurant.getId())).thenReturn(restaurant);
        when(hotelRepository.findHotelByRestaurantId(restaurant.getId())).thenReturn(hotel);
        when(restaurantRepository.deleteRestaurantById(restaurant.getId())).thenReturn(1);

        Restaurant result = housingService.deleteRestaurantById(restaurant.getId());

        assertEquals(restaurant.getId(), result.getId());
    }

    @Test
    void deleteRestaurantByIdSadFlow(){
        Hotel hotel = new Hotel();
        hotel.setId(1);

        Restaurant restaurant = new Restaurant();
        restaurant.setId(1);

        hotel.setRestaurant(restaurant);

        when(restaurantRepository.findRestaurantById(restaurant.getId())).thenReturn(restaurant);
        when(hotelRepository.findHotelByRestaurantId(restaurant.getId())).thenReturn(hotel);
        when(restaurantRepository.deleteRestaurantById(restaurant.getId())).thenReturn(0);

        Exception exception = assertThrows(InternalServerError.class,
                () -> housingService.deleteRestaurantById(restaurant.getId()));

        assertEquals("Deleted count <=0, restaurant with id " + restaurant.getId() + " still exists", exception.getMessage());
    }

    @Test
    void findMenuByidHappyFlow(){
        Menu menu = new Menu();
        menu.setId(1);

        when(menuRepository.findMenuById(menu.getId())).thenReturn(menu);

        Menu result = housingService.findMenuById(menu.getId());

        assertEquals(menu.getId(), result.getId());
    }

    @Test
    void findMenuByIdSadFlow(){
        Menu menu = new Menu();
        menu.setId(1);

        when(menuRepository.findMenuById(menu.getId())).thenReturn(null);

        Exception exception = assertThrows(NotFoundException.class,
                () -> housingService.findMenuById(menu.getId()));

        assertEquals("Menu with id " + menu.getId() + " not found", exception.getMessage());
    }


    @Test
    void saveMenu(){
        Menu menu = new Menu();
        menu.setId(1);

        when(menuRepository.save(menu)).thenReturn(menu);

        Menu result = housingService.saveMenu(menu);

        assertEquals(menu.getId(), result.getId());
    }

    @Test
    void modifyMenu(){
        Menu menu = new Menu();
        menu.setId(1);
        menu.setFriedEggsWeightInGrams(1);
        menu.setBoiledEggsWeightInGrams(1);
        menu.setBurgerWeightInGrams(1);
        menu.setSteakWeightInGrams(1);
        menu.setSoupWeightInGrams(1);
        menu.setFriesWeightInGrams(1);

        Menu dbMenu = new Menu();
        dbMenu.setId(1);

        when(menuRepository.findMenuById(menu.getId())).thenReturn(dbMenu);
        when(menuRepository.save(dbMenu)).thenReturn(dbMenu);

        Menu result = housingService.modifyMenu(menu);

        assertEquals(menu.getId(), result.getId());
        assertEquals(menu.getBoiledEggsWeightInGrams(), result.getBoiledEggsWeightInGrams());
        assertEquals(menu.getBurgerWeightInGrams(), result.getBurgerWeightInGrams());
        assertEquals(menu.getFriedEggsWeightInGrams(), result.getFriedEggsWeightInGrams());
        assertEquals(menu.getFriesWeightInGrams(), result.getFriesWeightInGrams());
        assertEquals(menu.getSteakWeightInGrams(), result.getSteakWeightInGrams());
    }

    @Test
    void deleteMenuByIdHappyFlow(){
        Menu menu = new Menu();
        menu.setId(1);

        Restaurant restaurant = new Restaurant();
        restaurant.setId(1);
        restaurant.setMenu(menu);

        when(menuRepository.findMenuById(menu.getId())).thenReturn(menu);
        when(restaurantRepository.findRestaurantByMenuId(menu.getId())).thenReturn(restaurant);
        when(menuRepository.deleteMenuById(menu.getId())).thenReturn(1);

        Menu result = housingService.deleteMenuById(menu.getId());

        assertEquals(menu.getId(), result.getId());
    }

    @Test
    void deleteMenuByIdSadFlow(){
        Menu menu = new Menu();
        menu.setId(1);

        Restaurant restaurant = new Restaurant();
        restaurant.setId(1);
        restaurant.setMenu(menu);

        when(menuRepository.findMenuById(menu.getId())).thenReturn(menu);
        when(restaurantRepository.findRestaurantByMenuId(menu.getId())).thenReturn(restaurant);
        when(menuRepository.deleteMenuById(menu.getId())).thenReturn(0);

        Exception exception = assertThrows(InternalServerError.class,
                () -> housingService.deleteMenuById(menu.getId()));

        assertEquals("Deleted count <=0, menu with id " + menu.getId() + " still exists", exception.getMessage());
    }

    @Test
    void findHotelsOnDriverRouteHappyFlow(){
        Truck truck = new Truck();
        truck.setId(1);

        Driver driver = new Driver();
        driver.setId(1);
        driver.setTruck(truck);

        Hotel hotel = new Hotel();
        List<Hotel> hotels = new ArrayList<>();
        hotels.add(hotel);

        LongHaul longHaul = new LongHaul();
        longHaul.setId(1);
        longHaul.setTruck(truck);
        longHaul.getHotelList().add(hotel);

        List<LongHaul> longHauls = new ArrayList<>();
        longHauls.add(longHaul);

        when(managementService.findDriverById(driver.getId())).thenReturn(driver);
        when(longHaulRepository.findLongHaulsByTruckId(driver.getTruck().getId())).thenReturn(longHauls);

        List<Hotel> result = housingService.findHotelsOnDriverRoute(driver.getId());

        assertEquals(hotels.get(0).getId(), result.get(0).getId());
    }

    @Test
    void findHotelsOnDriverRouteSadFlow(){
        Driver driver = new Driver();
        driver.setId(1);

        when(managementService.findDriverById(driver.getId())).thenReturn(driver);

        Exception exception = assertThrows(NotFoundException.class,
                () -> housingService.findHotelsOnDriverRoute(driver.getId()));

        assertEquals("Driver with id " + driver.getId() + " has no truck", exception.getMessage());
    }

}
