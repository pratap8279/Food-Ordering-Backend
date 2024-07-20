package com.rudra.restaurantDto;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Embeddable
public class RestaurantDto {

    private  Integer id;
    private String title;
    @Column(length = 1000)
    private List<String> images;

    private  String description;


}
