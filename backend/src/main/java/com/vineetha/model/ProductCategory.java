package com.vineetha.model;

/**
 * Product categories for Vineetha E-commerce Platform
 * 
 * @author Vinish
 */
public enum ProductCategory {
    ELECTRONICS("Electronics"),
    CLOTHING("Clothing"),
    FOOD_BEVERAGES("Food & Beverages"),
    BOOKS("Books"),
    HOME_GARDEN("Home & Garden"),
    SPORTS_OUTDOORS("Sports & Outdoors"),
    BEAUTY_HEALTH("Beauty & Health"),
    TOYS_GAMES("Toys & Games"),
    AUTOMOTIVE("Automotive"),
    PET_SUPPLIES("Pet Supplies");

    private final String displayName;

    ProductCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
} 