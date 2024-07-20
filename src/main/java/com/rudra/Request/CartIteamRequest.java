package com.rudra.Request;

import lombok.Data;

import java.util.List;

@Data
public class CartIteamRequest {

    private Integer foodId;
    private Integer quantity;

    private List<String> ingredient;


}
