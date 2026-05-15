package com.nari.shopsphere_backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nari.shopsphere_backend.entity.Order;
import com.nari.shopsphere_backend.entity.OrderStatus;
import com.nari.shopsphere_backend.payload.ApiResponse;
import com.nari.shopsphere_backend.service.OrderService;

@RestController
@RequestMapping("/api/admin/orders")
public class AdminOrderController {

    
    @Autowired
    private OrderService orderService;

    
    // GET ALL ORDERS
    @GetMapping
    public ResponseEntity<ApiResponse<List<Order>>>
    getAllOrders() {

        List<Order> orders =
                orderService.getAllOrders();

        return ResponseEntity.ok(

                new ApiResponse<>(

                        true,

                        "Orders Fetched Successfully",

                        orders
                )
        );
    }

    
    // GET ORDER BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Order>>
    getOrderById(
            @PathVariable Long id) {

        Order order =
                orderService.getOrderById(id);

        return ResponseEntity.ok(

                new ApiResponse<>(

                        true,

                        "Order Fetched Successfully",

                        order
                )
        );
    }

    
    // UPDATE ORDER STATUS
    @PutMapping("/{orderId}/status")
    public ResponseEntity<ApiResponse<Order>>
    updateOrderStatus(

            @PathVariable Long orderId,

            @RequestParam OrderStatus status) {

        Order updatedOrder =
                orderService.updateOrderStatus(
                        orderId,
                        status);

        return ResponseEntity.ok(

                new ApiResponse<>(

                        true,

                        "Order Status Updated Successfully",

                        updatedOrder
                )
        );
    }

    
    // TOTAL REVENUE
    @GetMapping("/revenue")
    public ResponseEntity<ApiResponse<Double>>
    getTotalRevenue() {

        Double revenue =
                orderService.getTotalRevenue();

        return ResponseEntity.ok(

                new ApiResponse<>(

                        true,

                        "Revenue Fetched Successfully",

                        revenue
                )
        );
    }

    
    // TOTAL ORDERS COUNT
    @GetMapping("/count")
    public ResponseEntity<ApiResponse<Long>>
    getTotalOrders() {

        Long totalOrders =
                orderService.getTotalOrders();

        return ResponseEntity.ok(

                new ApiResponse<>(

                        true,

                        "Orders Count Fetched Successfully",

                        totalOrders
                )
        );
    }
}