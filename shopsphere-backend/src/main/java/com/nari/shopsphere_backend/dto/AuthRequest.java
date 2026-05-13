package com.nari.shopsphere_backend.dto;

import lombok.Data;

@Data
public class AuthRequest {

    
    // USER EMAIL
    private String email;

    
    // USER PASSWORD
    private String password;
}