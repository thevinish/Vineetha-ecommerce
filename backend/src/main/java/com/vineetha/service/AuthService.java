package com.vineetha.service;

import com.vineetha.model.User;
import com.vineetha.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Service class for Authentication and JWT token management
 * 
 * @author Vinish
 */
@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Authenticate user and generate JWT token
     */
    public Map<String, Object> authenticateUser(String email, String password) {
        try {
            // Authenticate user
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Get user details
            User user = userService.getUserByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Update last login is handled in UserService.authenticateUser method

            // Generate JWT token
            String token = jwtUtil.generateTokenWithUserInfo(email, user.getId(), user.getRole().name());

            // Prepare response
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("user", user);
            response.put("message", "Login successful");

            return response;

        } catch (Exception e) {
            throw new RuntimeException("Authentication failed: " + e.getMessage());
        }
    }

    /**
     * Register new user
     */
    public Map<String, Object> registerUser(User user) {
        try {
            // Register user
            User registeredUser = userService.registerUser(user);

            // Generate JWT token
            String token = jwtUtil.generateTokenWithUserInfo(registeredUser.getEmail(), registeredUser.getId(), registeredUser.getRole().name());

            // Prepare response
            Map<String, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("user", registeredUser);
            response.put("message", "Registration successful");

            return response;

        } catch (Exception e) {
            throw new RuntimeException("Registration failed: " + e.getMessage());
        }
    }

    /**
     * Validate JWT token
     */
    public boolean validateToken(String token) {
        try {
            return jwtUtil.isTokenValid(token);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get user from JWT token
     */
    public User getUserFromToken(String token) {
        try {
            String email = jwtUtil.extractUsername(token);
            return userService.getUserByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));
        } catch (Exception e) {
            throw new RuntimeException("Invalid token: " + e.getMessage());
        }
    }

    /**
     * Refresh JWT token
     */
    public String refreshToken(String token) {
        try {
            String email = jwtUtil.extractUsername(token);
            User user = userService.getUserByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));
            return jwtUtil.generateTokenWithUserInfo(email, user.getId(), user.getRole().name());
        } catch (Exception e) {
            throw new RuntimeException("Token refresh failed: " + e.getMessage());
        }
    }

    /**
     * Logout user (invalidate token)
     */
    public void logoutUser(String token) {
        // In a real application, you might want to blacklist the token
        // For now, we'll just return success
        // The token will expire naturally
    }

    /**
     * Get current authenticated user
     */
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            String email = authentication.getName();
            return userService.getUserByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));
        }
        throw new RuntimeException("No authenticated user found");
    }

    /**
     * Check if user is authenticated
     */
    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null && authentication.isAuthenticated();
    }

    /**
     * Check if user has admin role
     */
    public boolean isAdmin() {
        try {
            User user = getCurrentUser();
            return user.getRole().name().equals("ADMIN");
        } catch (Exception e) {
            return false;
        }
    }
} 