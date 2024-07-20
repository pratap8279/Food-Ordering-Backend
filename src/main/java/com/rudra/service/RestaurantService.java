package com.rudra.service;

import com.rudra.Request.RestaurantRequest;
import com.rudra.model.Restaurant;
import com.rudra.model.User;
import com.rudra.restaurantDto.RestaurantDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Service;

import java.util.List;


public interface RestaurantService {
    public Restaurant createRestaurant(RestaurantRequest request, User user);
    public Restaurant updateRestaurant(Integer restaurantId, RestaurantRequest updated) throws  Exception ;
    public  void deleteRestaurant(Integer restaurantId) throws Exception;
    public List<Restaurant> getAllRestaurant();
    public List<Restaurant> searchRestaurant(String keyword);
    public Restaurant findRestaurantById(Integer id) throws  Exception;
    public Restaurant getRestaurantByUserId(Integer id) throws  Exception;
    public RestaurantDto addToFavorites(Integer restaurantId,User user) throws  Exception;
    public Restaurant updatedRestaurantStatus(Integer id) throws  Exception;

}
