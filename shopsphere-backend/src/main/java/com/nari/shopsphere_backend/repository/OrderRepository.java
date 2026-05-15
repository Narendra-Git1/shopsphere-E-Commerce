package com.nari.shopsphere_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nari.shopsphere_backend.entity.Order;
import com.nari.shopsphere_backend.entity.OrderStatus;

public interface OrderRepository
        extends JpaRepository<Order, Long> {

    
    // TOTAL REVENUE
    @Query("""
            SELECT COALESCE(SUM(o.totalAmount), 0)
            FROM Order o
            """)
    Double getTotalRevenue();

    
    // FIND ORDERS BY STATUS
    List<Order> findByStatus(
            OrderStatus status);
}