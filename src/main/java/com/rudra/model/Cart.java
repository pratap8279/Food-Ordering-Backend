package com.rudra.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @OneToOne
    private  User customer;

    private Long total;

    @OneToMany(mappedBy = "cart" ,cascade = CascadeType.ALL ,orphanRemoval = true)
    private List<CartItem> cartItemList= new ArrayList<>();

}
