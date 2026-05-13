package com.nari.shopsphere_backend.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cart")
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    // TOTAL CART PRICE
    private Double totalPrice = 0.0;

    
    // ONE CART → MANY CART ITEMS
    @OneToMany(
            mappedBy = "cart",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    
    @JsonManagedReference
    private List<CartItem> cartItems =
            new ArrayList<>();
}