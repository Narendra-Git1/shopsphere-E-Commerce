package com.nari.shopsphere_backend.service;

import com.nari.shopsphere_backend.dto.RegisterRequest;
import com.nari.shopsphere_backend.entity.User;

public interface UserService {

    
    // REGISTER USER
    User registerUser(
            RegisterRequest request);

    
    // FIND USER BY EMAIL
    User findByEmail(
            String email);
    
    User save(User user);
}