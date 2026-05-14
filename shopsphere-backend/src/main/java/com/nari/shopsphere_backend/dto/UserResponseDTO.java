package com.nari.shopsphere_backend.dto;

import com.nari.shopsphere_backend.entity.Role;

import lombok.Data;

@Data
public class UserResponseDTO {

    private Long id;

    private String name;

    private String email;

    private Role role;

    private String phone;

    private String address;

    private String city;

    private String state;

    private String pincode;

    private String profileImage;
}