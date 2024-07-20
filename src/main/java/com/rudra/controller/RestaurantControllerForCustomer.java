package com.rudra.controller;

import com.rudra.model.Restaurant;
import com.rudra.model.User;
import com.rudra.restaurantDto.RestaurantDto;
import com.rudra.service.RestaurantService;
import com.rudra.service.UserService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantControllerForCustomer {
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private UserService userService;

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> searchRestaurant(@RequestParam String keyword,
                                                             @RequestHeader("Authorization") String jwt
                                                             ) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
       List<Restaurant> restaurantList=  restaurantService.searchRestaurant(keyword);
       return  new ResponseEntity<>(restaurantList, HttpStatus.OK);
    }

    @GetMapping("/getAllRestaurant")
    public ResponseEntity<List<Restaurant>> getAllRestaurant( ) throws Exception {
//        User user=userService.findUserByJwtToken(jwt);
        List<Restaurant> restaurantList=restaurantService.getAllRestaurant();
        return  new ResponseEntity<>(restaurantList,HttpStatus.OK);
    }

//    @GetMapping("/userId/{id}")
//    public  ResponseEntity<Restaurant> getRestaurantByUserId(@RequestHeader("Authorization") String jwt,@PathVariable Integer id) throws Exception {
//        User user=userService.findUserByJwtToken(jwt);
//        Restaurant restaurantList=restaurantService.getRestaurantByUserId(id);
//        return new ResponseEntity<>(restaurantList,HttpStatus.OK);
//    }
    @GetMapping("/userId")
    public  ResponseEntity<Restaurant> getRestaurantByUserId(@RequestHeader("Authorization") String jwt) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        Restaurant restaurantList=restaurantService.getRestaurantByUserId(user.getId());
        return new ResponseEntity<>(restaurantList,HttpStatus.OK);
    }

    @GetMapping("/restaurantId/{id}")
    public  ResponseEntity<Restaurant> getRestaurantById(@RequestHeader("Authorization") String jwt,@PathVariable Integer id) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        Restaurant restaurantList=restaurantService.findRestaurantById(id);
        return new ResponseEntity<>(restaurantList,HttpStatus.OK);
    }

    @PutMapping("/{id}/addToFavorities")
    public ResponseEntity<RestaurantDto> addFavorite(@PathVariable Integer id,
                                                     @RequestHeader("Authorization") String jwt) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        RestaurantDto restaurantDto=restaurantService.addToFavorites(id,user);
        return  new ResponseEntity<>(restaurantDto,HttpStatus.OK);

    }


}
