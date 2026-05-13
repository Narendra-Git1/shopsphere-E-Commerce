package com.nari.shopsphere_backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "order_items")
@Data
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    // PRODUCT QUANTITY
    private Integer quantity;

    
    // PRODUCT PRICE
    private Double price;

    
    // SUBTOTAL
    private Double subTotal;

    
    // MANY ORDER ITEMS → ONE ORDER
    @ManyToOne
    @JoinColumn(name = "order_id")

    @JsonBackReference
    private Order order;

    
    // MANY ORDER ITEMS → ONE PRODUCT
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}