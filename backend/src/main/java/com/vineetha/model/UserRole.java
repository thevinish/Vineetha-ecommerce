package com.vineetha.model;

/**
 * User roles for Vineetha E-commerce Platform
 * 
 * @author Vinish
 */
public enum UserRole {
    CUSTOMER("Customer"),
    ADMIN("Administrator"),
    MODERATOR("Moderator");

    private final String displayName;

    UserRole(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
} 