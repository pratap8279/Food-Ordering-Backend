package com.rudra.service;

import com.rudra.Request.CreateFoodRequest;
import com.rudra.model.Category;
import com.rudra.model.Food;
import com.rudra.model.Restaurant;
import com.rudra.repositatory.CategoryRepositatory;
import com.rudra.repositatory.FoodRespositatory;
import com.rudra.repositatory.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements  FoodService{
    @Autowired
  private   FoodRespositatory foodRespositatory;
    @Autowired
   RestaurantServiceImpl restaurantService;

    @Autowired
    CategoryRepositatory categoryRepositatory;


    @Override
    public Food createFood(CreateFoodRequest createFoodRequest, Category category, Restaurant restaurant) {

//        Category category1= new Category();
//        category1.setId(category1.getId());
//        category1.setName(category1.getName());
//        categoryRepositatory.save(category1);
        Food food= new Food();
        food.setFoodCategory(category);
        food.setDescription(createFoodRequest.getDescription());
      food.setImages(createFoodRequest.getImages());
      food.setIngredientiteamList(createFoodRequest.getIngredientsiteams());
      food.setName(createFoodRequest.getName());
      food.setPrice(createFoodRequest.getPrice());
      food.setRestaurant(restaurant);
      food.setSeasonal(createFoodRequest.isSeasonal());
      food.setVegetarian(createFoodRequest.isVegetarin());
      food.setCreationDate(LocalDateTime.now());
    Food foodSave= foodRespositatory.save(food);
    restaurant.getFoods().add(foodSave);
    return foodSave;


    }

    @Override
    public void deleteFood(Integer foodId) throws Exception {
          Food food= findFoodById(foodId);
          food.setRestaurant(null);
          foodRespositatory.save(food);
//          foodRespositatory.deleteById(foodId);
//          categoryRepositatory.deleteById(food.getFoodCategory().getId());



    }

    @Override
    public List<Food> getRestaurantFood(Integer restaurantId, String foodCategory, boolean isVegitration, boolean isNonveg, boolean isSeasional) throws Exception {
          List<Food> food=foodRespositatory.findByRestaurantId(restaurantId);
           if(isVegitration){
               food=filterByVegitration( food,isVegitration);
           }
           if(isNonveg){
               food=filterByNonveg(food,isNonveg);
           }
           if(isSeasional){
               food=filterBySeasional(food,isSeasional);
           }
           if(foodCategory!=null || foodCategory.equals(food.isEmpty())){
               food=filterByCategory(food,foodCategory);
           }
return food;
    }

    private List<Food> filterByCategory(List<Food> food, String foodCategory) {
        return  food.stream().filter(food1 -> {
          if(food1.getFoodCategory()!=null){
              return food1.getFoodCategory().getName().equals(foodCategory);
          }
          return false;
        }).collect(Collectors.toList());
    }

    private List<Food> filterBySeasional(List<Food> food, boolean isSeasional) {
       return  food.stream().filter(food1 -> food1.isSeasonal()==isSeasional).collect(Collectors.toList());
    }

    private List<Food> filterByNonveg(List<Food> food, boolean isNonveg) {
        return  food.stream().filter(food1 -> food1.isVegetarian()==false).collect(Collectors.toList());
    }

    private List<Food> filterByVegitration(List<Food> food, boolean isVegitration) {
        return food.stream().filter(food1 -> food1.isVegetarian()).collect(Collectors.toList());
    }

    @Override
    public List<Food> searchFood(String keyword) {
       List<Food> foodList=foodRespositatory.searchFood(keyword);
       return foodList;
    }

    @Override
    public Food findFoodById(Integer foodId) throws Exception {
       Optional<Food> optionalFood=foodRespositatory.findById(foodId);
      if(optionalFood.isEmpty()){
          throw  new Exception("Food not found");
      }
      return  optionalFood.get();
    }

    @Override
    public Food updateAvailibilityStatus(Integer foodId) throws Exception {
        Food food=findFoodById(foodId);
        food.setAvailable(!food.isAvailable());
         return    foodRespositatory.save(food);

    }

    public  List<Food> getAllRestaurantFood(Integer restaurantId){
        List<Food> foodList=foodRespositatory.findByRestaurantId(restaurantId);
        return foodList;
    }
}
