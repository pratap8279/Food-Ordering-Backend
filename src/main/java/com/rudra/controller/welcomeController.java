package com.rudra.controller;

import com.rudra.model.Restaurant;
import com.rudra.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/start")
public class welcomeController {
    @Autowired
    private RestaurantService restaurantService;
    @GetMapping("/getAllRestaurant")
    public ResponseEntity<List<Restaurant>> getAllRestaurant( ) throws Exception {
//        User user=userService.findUserByJwtToken(jwt);
        List<Restaurant> restaurantList=restaurantService.getAllRestaurant();
        return  new ResponseEntity<>(restaurantList, HttpStatus.OK);
    }

}
