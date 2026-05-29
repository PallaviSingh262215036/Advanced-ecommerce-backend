package com.example.user_management.service;

import com.example.user_management.dto.OrderItemResponseDTO;
import com.example.user_management.dto.OrderResponseDTO;
import com.example.user_management.entity.*;
import com.example.user_management.repository.*;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class OrderService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;

    public OrderService(UserRepository userRepository,
                        CartRepository cartRepository,
                        OrderRepository orderRepository) {

        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public String placeOrder(Authentication authentication) {

        // current logged-in user
        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        // fetch cart
        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() ->
                        new RuntimeException("Cart not found"));

        // empty cart check
        if (cart.getCartItems().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        // create order
        OrderEntity order = new OrderEntity();
        order.setUser(user);

        double total = 0;

        // convert cart items → order items
        for (CartItem cartItem : cart.getCartItems()) {

            Product product = cartItem.getProduct();

            // stock validation
            if (product.getStock() < cartItem.getQuantity()) {
                throw new RuntimeException(
                        product.getName() + " out of stock"
                );
            }

            // reduce stock
            product.setStock(
                    product.getStock() - cartItem.getQuantity()
            );

            // create order item snapshot
            OrderItem orderItem = new OrderItem();

            orderItem.setOrder(order);

            orderItem.setProductName(product.getName());

            orderItem.setPriceAtPurchase(product.getPrice());

            orderItem.setQuantity(cartItem.getQuantity());

            // add to order
            order.getOrderItems().add(orderItem);

            // calculate total
            total += product.getPrice()
                    * cartItem.getQuantity();
        }

        order.setTotalAmount(total);

        // save order
        orderRepository.save(order);

        // clear cart
        cart.getCartItems().clear();

        return "Order placed successfully";
    }
    //getMyOrders function
    public List<OrderResponseDTO> getMyOrders(
        Authentication authentication) {

    String email = authentication.getName();

    User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                    new RuntimeException("User not found"));

    List<OrderEntity> orders =
            orderRepository.findWithItemsByUser(user);

    return orders.stream().map(order -> {

        OrderResponseDTO dto =
                new OrderResponseDTO();

        dto.setOrderId(order.getId());

        dto.setTotalAmount(order.getTotalAmount());

        dto.setCreatedAt(order.getCreatedAt());

        List<OrderItemResponseDTO> itemDTOs =
                order.getOrderItems()
                        .stream()
                        .map(item -> {

                            OrderItemResponseDTO itemDTO =
                                    new OrderItemResponseDTO();

                            itemDTO.setProductName(
                                    item.getProductName());

                            itemDTO.setPriceAtPurchase(
                                    item.getPriceAtPurchase());

                            itemDTO.setQuantity(
                                    item.getQuantity());

                            return itemDTO;

                        }).toList();

        dto.setItems(itemDTOs);

        return dto;

    }).toList();
}
}
