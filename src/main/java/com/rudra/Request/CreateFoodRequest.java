package com.rudra.Request;

import com.rudra.model.Category;
import com.rudra.model.ingredientsIteam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateFoodRequest {

    private  String name;
    private  Category category;
    private  String description;
    private Long price;
    private List<String> images;
    private Integer restaurantId;
    private boolean vegetarin;
    private boolean seasonal;
    private  boolean available;
    private  List<ingredientsIteam> ingredientsiteams;
}
