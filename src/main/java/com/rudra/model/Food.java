package com.rudra.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Food {
    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private  Integer id;

    private  String name;

    private  String  description;

    private  Long price;

    @ManyToOne(cascade = CascadeType.MERGE)
    private  Category foodCategory;

    @Column(length = 1000)
    @ElementCollection
    private List<String> images;

    private  boolean available;

    @ManyToOne
    private  Restaurant restaurant;


    private  boolean isVegetarian;


    private  boolean isSeasonal;

    @ManyToMany
    private  List<ingredientsIteam> ingredientiteamList= new ArrayList<>();

    private LocalDateTime creationDate;
}
