package com.nari.shopsphere_backend.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nari.shopsphere_backend.entity.Cart;
import com.nari.shopsphere_backend.entity.CartItem;
import com.nari.shopsphere_backend.entity.Order;
import com.nari.shopsphere_backend.entity.OrderItem;
import com.nari.shopsphere_backend.repository.CartRepository;
import com.nari.shopsphere_backend.repository.OrderRepository;
import com.nari.shopsphere_backend.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CartRepository cartRepository;

    
    // PLACE ORDER
    @Override
    public Order placeOrder(Long cartId) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() ->
                        new RuntimeException("Cart Not Found"));

        
        Order order = new Order();

        order.setStatus("PENDING");
        order.setTotalAmount(cart.getTotalPrice());

        
        List<OrderItem> orderItems =
                new ArrayList<>();

        
        for (CartItem cartItem : cart.getCartItems()) {

            OrderItem orderItem =
                    new OrderItem();

            orderItem.setProduct(
                    cartItem.getProduct());

            orderItem.setQuantity(
                    cartItem.getQuantity());

            orderItem.setPrice(
                    cartItem.getProduct().getPrice());

            orderItem.setSubTotal(
                    cartItem.getSubTotal());

            orderItem.setOrder(order);

            orderItems.add(orderItem);
        }

        
        order.setOrderItems(orderItems);

        
        Order savedOrder =
                orderRepository.save(order);

        
        // CLEAR CART
        cart.getCartItems().clear();
        cart.setTotalPrice(0.0);

        cartRepository.save(cart);

        return savedOrder;
    }

    
    // GET ALL ORDERS
    @Override
    public List<Order> getAllOrders() {

        return orderRepository.findAll();
    }

    
    // GET ORDER BY ID
    @Override
    public Order getOrderById(Long id) {

        return orderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Order Not Found"));
    }
}