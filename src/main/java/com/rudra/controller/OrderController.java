package com.rudra.controller;

import com.rudra.Request.FetchRestaurantOrderRequest;
import com.rudra.Request.createOrderRequest;
import com.rudra.model.User;
import com.rudra.model.order;
import com.rudra.response.PaymentResponse;
import com.rudra.service.OrderServiceImpl;
import com.rudra.service.PaymentService;
import com.rudra.service.UserServiceImpl;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    UserServiceImpl userService;

    @Autowired
    private PaymentService paymentService;
    @Autowired
    OrderServiceImpl orderService;

//    @PostMapping("/create")
//    public ResponseEntity<PaymentResponse> createOrder(@RequestBody createOrderRequest req   , @RequestHeader("Authorization") String jwt) throws Exception {
//        User user=userService.findUserByJwtToken(jwt);
//        order order=orderService.createOrder(req,user);
//       PaymentResponse paymentResponse=paymentService.createPaymentLink(order);
//        return new ResponseEntity<>(paymentResponse, HttpStatus.OK);
//    }
        @PostMapping("/create")
    public ResponseEntity<order> createOrder(@RequestBody createOrderRequest req   , @RequestHeader("Authorization") String jwt) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        order order=orderService.createOrder(req,user);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping("/cancleOrder")
    public ResponseEntity cancleOrder(@PathVariable Integer orderId) throws Exception {
            orderService.cancleOrder(orderId);
            return new ResponseEntity("order Is cancle ",HttpStatus.OK);
    }
    @GetMapping("/getUserOrder")
    public ResponseEntity<List<order>> getAllOrder(@RequestHeader("Authorization") String jwt) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
              List<order> orderList=orderService.getAllTheOrder(user.getId());
        return new ResponseEntity(orderList,HttpStatus.OK);
    }

    @GetMapping("/fetchRestaurantOrders")
    public ResponseEntity<List<order>> fetchRestaurantOrders(@RequestBody FetchRestaurantOrderRequest fetchRestaurantOrderRequest,
                                                             @RequestHeader("Authorization") String jwt
                                                             ) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        List<order> orderList=orderService.getALLTheOrderFromRestaurant(fetchRestaurantOrderRequest.getRestaurantId(),
                fetchRestaurantOrderRequest.getOrderStatus());
        return  new ResponseEntity<>(orderList,HttpStatus.OK);


    }






}
