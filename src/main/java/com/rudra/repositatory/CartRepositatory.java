package com.rudra.repositatory;

import com.rudra.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface CartRepositatory extends JpaRepository<Cart,Integer> {
    public  Cart findByCustomerId(Integer customerId);
}
