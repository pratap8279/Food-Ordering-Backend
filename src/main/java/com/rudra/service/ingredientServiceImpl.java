package com.rudra.service;

import com.rudra.model.Restaurant;
import com.rudra.model.ingredientCategory;
import com.rudra.model.ingredientsIteam;
import com.rudra.repositatory.IngredientCategoryRepositatory;
import com.rudra.repositatory.IngredientIteamRepositatory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ingredientServiceImpl implements ingredientService {
    @Autowired
private     RestaurantServiceImpl restaurantService;
    @Autowired
    private IngredientCategoryRepositatory ingredientCategoryRepositatory;

    @Autowired
    private IngredientIteamRepositatory ingredientIteamRepositatory;


    @Override
    public ingredientCategory createIndgredientCategory(String name, Integer restaurantId) throws Exception {
        Restaurant restaurant=restaurantService.findRestaurantById(restaurantId);
        ingredientCategory ingredientCategory=new ingredientCategory();
        ingredientCategory.setName(name);
        ingredientCategory.setRestaurant(restaurant);
        return  ingredientCategoryRepositatory.save(ingredientCategory);
    }

    @Override
    public ingredientCategory findIngredientCategoryById(Integer id) throws Exception {
     Optional< ingredientCategory> ingredientCategory= ingredientCategoryRepositatory.findById(id);
     if (ingredientCategory.isEmpty()){
         throw  new Exception("Ingredient Not Found");
     }
        return ingredientCategory.get();
    }

    @Override
    public List<ingredientCategory> findIngredientCategoryByRestaurantId(Integer id) throws Exception {
                   restaurantService.findRestaurantById(id);
                 return  ingredientCategoryRepositatory.findByRestaurantId(id);
    }

    @Override
    public ingredientsIteam  createIngredients(Integer restaurantId, String ingredientName, Integer categoryId) throws Exception {
                Restaurant restaurant= restaurantService.findRestaurantById(restaurantId);
                ingredientCategory ingredientCategory=findIngredientCategoryById(categoryId);
                ingredientsIteam ingredientsIteam=new ingredientsIteam();
                ingredientsIteam.setCategory(ingredientCategory);
                ingredientsIteam.setRestaurant(restaurant);
                ingredientsIteam.setName(ingredientName);
                 ingredientsIteam ingredientsIteamList=  ingredientIteamRepositatory.save(ingredientsIteam);
                 ingredientCategory.getIngredientsIteamList().add(ingredientsIteamList);
              return  ingredientsIteamList;



    }

    @Override
    public List<ingredientsIteam> findRestaurantByIngredient(Integer restaurantId) throws Exception {
         return  ingredientIteamRepositatory.findByRestaurantId(restaurantId);
    }
    @Override
    public List<ingredientsIteam> findIngredientItemByCategory(Integer categoryId) throws Exception {
        return  ingredientIteamRepositatory.findByCategoryId(categoryId);
    }

    @Override
    public ingredientsIteam updateStock(Integer id) throws Exception {
           Optional<ingredientsIteam> ingredientsIteam=ingredientIteamRepositatory.findById(id);
           if(ingredientsIteam.isEmpty()){
               throw  new Exception("Ingredient Not found");
           }
        ingredientsIteam ingredientsIteam1=ingredientsIteam.get();
           ingredientsIteam1.setStock(!ingredientsIteam1.isStock());

       return  ingredientIteamRepositatory.save(ingredientsIteam1);

    }
}
