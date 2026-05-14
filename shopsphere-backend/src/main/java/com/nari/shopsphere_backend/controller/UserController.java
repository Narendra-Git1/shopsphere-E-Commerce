package com.nari.shopsphere_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.nari.shopsphere_backend.dto.UserProfileDTO;
import com.nari.shopsphere_backend.dto.UserResponseDTO;
import com.nari.shopsphere_backend.entity.User;
import com.nari.shopsphere_backend.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    
    // GET USER PROFILE
    @GetMapping("/profile")
    public ResponseEntity<UserResponseDTO> getProfile(
            Authentication authentication) {

        User loggedInUser =
                (User) authentication.getPrincipal();

        String email =
                loggedInUser.getEmail();

        User user =
                userService.findByEmail(email);

        
        UserResponseDTO response =
                mapToDTO(user);

        return ResponseEntity.ok(response);
    }

    
    // UPDATE USER PROFILE
    @PutMapping("/profile")
    public ResponseEntity<UserResponseDTO> updateProfile(

            Authentication authentication,

            @RequestBody UserProfileDTO dto) {

        User loggedInUser =
                (User) authentication.getPrincipal();

        String email =
                loggedInUser.getEmail();

        User user =
                userService.findByEmail(email);

        
        user.setName(dto.getName());
        user.setPhone(dto.getPhone());
        user.setAddress(dto.getAddress());
        user.setCity(dto.getCity());
        user.setState(dto.getState());
        user.setPincode(dto.getPincode());
        user.setProfileImage(dto.getProfileImage());

        
        User updatedUser =
                userService.save(user);

        
        UserResponseDTO response =
                mapToDTO(updatedUser);

        return ResponseEntity.ok(response);
    }

    
    // ENTITY TO DTO
    private UserResponseDTO mapToDTO(
            User user) {

        UserResponseDTO dto =
                new UserResponseDTO();

        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        dto.setPhone(user.getPhone());
        dto.setAddress(user.getAddress());
        dto.setCity(user.getCity());
        dto.setState(user.getState());
        dto.setPincode(user.getPincode());
        dto.setProfileImage(user.getProfileImage());

        return dto;
    }
}