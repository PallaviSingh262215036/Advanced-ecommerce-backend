package com.example.user_management.controller;
import com.example.user_management.service.AdminService;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.user_management.dto.AdminAnalyticsDTO;

@RestController
public class AdminController {
    
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/admin/test")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminTest() {

        return "Only ADMIN can access this";
    }

    @GetMapping("/admin/analytics")
    @PreAuthorize("hasRole('ADMIN')")
    public AdminAnalyticsDTO getAnalytics() {

        return adminService.getAnalytics();
    }
}
