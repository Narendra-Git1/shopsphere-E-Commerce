package com.nari.shopsphere_backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cart_items")
@Data
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    // PRODUCT QUANTITY
    private Integer quantity;

    
    // SUBTOTAL
    private Double subTotal;

    
    // MANY CART ITEMS → ONE CART
    @ManyToOne
    @JoinColumn(name = "cart_id")
    
    @JsonBackReference
    private Cart cart;

    
    // MANY CART ITEMS → ONE PRODUCT
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}