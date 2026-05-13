package com.nari.shopsphere_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nari.shopsphere_backend.entity.OrderItem;

public interface OrderItemRepository
        extends JpaRepository<OrderItem, Long> {

}