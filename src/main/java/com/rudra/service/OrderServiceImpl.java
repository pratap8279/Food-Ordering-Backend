package com.rudra.service;

import com.fasterxml.jackson.core.sym.CharsToNameCanonicalizer;
import com.rudra.Request.createOrderRequest;
import com.rudra.model.*;

import com.rudra.repositatory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private RestaurantServiceImpl restaurantService;

    @Autowired
    CartServiceImpl cartService;


//    @Autowired
//    CartRepositatory cartRepositatory;


    @Autowired
    UserRepositatory userRepositatory;
    @Autowired private AddressRepositatory addressRepositatory;
    @Autowired OrderRepositatory orderRepositatory;
    @Autowired
    orderItemRepositatory  orderItemRepositatory;


    @Override
    public order createOrder(createOrderRequest createOrderRequest, User user) throws Exception {

     Address deliveryAddress=createOrderRequest.getAddress();
     Address addressFromdb=addressRepositatory.save(deliveryAddress);
        if (!user.getAddressList().contains(addressFromdb)){
            user.getAddressList().add(addressFromdb);
            userRepositatory.save(user);
        }

        Restaurant restaurant=restaurantService.findRestaurantById(createOrderRequest.getRestaurantId());
        order order= new order();
        order.setOrderStatus("pending");
        order.setCreatedAt(LocalDateTime.now());
        order.setCustomeruser(user);
        order.setRestaurant(restaurant);
        order.setDeliveryAddress(deliveryAddress);
           Cart cart =cartService.findCartByUserId(user.getId());
         List<orderIteam> orderIteams=new ArrayList<>();
         for (CartItem cartItem:cart.getCartItemList()){
         orderIteam orderIteam= new orderIteam();
         orderIteam.setFood(cartItem.getFood());
         orderIteam.setQuantity(cartItem.getQuantity());
         orderIteam.setTotalPrice(cartItem.getTotalPrice());
         orderIteam.setIngredients(cartItem.getIngredient());
         orderIteam savedOrderitem=orderItemRepositatory.save(orderIteam);
         orderIteams.add(savedOrderitem);
         }
         Long totalPrice=cartService.cartTotal(cart);
         order.setTotalAmount(totalPrice);
        order.setIteams(orderIteams);
        order savedOrder=    orderRepositatory.save(order);
         restaurant.getOrderList().add(order);
         return order;

    }

    @Override
    public order updateOrder(Integer orderId, String orderStatus) throws Exception {
       order order=findOrderById(orderId);
      if(orderStatus.equals("OUT_OF_DELIVERY")||
              orderStatus.equals("DELIVERED")||
              orderStatus.equals("COMPLETED") ||
              orderStatus.equals("PENDING") ){
          order.setOrderStatus(orderStatus);
         return   orderRepositatory.save(order);

      }
      throw  new Exception("Please Select a Valid order status");


    }

    @Override
    public void cancleOrder(Integer orderId) throws Exception {
           order order=findOrderById(orderId);
           orderRepositatory.deleteById(orderId);

    }

    @Override
    public List<order> getAllTheOrder(Integer userId) throws Exception {
       List<order> orderList=orderRepositatory.findByCustomeruserId(userId);
       return orderList;
    }

    @Override
    public List<order> getALLTheOrderFromRestaurant(Integer restaurantId, String orderStatus) throws Exception {
        List<order> orderList=     orderRepositatory.findByRestaurantId(restaurantId);
        if (orderList!=null) {
         orderList=orderList.stream().filter(orderListnew -> orderListnew.getOrderStatus().equals(orderStatus)).collect(Collectors.toList());
            return orderList;
        }
        return orderList;
    }

    public  order findOrderById(Integer orderId) throws Exception {
       Optional<order> order=orderRepositatory.findById(orderId);
       if (order.isEmpty()){
           throw  new Exception("Not found Any Oeder");
       }
       return order.get();
    }

    @Override
    public List<order> getALLTheOrderFromRestaurant(Integer id) throws Exception {
        List<order> orderList=orderRepositatory.findByRestaurantId(id);
        return orderList;
    }

}
