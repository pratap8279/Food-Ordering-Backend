package com.rudra.Request;

import com.rudra.model.Address;
import lombok.Data;

@Data
public class createOrderRequest {

    private Integer restaurantId;
    private Address address;

}
