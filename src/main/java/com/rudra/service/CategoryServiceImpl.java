package com.rudra.service;

import com.rudra.model.Category;
import com.rudra.model.Restaurant;
import com.rudra.repositatory.CategoryRepositatory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements   CategoryService{
    @Autowired
  private   CategoryRepositatory categoryRepositatory;
    @Autowired
    RestaurantServiceImpl restaurantService;

//    @Autowired
//    CategoryService categoryService;

    @Override
    public Category createCategory(String name, Integer id) throws Exception {
        Restaurant restaurant=restaurantService.getRestaurantByUserId(id);
        Category category= new Category();
        category.setName(name);
        category.setRestaurant(restaurant);
        return categoryRepositatory.save(category);
    }

    @Override
    public List<Category> findCategoryByRestaurantId(Integer restaurantId) throws Exception {
        Restaurant restaurant=restaurantService.getRestaurantByUserId(restaurantId);
        List<Category> categories=categoryRepositatory.findByRestaurantId(restaurantId);
        return categories;
    }
    @Override
    public List<Category> findCategoryByUserId(Integer restaurantId) throws Exception {
        Restaurant restaurant=restaurantService.getRestaurantByUserId(restaurantId);
        List<Category> categories=categoryRepositatory.findByRestaurantId(restaurant.getId());
        return categories;
    }

    @Override
    public Category findCategoryById(Integer id) throws Exception {
       Optional<Category> categories=categoryRepositatory.findById(id);
       if(categories.isEmpty()){
           throw new Exception("Category not found");
       }
        return categories.get();
    }
}
