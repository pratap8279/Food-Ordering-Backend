package com.rudra.repositatory;

import com.rudra.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant ,Integer> {
    @Query("select r From Restaurant r Where lower(r.name) like lower(concat('%' ,:query,'%'))"+
    "OR lower(r.cuisineType) Like lower(concat('%',:query,'%'))")
    public List<Restaurant> findBySearchQuery(String query);
    Restaurant findByOwnerId(Integer userId);
}
