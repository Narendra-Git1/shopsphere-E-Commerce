package com.nari.shopsphere_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nari.shopsphere_backend.entity.CartItem;

public interface CartItemRepository
        extends JpaRepository<CartItem, Long> {

}