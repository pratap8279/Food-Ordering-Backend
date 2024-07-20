package com.rudra.service;

import com.rudra.model.Restaurant;
import com.rudra.model.ingredientCategory;
import com.rudra.model.ingredientsIteam;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public interface ingredientService {
    public List<ingredientsIteam> findIngredientItemByCategory(Integer categoryId)throws Exception;
    public ingredientCategory createIndgredientCategory(String name, Integer restaurantId)throws Exception ;
    public  ingredientCategory findIngredientCategoryById(Integer id) throws Exception;
    public  List<ingredientCategory> findIngredientCategoryByRestaurantId(Integer id) throws Exception;

    public  ingredientsIteam createIngredients(Integer restaurantId,String ingredientName,Integer categoryId) throws Exception;
    public List<ingredientsIteam> findRestaurantByIngredient(Integer restaurantId) throws Exception;

    public  ingredientsIteam updateStock(Integer id) throws Exception;


}
