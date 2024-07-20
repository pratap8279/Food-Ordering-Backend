package com.rudra.Request;

import lombok.Data;

@Data
public class ingredientIteamRequest {

    private String name;
    private Integer categoryId;
    private Integer restaurantId;
}
