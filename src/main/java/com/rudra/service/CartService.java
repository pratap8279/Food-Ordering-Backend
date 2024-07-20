package com.rudra.service;

import com.rudra.Request.CartIteamRequest;
import com.rudra.model.Cart;
import com.rudra.model.CartItem;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {

  public CartItem addItemToCart(CartIteamRequest req,String jwt) throws Exception;

  public  CartItem updatedCartItemQuantity(Integer cartItemId, int quantity) throws Exception;
  public Cart  removeCartItemFromCart(Integer cartItemId,String jwt) throws Exception;
  public  Long cartTotal(Cart cart) throws Exception;
  public Cart findCartById(Integer id) throws Exception;
  public Cart findCartByUserId(Integer userId) throws Exception;
  public  Cart clearCart(Integer userId) throws Exception;
}
