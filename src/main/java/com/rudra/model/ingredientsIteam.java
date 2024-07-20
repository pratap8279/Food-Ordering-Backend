package com.rudra.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ingredientsIteam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    private String name;

    @ManyToOne
    private  ingredientCategory category;

    @JsonIgnore
    @ManyToOne
    private  Restaurant restaurant;

    private  boolean isStock=true;
}
