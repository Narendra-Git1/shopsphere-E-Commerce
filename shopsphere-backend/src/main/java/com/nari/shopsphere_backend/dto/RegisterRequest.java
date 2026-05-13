package com.nari.shopsphere_backend.dto;

import com.nari.shopsphere_backend.entity.Role;

import lombok.Data;

@Data
public class RegisterRequest {

    
    // USER NAME
    private String name;

    
    // EMAIL
    private String email;

    
    // PASSWORD
    private String password;

    
    // ROLE
    private Role role;
}