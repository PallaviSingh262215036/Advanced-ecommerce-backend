
package com.example.user_management.controller;

import com.example.user_management.dto.LoginRequest;
import com.example.user_management.security.JwtService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    // 🔹 Constructor injection
    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService=jwtService;
    }

    // 🔹 LOGIN API
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        //  This is where authentication happens
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // If authentication fails → exception → 401 automatically

        return jwtService.generateToken(request.getEmail());
    }

    @GetMapping("/me")
public String currentUser(Authentication authentication) {

    return authentication.getName();
}
}
