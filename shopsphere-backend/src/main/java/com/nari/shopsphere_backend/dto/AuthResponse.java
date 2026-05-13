package com.nari.shopsphere_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {

    
    // JWT TOKEN
    private String token;
}