package com.rudra.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.rudra.restaurantDto.RestaurantDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    private  String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private  String password;
    private  String fullName;
    private  USER_ROLE role;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL , mappedBy ="customeruser",orphanRemoval = true )
    private List<order> orderList=new ArrayList<>();

    @ElementCollection
    private  List<RestaurantDto> favorites=new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL , orphanRemoval = true)
     private  List<Address> addressList=new  ArrayList<>();


}
