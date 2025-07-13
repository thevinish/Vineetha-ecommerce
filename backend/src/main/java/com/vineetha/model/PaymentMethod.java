package com.vineetha.model;

/**
 * Payment methods for Vineetha E-commerce Platform
 * 
 * @author Vinish
 */
public enum PaymentMethod {
    CASH_ON_DELIVERY("Cash on Delivery"),
    UPI("UPI"),
    CREDIT_CARD("Credit Card"),
    DEBIT_CARD("Debit Card"),
    NET_BANKING("Net Banking"),
    WALLET("Digital Wallet");

    private final String displayName;

    PaymentMethod(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
} 