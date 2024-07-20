package com.rudra.repositatory;

import com.rudra.model.ingredientCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IngredientCategoryRepositatory extends JpaRepository<ingredientCategory,Integer> {
 public    List<ingredientCategory> findByRestaurantId(Integer id);
}
