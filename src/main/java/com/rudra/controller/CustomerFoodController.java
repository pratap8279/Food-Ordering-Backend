package com.rudra.controller;

import com.rudra.model.Category;
import com.rudra.model.Food;
import com.rudra.model.Restaurant;
import com.rudra.model.User;
import com.rudra.service.FoodServiceImpl;
import com.rudra.service.RestaurantServiceImpl;
import com.rudra.service.UserServiceImpl;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerFoodController {
    @Autowired
    private FoodServiceImpl foodService;
    @Autowired
    private RestaurantServiceImpl restaurantService;
    @Autowired
    UserServiceImpl userService;

    @GetMapping("/search")
    public  ResponseEntity<List<Food>> searchFood(@PathVariable String keyword,
                                                  @RequestHeader("Authorization") String jwt){
       List<Food> foodlist=foodService.searchFood(keyword);
        return new ResponseEntity<>(foodlist,HttpStatus.OK);
    }

    @GetMapping("/{id}/findFoodById")
    public ResponseEntity<Food> findFoodById(@PathVariable Integer id) throws Exception {
        Food food=foodService.findFoodById(id);
       return  new ResponseEntity<>(food,HttpStatus.OK);

    }
    @PostMapping("/{id}/updateFood")
    public ResponseEntity<Food> updateFood(@PathVariable Integer foodId) throws Exception {
    Food food=    foodService.updateAvailibilityStatus(foodId);
    return new ResponseEntity<>(food,HttpStatus.OK);
    }

    @GetMapping("/getRestaurantFood/{restaurantId}")
    public ResponseEntity<List<Food>> getRestaurantFood(@PathVariable Integer restaurantId,
                                                  @RequestParam(required = false) boolean isVegitration,
                                                  @RequestParam(required = false) boolean  isNonveg,
                                                  @RequestParam(required = false) boolean isSeasional,
                                                  @RequestParam String foodCategory,
                                                  @RequestHeader("Authorization") String jwt) throws Exception {
        User user=userService.findUserByJwtToken(jwt);

        Restaurant restaurant=restaurantService.findRestaurantById(restaurantId);

        List<Food> foodList=foodService.getRestaurantFood(restaurantId,foodCategory,isVegitration,isNonveg,isSeasional);
        return new ResponseEntity<>(foodList,HttpStatus.OK);


    }


}
