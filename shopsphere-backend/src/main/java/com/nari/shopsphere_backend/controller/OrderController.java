package com.nari.shopsphere_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nari.shopsphere_backend.entity.Order;
import com.nari.shopsphere_backend.payload.ApiResponse;
import com.nari.shopsphere_backend.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    
    // PLACE ORDER
    @PostMapping("/place/{cartId}")
    public ResponseEntity<ApiResponse<Order>>
    placeOrder(
            @PathVariable Long cartId) {

        Order order =
                orderService.placeOrder(cartId);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Order Placed Successfully",
                        order));
    }

    
    // GET ALL ORDERS
    @GetMapping
    public ResponseEntity<ApiResponse<List<Order>>>
    getAllOrders() {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Orders Fetched Successfully",
                        orderService.getAllOrders()));
    }

    
    // GET ORDER BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Order>>
    getOrderById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Order Fetched Successfully",
                        orderService.getOrderById(id)));
    }
}