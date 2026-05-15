package com.nari.shopsphere_backend.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    // ORDER DATE
    private LocalDateTime orderDate =
            LocalDateTime.now();

    
    // TOTAL AMOUNT
    private Double totalAmount;

    
    // ORDER STATUS
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    
    // ONE ORDER → MANY ORDER ITEMS
    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL,
            orphanRemoval = true)

    @JsonManagedReference
    private List<OrderItem> orderItems =
            new ArrayList<>();
}