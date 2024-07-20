package com.rudra.service;

import com.rudra.Request.createOrderRequest;
import com.rudra.model.User;
import com.rudra.model.order;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.query.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderService  {

    public order createOrder(createOrderRequest createOrderRequest , User user) throws Exception;
    public  order updateOrder(Integer orderId,String orderStatus) throws  Exception;
    public  void cancleOrder(Integer orderId) throws    Exception;
    public List<order>  getAllTheOrder(Integer userId)throws Exception;
    public List<order> getALLTheOrderFromRestaurant(Integer restaurantId,String orderStatus) throws Exception;
    public  order findOrderById(Integer orderId) throws Exception;
    public List<order>getALLTheOrderFromRestaurant(Integer id) throws  Exception;
}
