package com.rudra.repositatory;

import com.rudra.model.Category;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepositatory  extends JpaRepository<Category , Integer> {

    public List<Category> findByRestaurantId(Integer restaurantId);
//    public Category findByCategoryId(Integer id);


}
