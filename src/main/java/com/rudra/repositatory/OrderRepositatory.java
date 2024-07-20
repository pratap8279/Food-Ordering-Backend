package com.rudra.repositatory;

import com.rudra.model.order;
import org.hibernate.query.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepositatory extends JpaRepository<order ,Integer> {
    public List<order> findByRestaurantId(Integer restaurantId);
    public List<order> findByCustomeruserId(Integer customerId);
}
