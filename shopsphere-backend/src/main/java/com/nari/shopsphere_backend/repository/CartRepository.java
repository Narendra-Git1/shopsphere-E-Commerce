package com.nari.shopsphere_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nari.shopsphere_backend.entity.Cart;

public interface CartRepository
        extends JpaRepository<Cart, Long> {

}