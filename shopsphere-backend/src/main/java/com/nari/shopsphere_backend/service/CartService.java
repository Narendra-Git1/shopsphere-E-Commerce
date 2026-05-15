package com.nari.shopsphere_backend.service;

import com.nari.shopsphere_backend.entity.Cart;

public interface CartService {

    
    // ADD PRODUCT TO CART
    Cart addToCart(
            Long cartId,
            Long productId,
            Integer quantity);

    
    // GET CART
    Cart getCart(Long cartId);

    
    // UPDATE CART ITEM QUANTITY
    Cart updateCartItemQuantity(
            Long cartItemId,
            Integer quantity);

    
    // REMOVE CART ITEM
    void removeCartItem(Long cartItemId);

    
    // CLEAR CART
    void clearCart(Long cartId);
}