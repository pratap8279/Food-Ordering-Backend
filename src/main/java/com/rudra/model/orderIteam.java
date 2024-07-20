package com.rudra.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class orderIteam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @ManyToOne
    private  Food food;

    private Integer quantity;

    private  Long totalPrice;

    private List<String> ingredients;
}
