package com.example.user_management.security;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.example.user_management.entity.User;
import com.example.user_management.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class CustomUserDetailsService implements UserDetailsService {
       private final UserRepository userRepository;
       private final PasswordEncoder passwordEncoder;
       public CustomUserDetailsService(UserRepository userRepository,PasswordEncoder passwordEncoder){
        this.userRepository= userRepository;
        this.passwordEncoder = passwordEncoder;
       }
       @Override
       public UserDetails loadUserByUsername(String email){
        User user= userRepository.findByEmail(email)
                   .orElseThrow(()->new RuntimeException("User not found"));

    System.out.println("Entered password match check:");
    System.out.println(passwordEncoder.matches("1234", user.getPassword()));
    System.out.println("Roles: " + user.getRoles());

        return org.springframework.security.core.userdetails.User
               .builder()
               .username(user.getEmail())
               .password(user.getPassword())
               .roles(user.getRoles().stream()
                    .map(role->role.getRoleName())
                    .toArray(String[]::new))
                .build();
       }
       
}
