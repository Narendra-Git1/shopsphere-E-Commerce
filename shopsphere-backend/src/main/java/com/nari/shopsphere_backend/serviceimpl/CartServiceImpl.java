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

        
        // subtotal
        cartItem.setSubTotal(
                product.getPrice() * quantity);

        
        cartItem.setCart(cart);

        
        cart.getCartItems()
                .add(cartItem);

        
        // update total cart price
        double total =
                cart.getCartItems()
                .stream()
                .mapToDouble(
                        CartItem::getSubTotal)
                .sum();

        cart.setTotalPrice(total);

        
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

    
    // REMOVE CART ITEM
    @Override
    public void removeCartItem(
            Long cartItemId) {

        cartItemRepository
                .deleteById(cartItemId);
    }

    
    // CLEAR CART
    @Override
    public void clearCart(Long cartId) {

        Cart cart = getCart(cartId);

        cart.getCartItems().clear();

        cart.setTotalPrice(0.0);

        cartRepository.save(cart);
    }
}