package com.nari.shopsphere_backend.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nari.shopsphere_backend.entity.Cart;
import com.nari.shopsphere_backend.entity.CartItem;
import com.nari.shopsphere_backend.entity.Product;
import com.nari.shopsphere_backend.repository.CartItemRepository;
import com.nari.shopsphere_backend.repository.CartRepository;
import com.nari.shopsphere_backend.repository.ProductRepository;
import com.nari.shopsphere_backend.service.CartService;

@Service
public class CartServiceImpl
        implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    
    // ADD TO CART
    @Override
    public Cart addToCart(
            Long cartId,
            Long productId,
            Integer quantity) {

        
        Cart cart = cartRepository
                .findById(cartId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Cart Not Found"));

        
        Product product = productRepository
                .findById(productId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Product Not Found"));

        
        CartItem cartItem =
                new CartItem();

        cartItem.setProduct(product);

        cartItem.setQuantity(quantity);

        
        // SUBTOTAL
        cartItem.setSubTotal(
                product.getPrice() * quantity);

        
        cartItem.setCart(cart);

        
        cart.getCartItems()
                .add(cartItem);

        
        updateCartTotal(cart);

        
        return cartRepository.save(cart);
    }

    
    // GET CART
    @Override
    public Cart getCart(Long cartId) {

        return cartRepository
                .findById(cartId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Cart Not Found"));
    }

    
    // UPDATE CART ITEM QUANTITY
    @Override
    public Cart updateCartItemQuantity(
            Long cartItemId,
            Integer quantity) {

        
        CartItem cartItem =
                cartItemRepository
                .findById(cartItemId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Cart Item Not Found"));

        
        cartItem.setQuantity(quantity);

        
        cartItem.setSubTotal(

                cartItem.getProduct().getPrice()
                        * quantity);

        
        cartItemRepository.save(cartItem);

        
        Cart cart =
                cartItem.getCart();

        
        updateCartTotal(cart);

        
        return cartRepository.save(cart);
    }

    
    // REMOVE CART ITEM
    @Override
    public void removeCartItem(
            Long cartItemId) {

        
        CartItem cartItem =
                cartItemRepository
                .findById(cartItemId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Cart Item Not Found"));

        
        Cart cart =
                cartItem.getCart();

        
        cart.getCartItems()
                .remove(cartItem);

        
        cartItemRepository.delete(cartItem);

        
        updateCartTotal(cart);

        
        cartRepository.save(cart);
    }

    
    // CLEAR CART
    @Override
    public void clearCart(Long cartId) {

        
        Cart cart = getCart(cartId);

        
        cart.getCartItems().clear();

        
        cart.setTotalPrice(0.0);

        
        cartRepository.save(cart);
    }

    
    // UPDATE TOTAL PRICE
    private void updateCartTotal(
            Cart cart) {

        
        double total =
                cart.getCartItems()
                .stream()
                .mapToDouble(
                        CartItem::getSubTotal)
                .sum();

        
        cart.setTotalPrice(total);
    }
}