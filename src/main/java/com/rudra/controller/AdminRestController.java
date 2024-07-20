package com.rudra.controller;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.rudra.Request.RestaurantRequest;
import com.rudra.model.Restaurant;
import com.rudra.model.User;
import com.rudra.repositatory.CartRepositatory;
import com.rudra.response.MesageResponse;
import com.rudra.service.RestaurantService;
import com.rudra.service.RestaurantServiceImpl;
import com.rudra.service.UserService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/restaurant")
public class AdminRestController {
    @Autowired
    private RestaurantServiceImpl restaurantService;
    @Autowired
    private UserService userService;

    @PostMapping("/createRestaurant")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody RestaurantRequest req,
                                                       @RequestHeader("Authorization") String jwt) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        Restaurant restaurant=restaurantService.createRestaurant(req,user);
        return new  ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/updateRestaurant")
    public ResponseEntity<Restaurant> updateRestaurant(@RequestBody RestaurantRequest request,
                                                       @PathVariable Integer id) throws Exception {

        Restaurant restaurant=restaurantService.updateRestaurant(id ,request);
        return  new ResponseEntity<>(restaurant,HttpStatus.OK);
    }
    @PostMapping("/{id}")
    public  ResponseEntity<MesageResponse> deleteRestaurants(@PathVariable Integer id) throws Exception {
        restaurantService.deleteRestaurant(id);
        MesageResponse mesageResponse=new MesageResponse();
        mesageResponse.setMessage("Restaurant deleted Successfully");
        return new ResponseEntity<>( mesageResponse,HttpStatus.OK);
    }
    @PutMapping("/{id}/status")
    public  ResponseEntity<Restaurant>  updateRestaurantStatus(
                                                                   @PathVariable Integer id) throws Exception {
            Restaurant  restaurant= restaurantService.updatedRestaurantStatus(id);

            return  new ResponseEntity<>(restaurant,HttpStatus.OK);
      }
      @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findRestaurantByUserId(@PathVariable Integer id) throws Exception {
        Restaurant restaurant=restaurantService.findRestaurantById(id);
        return  new ResponseEntity<>(restaurant,HttpStatus.OK);
      }
    @GetMapping("/getRestaurantByUserId")
    public ResponseEntity<Restaurant> getRestaurantByUserId(
                                                            @RequestHeader("Authorization") String jwt) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        Restaurant restaurant=restaurantService.getRestaurantByUserId(user.getId());
        return  new ResponseEntity<>(restaurant,HttpStatus.OK);
    }


}
