package com.example.sample.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sample.entity.User;
import com.example.sample.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Map<String, String> credentials) {
        try {
            String username = credentials.get("username");
            String password = credentials.get("password");
            if (username == null || password == null) {
                return ResponseEntity.badRequest().body("Username and password are required");
            }
            User user = userService.registerUser(username, password);
            logger.info("User registered successfully: {}", username);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            logger.error("Error during user registration: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error during user registration", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> credentials) {
        try {
            String username = credentials.get("username");
            String password = credentials.get("password");
            if (username == null || password == null) {
                return ResponseEntity.badRequest().body("Username and password are required");
            }
            User user = userService.authenticateUser(username, password);
            logger.info("User authenticated successfully: {}", username);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            logger.error("Error during user authentication: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            logger.error("Unexpected error during user authentication", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }
}
