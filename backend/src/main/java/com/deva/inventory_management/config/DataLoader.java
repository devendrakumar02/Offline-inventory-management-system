package com.deva.inventory_management.config;

import com.deva.inventory_management.entity.User;
import com.deva.inventory_management.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository,
                      PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {

        if (userRepository.findByUsername("admin").isEmpty()) {

            User admin = new User(
                    "admin",
                    passwordEncoder.encode("admin123"),
                    "ADMIN"
            );

            userRepository.save(admin);

            System.out.println("Default Admin Created");
        }
    }
}