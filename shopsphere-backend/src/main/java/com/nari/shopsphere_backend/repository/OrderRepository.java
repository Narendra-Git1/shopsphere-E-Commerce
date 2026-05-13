package com.nari.shopsphere_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nari.shopsphere_backend.entity.Order;

public interface OrderRepository
        extends JpaRepository<Order, Long> {

}