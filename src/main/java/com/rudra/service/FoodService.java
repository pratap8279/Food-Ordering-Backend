package com.rudra.service;

import com.rudra.Request.CreateFoodRequest;
import com.rudra.model.Category;
import com.rudra.model.Food;
import com.rudra.model.Restaurant;

import java.util.List;

public interface FoodService {
    public Food createFood(CreateFoodRequest createFoodRequest,Category category, Restaurant restaurant);
    public  void  deleteFood(Integer foodId)throws  Exception;
    public List<Food> getRestaurantFood(Integer restaurantId, String foodCategory, boolean isVegitration,boolean isNonveg,boolean isSeasional) throws Exception;
    public List<Food> searchFood(String keyword) ;
    public  Food findFoodById(Integer foodId)throws  Exception;
    public  Food updateAvailibilityStatus(Integer foodId) throws Exception;
    public List<Food> getAllRestaurantFood(Integer restaurantId) throws Exception;
}
