package com.nari.shopsphere_backend.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nari.shopsphere_backend.dto.UserResponseDTO;
import com.nari.shopsphere_backend.entity.User;
import com.nari.shopsphere_backend.payload.ApiResponse;
import com.nari.shopsphere_backend.service.UserService;


import com.nari.shopsphere_backend.dto.DashboardStatsDTO;
import com.nari.shopsphere_backend.repository.OrderRepository;
import com.nari.shopsphere_backend.repository.ProductRepository;
import com.nari.shopsphere_backend.repository.UserRepository;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    
    @Autowired
    private UserService userService;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    
    // ADMIN DASHBOARD
    @GetMapping("/dashboard")
    public String adminDashboard() {

        return "Welcome Admin";
    }

    
    // GET ALL USERS
    @GetMapping("/users")
    public ResponseEntity<ApiResponse<List<UserResponseDTO>>>
    getAllUsers() {

        List<UserResponseDTO> users =
                userService.getAllUsers()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(

                new ApiResponse<>(

                        true,

                        "Users Fetched Successfully",

                        users
                ));
    }

    
    // GET USER BY ID
    @GetMapping("/users/{id}")
    public ResponseEntity<ApiResponse<UserResponseDTO>>
    getUserById(
            @PathVariable Long id) {

        User user =
                userService.getUserById(id);

        UserResponseDTO dto =
                mapToDTO(user);

        return ResponseEntity.ok(

                new ApiResponse<>(

                        true,

                        "User Fetched Successfully",

                        dto
                ));
    }

    
    // DELETE USER
    @DeleteMapping("/users/{id}")
    public ResponseEntity<ApiResponse<String>>
    deleteUser(
            @PathVariable Long id) {

        userService.deleteUser(id);

        return ResponseEntity.ok(

                new ApiResponse<>(

                        true,

                        "User Deleted Successfully",

                        null
                ));
    }

    
    // CONVERT USER → DTO
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
    
 // DASHBOARD STATS
    @GetMapping("/stats")
    public ResponseEntity<ApiResponse<DashboardStatsDTO>>
    getDashboardStats() {

        DashboardStatsDTO stats =
                new DashboardStatsDTO();

        stats.setTotalUsers(
                userRepository.count());

        stats.setTotalProducts(
                productRepository.count());

        stats.setTotalOrders(
                orderRepository.count());

        stats.setTotalRevenue(
                orderRepository.getTotalRevenue());

        return ResponseEntity.ok(

                new ApiResponse<>(

                        true,

                        "Dashboard Stats Fetched Successfully",

                        stats
                ));
    }
}