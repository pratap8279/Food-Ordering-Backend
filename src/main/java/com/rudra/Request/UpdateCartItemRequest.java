package com.rudra.Request;

import lombok.Data;

@Data
public class UpdateCartItemRequest {
    public Integer cardItemId;
    public int quantity;
}
