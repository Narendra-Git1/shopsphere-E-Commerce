package com.nari.shopsphere_backend.entity;

import jakarta.persistence.*;

import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    // USER NAME
    private String name;

    
    // EMAIL
    @Column(unique = true)
    private String email;

    
    // ENCRYPTED PASSWORD
    private String password;

    
    // USER ROLE
    @Enumerated(EnumType.STRING)
    private Role role;

    
    // PHONE NUMBER
    private String phone;

    
    // ADDRESS
    private String address;

    
    // CITY
    private String city;

    
    // STATE
    private String state;

    
    // PINCODE
    private String pincode;

    
    // PROFILE IMAGE URL
    private String profileImage;
}