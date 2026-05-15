package com.nari.shopsphere_backend.service;

import java.util.List;

import com.nari.shopsphere_backend.entity.Order;
import com.nari.shopsphere_backend.entity.OrderStatus;

public interface OrderService {

    
    // PLACE ORDER
    Order placeOrder(Long cartId);

    
    // GET ALL ORDERS
    List<Order> getAllOrders();

    
    // GET ORDER BY ID
    Order getOrderById(Long id);

    
    // UPDATE ORDER STATUS
    Order updateOrderStatus(
            Long orderId,
            OrderStatus status);

    
    // TOTAL REVENUE
    Double getTotalRevenue();

    
    // TOTAL ORDERS
    Long getTotalOrders();
}