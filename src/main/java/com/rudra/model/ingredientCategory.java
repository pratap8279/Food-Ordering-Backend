package com.rudra.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import com.rudra.model.Category;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="ingredientItemCategory")
public class ingredientCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    private  String name;

    @JsonIgnore
    @ManyToOne
    private  Restaurant restaurant;


    @JsonIgnore
    @OneToMany(mappedBy = "category" , cascade = CascadeType.ALL)
    private List<ingredientsIteam> ingredientsIteamList= new ArrayList<>();
}
