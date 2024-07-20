package com.rudra.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;


    @ManyToOne
    @JsonIgnore
    private  Cart cart;

    @ManyToOne
    private  Food food;

    private Integer quantity;

    private List<String> ingredient;

    private  Long totalPrice;
}
