package com.nari.shopsphere_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nari.shopsphere_backend.entity.Cart;
import com.nari.shopsphere_backend.payload.ApiResponse;
import com.nari.shopsphere_backend.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    
    @Autowired
    private CartService cartService;

    
    // ADD TO CART
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<Cart>>
    addToCart(

            @RequestParam Long cartId,

            @RequestParam Long productId,

            @RequestParam Integer quantity) {

        
        Cart cart =
                cartService.addToCart(

                        cartId,

                        productId,

                        quantity);

        
        return ResponseEntity.ok(

                new ApiResponse<>(

                        true,

                        "Product Added To Cart",

                        cart
                ));
    }

    
    // GET CART
    @GetMapping("/{cartId}")
    public ResponseEntity<ApiResponse<Cart>>
    getCart(
            @PathVariable Long cartId) {

        
        Cart cart =
                cartService.getCart(cartId);

        
        return ResponseEntity.ok(

                new ApiResponse<>(

                        true,

                        "Cart Fetched Successfully",

                        cart
                ));
    }

    
    // UPDATE CART ITEM QUANTITY
    @PutMapping("/update/{cartItemId}")
    public ResponseEntity<ApiResponse<Cart>>
    updateCartItemQuantity(

            @PathVariable Long cartItemId,

            @RequestParam Integer quantity) {

        
        Cart cart =
                cartService.updateCartItemQuantity(

                        cartItemId,

                        quantity);

        
        return ResponseEntity.ok(

                new ApiResponse<>(

                        true,

                        "Cart Updated Successfully",

                        cart
                ));
    }

    
    // REMOVE CART ITEM
    @DeleteMapping("/remove/{cartItemId}")
    public ResponseEntity<ApiResponse<String>>
    removeCartItem(
            @PathVariable Long cartItemId) {

        
        cartService.removeCartItem(
                cartItemId);

        
        return ResponseEntity.ok(

                new ApiResponse<>(

                        true,

                        "Cart Item Removed",

                        null
                ));
    }

    
    // CLEAR CART
    @DeleteMapping("/clear/{cartId}")
    public ResponseEntity<ApiResponse<String>>
    clearCart(
            @PathVariable Long cartId) {

        
        cartService.clearCart(cartId);

        
        return ResponseEntity.ok(

                new ApiResponse<>(

                        true,

                        "Cart Cleared Successfully",

                        null
                ));
    }
}