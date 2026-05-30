package com.example.user_management.service;

import com.example.user_management.dto.AddToCartRequestDTO;
import com.example.user_management.dto.CartItemResponseDTO;
import com.example.user_management.dto.CartResponseDTO;
import com.example.user_management.entity.*;
import com.example.user_management.repository.*;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import com.example.user_management.dto.*;
import java.util.List;

@Service
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository,
                       CartItemRepository cartItemRepository,
                       ProductRepository productRepository,
                       UserRepository userRepository) {

        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public void addToCart(AddToCartRequestDTO dto,
                          Authentication authentication) {

        // logged-in email
        String email = authentication.getName();

        // fetch user
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        // fetch/create cart
        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });

        // fetch product
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() ->
                        new RuntimeException("Product not found"));

        // create cart item
       // check if product already exists in cart
CartItem cartItem = cartItemRepository
        .findByCartAndProduct(cart, product)
        .orElseGet(() -> {

            CartItem newItem = new CartItem();

            newItem.setCart(cart);
            newItem.setProduct(product);
            newItem.setQuantity(0);

            return newItem;
        });

// merge quantities
cartItem.setQuantity(
        cartItem.getQuantity() + dto.getQuantity()
);

cartItemRepository.save(cartItem);
    }


    @Transactional(readOnly = true)
    public CartResponseDTO getCart(Authentication authentication) {

    String email = authentication.getName();

    User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                    new RuntimeException("User not found"));

    Cart cart = cartRepository.findByUser(user)
            .orElseThrow(() ->
                    new RuntimeException("Cart not found"));

    List<CartItemResponseDTO> items =
            cart.getCartItems()
                    .stream()
                    .map(item -> {

                        Double subtotal =
                                item.getQuantity()
                                * item.getProduct().getPrice();

                        return new CartItemResponseDTO(
                                item.getId(),
                                item.getProduct().getName(),
                                item.getQuantity(),
                                item.getProduct().getPrice(),
                                subtotal
                        );
                    })
                    .toList();

    Double totalAmount =
            items.stream()
                    .mapToDouble(CartItemResponseDTO::getSubtotal)
                    .sum();

    return new CartResponseDTO(
            items,
            totalAmount
    );
}

 @Transactional
public void removeFromCart(Long cartItemId,
                           Authentication authentication) {

    String email = authentication.getName();

    User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                    new RuntimeException("User not found"));

    Cart cart = cartRepository.findByUser(user)
            .orElseThrow(() ->
                    new RuntimeException("Cart not found"));

    CartItem cartItem = cartItemRepository
            .findByIdAndCart(cartItemId, cart)
            .orElseThrow(() ->
                    new RuntimeException("Cart item not found"));

    // remove from cart collection
    cart.getCartItems().remove(cartItem);

    // optional explicit delete
    cartItemRepository.delete(cartItem);
}

}
