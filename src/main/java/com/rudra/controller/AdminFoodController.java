package com.rudra.controller;

import com.rudra.Request.CreateFoodRequest;
import com.rudra.model.Category;
import com.rudra.model.Food;
import com.rudra.model.Restaurant;
import com.rudra.model.User;
import com.rudra.response.MesageResponse;
import com.rudra.service.FoodServiceImpl;
import com.rudra.service.RestaurantServiceImpl;
import com.rudra.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {
    @Autowired
    private FoodServiceImpl foodService;
    @Autowired
    private RestaurantServiceImpl restaurantService;
    @Autowired UserServiceImpl userService;

    @PostMapping("/createFood")
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodRequest createFoodRequest, @RequestHeader("Authorization") String jwt) throws Exception {
       User user=userService.findUserByJwtToken(jwt);
//        Restaurant restaurant=restaurantService.findRestaurantById(createFoodRequest.getRestaurantId());
        Restaurant restaurant=restaurantService.getRestaurantByUserId(user.getId());
        Food food=foodService.createFood(createFoodRequest,createFoodRequest.getCategory(),restaurant);
        return new ResponseEntity<>(food,HttpStatus.OK);


    }
    @DeleteMapping("/deleteFood/{foodId}")
    public ResponseEntity<MesageResponse> deleteFood(@RequestHeader("Authorization") String jwt,
                                                     @PathVariable Integer foodId
                                                     ) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        MesageResponse mesageResponse=new MesageResponse();
        mesageResponse.setMessage("Food Delete Successfuly");
        foodService.deleteFood(foodId);
        return new ResponseEntity<>(mesageResponse,HttpStatus.OK);

    }
    @PutMapping("/{id}/update")
    public ResponseEntity<Food>updateFoodAvaliablity(@PathVariable Integer id) throws Exception {
        Food food=foodService.updateAvailibilityStatus(id);
        return new ResponseEntity<>(food, HttpStatus.OK);
    }

    @GetMapping("/getAllFoodFromRestaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getAllFoodFromRestaurant(@RequestHeader("Authorization") String jwt,
                                                               @PathVariable Integer restaurantId){
        List<Food> foodList=foodService.getAllRestaurantFood(restaurantId);
        return  new ResponseEntity<>(foodList,HttpStatus.OK);

    }

}
