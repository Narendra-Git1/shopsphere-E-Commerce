package com.nari.shopsphere_backend.service;

import java.util.List;

import com.nari.shopsphere_backend.dto.RegisterRequest;
import com.nari.shopsphere_backend.entity.User;

public interface UserService {

    
    // REGISTER USER
    User registerUser(
            RegisterRequest request);

    
    // FIND USER BY EMAIL
    User findByEmail(
            String email);

    
    // SAVE USER
    User save(User user);

    
    // GET ALL USERS
    List<User> getAllUsers();

    
    // GET USER BY ID
    User getUserById(Long id);

    
    // DELETE USER
    void deleteUser(Long id);
}