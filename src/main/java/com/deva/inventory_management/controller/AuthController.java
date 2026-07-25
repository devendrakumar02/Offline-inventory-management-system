package com.deva.inventory_management.controller;

import com.deva.inventory_management.dto.LoginRequest;
import com.deva.inventory_management.dto.LoginResponse;
import com.deva.inventory_management.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}