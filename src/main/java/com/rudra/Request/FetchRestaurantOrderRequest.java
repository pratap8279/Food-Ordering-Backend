package com.rudra.Request;

import lombok.Data;

@Data
public class FetchRestaurantOrderRequest {

    private  Integer restaurantId;
    private String orderStatus;
}
