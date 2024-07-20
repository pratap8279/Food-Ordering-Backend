package com.rudra.service;

import com.rudra.Request.CartIteamRequest;
import com.rudra.model.Cart;
import com.rudra.model.CartItem;
import com.rudra.model.Food;
import com.rudra.model.User;
import com.rudra.repositatory.CartItemRepositatory;
import com.rudra.repositatory.CartRepositatory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepositatory cartRepositatory;



    @Autowired
    private FoodServiceImpl foodService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private CartItemRepositatory cartItemRepositatory;

    public CartItem addItemToCart(CartIteamRequest req,String jwt) throws Exception {
       Food food=foodService.findFoodById(req.getFoodId());
        User user=userService.findUserByJwtToken(jwt);
        Cart cart=cartRepositatory.findByCustomerId(user.getId());

           for(CartItem cartItem1:cart.getCartItemList()){
               if(cartItem1.getFood().equals(food)){
                   int quantity=cartItem1.getQuantity()+req.getQuantity();
                   return updatedCartItemQuantity(cartItem1.getId(),quantity);
               }
           }
        CartItem cartItem=new CartItem();
        cartItem.setCart(cart);
           cartItem.setFood(food);
          cartItem.setQuantity(req.getQuantity());
          cartItem.setIngredient(req.getIngredient());
          cartItem.setTotalPrice((req.getQuantity())*food.getPrice());

          CartItem  cartItemFromDb=cartItemRepositatory.save(cartItem);
          cart.getCartItemList().add(cartItemFromDb);
          return cartItemFromDb;
    }

    @Override
    public CartItem updatedCartItemQuantity(Integer cartItemId, int quantity) throws Exception {
        Optional<CartItem> cartItem=cartItemRepositatory.findById(cartItemId);
//        Optional<CartItem> cartItem=cartItemRepositatory.find
//        Cart cart=cartRepositatory.findByCustomerId()
//        Optional<CartItem> cartItem=cartItemRepositatory.findByCartId(cartItemId);
//        List<CartItem> cartItem=cartItemRepositatory.findByCartId(cartItemId);
        if (cartItem.isEmpty()){
            throw  new Exception("cartItem Not found ");
        }
         cartItem.get().setQuantity(cartItem.get().getQuantity()+quantity);
        cartItem.get().setTotalPrice(cartItem.get().getTotalPrice()+(cartItem.get().getFood().getPrice()*quantity));

        return    cartItemRepositatory.save(cartItem.get());


    }

    @Override
    public Cart removeCartItemFromCart(Integer cartItemId, String jwt) throws Exception {
       User user=userService.findUserByJwtToken(jwt);
       Cart cart=findCartByUserId(user.getId());
      Optional<CartItem> cartItem=cartItemRepositatory.findById(cartItemId);
      if(cartItem.isEmpty()){
          throw new Exception("cart item not found");
      }
       cart.getCartItemList().remove(cartItem.get());
       return cartRepositatory.save(cart);
    }

    @Override
    @Deprecated
    public Long cartTotal(Cart cart) throws Exception {
        Long total=0L;
        List<CartItem> cartItem=  cartItemRepositatory.findByCartId(cart.getId());

//     for(CartItem cartItem:cart.getCartItemList()){
         for(CartItem cartItem1:cartItem){
             Long l= Long.valueOf(cartItem1.getQuantity());
             total +=  cartItem1.getFood().getPrice() * l;


     }

     return total;
    }

    @Override
    public Cart findCartById(Integer id) throws Exception {
    Optional<Cart> cart=cartRepositatory.findById(id);
    if (cart.isEmpty()){
        throw  new Exception("Cart not find");
    }
    return cart.get();

    }

    @Override
    public Cart findCartByUserId(Integer userId) throws Exception {

     Cart cart=cartRepositatory.findByCustomerId(userId);
     cart.setTotal(cartTotal(cart));
//        cart.setTotal(cart.getTotal());
     return cart;
    }

    @Override
    public Cart clearCart(Integer userId) throws Exception {

    Cart cart=findCartByUserId(userId);
     cart.getCartItemList().clear();
     return cartRepositatory.save(cart);
    }
}
