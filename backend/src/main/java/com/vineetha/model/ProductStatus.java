package com.vineetha.model;

/**
 * Product status for Vineetha E-commerce Platform
 * 
 * @author Vinish
 */
public enum ProductStatus {
    ACTIVE("Active"),
    INACTIVE("Inactive"),
    OUT_OF_STOCK("Out of Stock"),
    DISCONTINUED("Discontinued"),
    DRAFT("Draft");

    private final String displayName;

    ProductStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
} 