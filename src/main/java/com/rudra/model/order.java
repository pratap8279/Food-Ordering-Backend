package com.rudra.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order1")
public class order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer Id;

    @ManyToOne
    private  User customeruser;
//
    @JsonIgnore
    @ManyToOne
    private Restaurant restaurant;
//
//
    private  Long totalAmount;
//
    private  String orderStatus;


    private LocalDateTime createdAt;

    @JsonIgnore
    @ManyToOne
    private Address deliveryAddress;

    @OneToMany
    private List<orderIteam> iteams;

//    private  Payment payment
    private  Integer totalItem;
    private  Long totalPrice;


}
