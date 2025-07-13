package com.vineetha.model;

/**
 * Review status for Vineetha E-commerce Platform
 * 
 * @author Vinish
 */
public enum ReviewStatus {
    PENDING("Pending"),
    APPROVED("Approved"),
    REJECTED("Rejected"),
    SPAM("Spam");

    private final String displayName;

    ReviewStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
} 