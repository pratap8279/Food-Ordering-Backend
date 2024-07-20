package com.rudra.service;

import com.rudra.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {
    public Category createCategory(String name,Integer id) throws Exception;
    public List<Category> findCategoryByRestaurantId(Integer restaurantId) throws  Exception;
    public List<Category> findCategoryByUserId(Integer Id) throws  Exception;
    public  Category findCategoryById(Integer id) throws Exception ;

}
