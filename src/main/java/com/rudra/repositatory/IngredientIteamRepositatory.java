package com.rudra.repositatory;

import com.rudra.model.ingredientsIteam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientIteamRepositatory extends JpaRepository<ingredientsIteam ,Integer> {
    List<ingredientsIteam> findByRestaurantId(Integer id);
    List<ingredientsIteam> findByCategoryId(Integer id);
}
