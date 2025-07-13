package com.vineetha.service;

import com.vineetha.model.CartItem;
import com.vineetha.model.Product;
import com.vineetha.model.User;
import com.vineetha.repository.CartItemRepository;
import com.vineetha.repository.ProductRepository;
import com.vineetha.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Add item to cart
     */
    public CartItem addToCart(Long userId, Long productId, int quantity) {
        // Validate user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Validate product
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Check if product is in stock
        if (product.getStockQuantity() < quantity) {
            throw new RuntimeException("Insufficient stock available");
        }

        // Check if product is active
        if (product.getStatus().toString() != "ACTIVE") {
            throw new RuntimeException("Product is not available");
        }

        // Check if item already exists in cart
        Optional<CartItem> existingItem = cartItemRepository.findByUserAndProduct(user, product);
        
        if (existingItem.isPresent()) {
            // Update quantity
            CartItem cartItem = existingItem.get();
            int newQuantity = cartItem.getQuantity() + quantity;
            
            // Check stock again
            if (product.getStockQuantity() < newQuantity) {
                throw new RuntimeException("Insufficient stock available");
            }
            
            cartItem.setQuantity(newQuantity);
            cartItem.setUpdatedAt(LocalDateTime.now());
            return cartItemRepository.save(cartItem);
        } else {
            // Create new cart item
            CartItem cartItem = new CartItem();
            cartItem.setUser(user);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setUnitPrice(product.getPrice());
            cartItem.setCreatedAt(LocalDateTime.now());
            cartItem.setUpdatedAt(LocalDateTime.now());
            
            return cartItemRepository.save(cartItem);
        }
    }

    /**
     * Update cart item quantity
     */
    public CartItem updateCartItemQuantity(Long userId, Long cartItemId, int quantity) {
        // Validate user owns the cart item
        CartItem cartItem = cartItemRepository.findByIdAndUserId(cartItemId, userId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        if (quantity <= 0) {
            // Remove item if quantity is 0 or negative
            cartItemRepository.delete(cartItem);
            return null;
        }

        // Check stock availability
        Product product = cartItem.getProduct();
        if (product.getStockQuantity() < quantity) {
            throw new RuntimeException("Insufficient stock available");
        }

        cartItem.setQuantity(quantity);
        cartItem.setUpdatedAt(LocalDateTime.now());
        
        return cartItemRepository.save(cartItem);
    }

    /**
     * Remove item from cart
     */
    public void removeFromCart(Long userId, Long cartItemId) {
        CartItem cartItem = cartItemRepository.findByIdAndUserId(cartItemId, userId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        cartItemRepository.delete(cartItem);
    }

    /**
     * Get user's cart items
     */
    public List<CartItem> getUserCart(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return cartItemRepository.findByUserOrderByCreatedAtDesc(user);
    }

    /**
     * Get cart item by ID
     */
    public Optional<CartItem> getCartItemById(Long cartItemId) {
        return cartItemRepository.findById(cartItemId);
    }

    /**
     * Clear user's cart
     */
    public void clearCart(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<CartItem> cartItems = cartItemRepository.findByUser(user);
        cartItemRepository.deleteAll(cartItems);
    }

    /**
     * Get cart total
     */
    public CartTotal getCartTotal(Long userId) {
        List<CartItem> cartItems = getUserCart(userId);
        
        BigDecimal subtotal = BigDecimal.ZERO;
        int totalItems = 0;

        for (CartItem item : cartItems) {
            BigDecimal itemTotal = item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
            subtotal = subtotal.add(itemTotal);
            totalItems += item.getQuantity();
        }

        // Calculate shipping (free if subtotal > 499)
        BigDecimal shipping = subtotal.compareTo(new BigDecimal("499")) > 0 ? 
            BigDecimal.ZERO : new BigDecimal("99");

        // Calculate tax (18% GST)
        BigDecimal tax = subtotal.multiply(new BigDecimal("0.18"));

        // Calculate total
        BigDecimal total = subtotal.add(shipping).add(tax);

        return new CartTotal(subtotal, shipping, tax, total, totalItems);
    }

    /**
     * Move item to saved for later
     */
    public CartItem moveToSavedForLater(Long userId, Long cartItemId) {
        CartItem cartItem = cartItemRepository.findByIdAndUserId(cartItemId, userId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        cartItem.setSavedForLater(true);
        cartItem.setUpdatedAt(LocalDateTime.now());
        
        return cartItemRepository.save(cartItem);
    }

    /**
     * Move item back to cart from saved for later
     */
    public CartItem moveToCart(Long userId, Long cartItemId) {
        CartItem cartItem = cartItemRepository.findByIdAndUserId(cartItemId, userId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        // Check stock availability
        Product product = cartItem.getProduct();
        if (product.getStockQuantity() < cartItem.getQuantity()) {
            throw new RuntimeException("Insufficient stock available");
        }

        cartItem.setSavedForLater(false);
        cartItem.setUpdatedAt(LocalDateTime.now());
        
        return cartItemRepository.save(cartItem);
    }

    /**
     * Get saved for later items
     */
    public List<CartItem> getSavedForLater(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return cartItemRepository.findByUserAndSavedForLaterTrue(user);
    }

    /**
     * Update cart item prices (when product prices change)
     */
    public void updateCartItemPrices(Long userId) {
        List<CartItem> cartItems = getUserCart(userId);
        
        for (CartItem item : cartItems) {
            Product product = item.getProduct();
            if (!item.getUnitPrice().equals(product.getPrice())) {
                item.setUnitPrice(product.getPrice());
                item.setUpdatedAt(LocalDateTime.now());
                cartItemRepository.save(item);
            }
        }
    }

    /**
     * Check cart item availability
     */
    public List<CartItemAvailability> checkCartItemAvailability(Long userId) {
        List<CartItem> cartItems = getUserCart(userId);
        List<CartItemAvailability> availabilityList = new java.util.ArrayList<>();

        for (CartItem item : cartItems) {
            Product product = item.getProduct();
            boolean available = product.getStockQuantity() >= item.getQuantity() && 
                              product.getStatus().toString().equals("ACTIVE");
            
            availabilityList.add(new CartItemAvailability(
                item.getId(), 
                product.getId(), 
                product.getName(), 
                available, 
                product.getStockQuantity(),
                item.getQuantity()
            ));
        }

        return availabilityList;
    }

    /**
     * Get cart count
     */
    public int getCartCount(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return (int) cartItemRepository.countByUserAndSavedForLaterFalse(user);
    }

    /**
     * Get cart statistics (admin only)
     */
    public CartStatistics getCartStatistics() {
        long totalCartItems = cartItemRepository.count();
        long activeCartItems = cartItemRepository.countBySavedForLaterFalse();
        long savedForLaterItems = cartItemRepository.countBySavedForLaterTrue();
        
        return new CartStatistics(totalCartItems, activeCartItems, savedForLaterItems);
    }

    // Cart Total class
    public static class CartTotal {
        private final BigDecimal subtotal;
        private final BigDecimal shipping;
        private final BigDecimal tax;
        private final BigDecimal total;
        private final int totalItems;

        public CartTotal(BigDecimal subtotal, BigDecimal shipping, BigDecimal tax, BigDecimal total, int totalItems) {
            this.subtotal = subtotal;
            this.shipping = shipping;
            this.tax = tax;
            this.total = total;
            this.totalItems = totalItems;
        }

        // Getters
        public BigDecimal getSubtotal() { return subtotal; }
        public BigDecimal getShipping() { return shipping; }
        public BigDecimal getTax() { return tax; }
        public BigDecimal getTotal() { return total; }
        public int getTotalItems() { return totalItems; }
    }

    // Cart Item Availability class
    public static class CartItemAvailability {
        private final Long cartItemId;
        private final Long productId;
        private final String productName;
        private final boolean available;
        private final int stockQuantity;
        private final int requestedQuantity;

        public CartItemAvailability(Long cartItemId, Long productId, String productName, 
                                  boolean available, int stockQuantity, int requestedQuantity) {
            this.cartItemId = cartItemId;
            this.productId = productId;
            this.productName = productName;
            this.available = available;
            this.stockQuantity = stockQuantity;
            this.requestedQuantity = requestedQuantity;
        }

        // Getters
        public Long getCartItemId() { return cartItemId; }
        public Long getProductId() { return productId; }
        public String getProductName() { return productName; }
        public boolean isAvailable() { return available; }
        public int getStockQuantity() { return stockQuantity; }
        public int getRequestedQuantity() { return requestedQuantity; }
    }

    // Cart Statistics class
    public static class CartStatistics {
        private final long totalCartItems;
        private final long activeCartItems;
        private final long savedForLaterItems;

        public CartStatistics(long totalCartItems, long activeCartItems, long savedForLaterItems) {
            this.totalCartItems = totalCartItems;
            this.activeCartItems = activeCartItems;
            this.savedForLaterItems = savedForLaterItems;
        }

        // Getters
        public long getTotalCartItems() { return totalCartItems; }
        public long getActiveCartItems() { return activeCartItems; }
        public long getSavedForLaterItems() { return savedForLaterItems; }
    }
} 