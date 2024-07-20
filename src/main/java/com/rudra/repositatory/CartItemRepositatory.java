package com.rudra.repositatory;

import com.rudra.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepositatory extends JpaRepository<CartItem ,Integer> {
   List<CartItem>  findByCartId(Integer id);


}
