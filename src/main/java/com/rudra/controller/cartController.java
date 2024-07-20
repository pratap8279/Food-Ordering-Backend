package com.rudra.controller;

import com.rudra.Request.CartIteamRequest;
import com.rudra.Request.UpdateCartItemRequest;
import com.rudra.model.Cart;
import com.rudra.model.CartItem;
import com.rudra.model.User;
import com.rudra.service.CartServiceImpl;
import com.rudra.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class cartController {
    @Autowired
   private CartServiceImpl cartService;
    @Autowired
    private UserServiceImpl userService;

    @PutMapping("/addItemToCart")
    public ResponseEntity<CartItem> addItemToCart(@RequestBody CartIteamRequest cartIteamRequest,
                                                  @RequestHeader("Authorization") String jwt) throws Exception {
    CartItem cartItem=   cartService.addItemToCart(cartIteamRequest ,jwt);
       return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @PutMapping("/updatedCartItemQuantity")
    public ResponseEntity<CartItem> updatedCartItemQuantity(@RequestBody UpdateCartItemRequest req,
                                                            @RequestHeader("Authorization") String jwt) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        CartItem cartItem=cartService.updatedCartItemQuantity(req.getCardItemId(), req.getQuantity() );
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }

    @DeleteMapping("/removeCartItem/{id}")
    public ResponseEntity<Cart> removeCartItem(@PathVariable Integer id ,
                                               @RequestHeader("Authorization") String jwt) throws Exception {
        Cart cart=cartService.removeCartItemFromCart(id,jwt);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping("/cartTotal")
    public  Long cartTotal(@RequestBody Cart cart) throws Exception {
        return cartService.cartTotal(cart);
    }

    @PostMapping("/findCartById/{id}")
    public  ResponseEntity<Cart> findCartById(@PathVariable Integer id) throws Exception {
       Cart cart= cartService.findCartById(id);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping("/findCartByUserId/{id}")
    public  ResponseEntity<Cart> findCartByUserId(@RequestHeader("Authorization") String jwt) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        Cart cart= cartService.findCartByUserId(user.getId());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @DeleteMapping("/clearCart/{id}")
    public ResponseEntity<Cart> clearCart(@RequestHeader("Authorization") String jwt) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        Cart cart=cartService.clearCart(user.getId());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping("/findUserCart")
    public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String jwt) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        Cart cart=cartService.findCartByUserId(user.getId());

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

}
