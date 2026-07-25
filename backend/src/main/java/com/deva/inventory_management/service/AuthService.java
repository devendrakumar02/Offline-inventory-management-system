package com.deva.inventory_management.service;

import com.deva.inventory_management.dto.LoginRequest;
import com.deva.inventory_management.dto.LoginResponse;
import com.deva.inventory_management.entity.User;
import com.deva.inventory_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public LoginResponse login(LoginRequest request) {

        Optional<User> user = userRepository.findByUsername(request.getUsername());

        if (user.isPresent()) {

            if (passwordEncoder.matches(
                    request.getPassword(),
                    user.get().getPassword())) {

                return new LoginResponse(true, "Login Successful");
            }
        }

        return new LoginResponse(false, "Invalid Username or Password");
    }
}