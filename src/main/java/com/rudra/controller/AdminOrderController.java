package com.rudra.controller;

import com.rudra.model.User;
import com.rudra.model.order;
import com.rudra.service.OrderServiceImpl;
import com.rudra.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminOrderController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    OrderServiceImpl orderService;

    @GetMapping("/getOrderHistoryFromRestaurant/{id}")
    public ResponseEntity<List<order>> getOrderHistoryFromRestaurant(@RequestHeader("Authorization") String jwt,
                                                                     @PathVariable Integer id,
                                                                     @RequestParam(required = false) String orderStatus
                                                                     ) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        List<order> order=orderService.getALLTheOrderFromRestaurant(id,orderStatus);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }
    @GetMapping("/getAllOrderFromRestaurant/{id}")
    public ResponseEntity<List<order>> getAllRestaurantFromOrder(@RequestHeader("Authorization") String jwt,
                                                                     @PathVariable Integer id

    ) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
        List<order> order=orderService.getALLTheOrderFromRestaurant(id);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/update/{orderId}/{orderStatus}")
    public ResponseEntity<order> updateOrderStatus(@RequestHeader("Authorization") String jwt,
                                                                     @PathVariable Integer orderId,
                                                                       @PathVariable String orderStatus
    ) throws Exception {
        User user=userService.findUserByJwtToken(jwt);
           order order=orderService.updateOrder(orderId,orderStatus);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
