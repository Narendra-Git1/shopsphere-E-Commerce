package com.nari.shopsphere_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.nari.shopsphere_backend.dto.AuthRequest;
import com.nari.shopsphere_backend.dto.AuthResponse;
import com.nari.shopsphere_backend.dto.RegisterRequest;
import com.nari.shopsphere_backend.entity.User;
import com.nari.shopsphere_backend.jwt.JwtUtil;
import com.nari.shopsphere_backend.payload.ApiResponse;
import com.nari.shopsphere_backend.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    
    // REGISTER API
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<User>>
    registerUser(
            @RequestBody RegisterRequest request) {

        User savedUser =
                userService.registerUser(request);

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "User Registered Successfully",
                        savedUser));
    }

    
    // LOGIN API
    @PostMapping("/login")
    public ResponseEntity<AuthResponse>
    loginUser(
            @RequestBody AuthRequest request) {

        User user =
                userService.findByEmail(
                        request.getEmail());

        
        // CHECK PASSWORD
        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new RuntimeException(
                    "Invalid Password");
        }

        
        // GENERATE JWT TOKEN
        String token =
                jwtUtil.generateToken(
                        user.getEmail());

        return ResponseEntity.ok(
                new AuthResponse(token));
    }
}