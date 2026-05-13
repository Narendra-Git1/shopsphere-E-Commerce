package com.nari.shopsphere_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nari.shopsphere_backend.entity.User;

public interface UserRepository
        extends JpaRepository<User, Long> {

    
    // FIND USER BY EMAIL
    Optional<User> findByEmail(String email);
}