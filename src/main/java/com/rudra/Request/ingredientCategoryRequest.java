package com.rudra.Request;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;

@Data
public class ingredientCategoryRequest {
    private  String name;
    private Integer restaurantId;
//    private Integer categoryId;
}
