package com.vineetha.controller;

import com.vineetha.model.CartItem;
import com.vineetha.service.CartService;
import com.vineetha.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/cart")
@CrossOrigin(origins = "*")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Get user's cart items
     */
    @GetMapping
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
    public ResponseEntity<?> getUserCart(@RequestHeader("Authorization") String token) {
        try {
            Long userId = jwtUtil.extractUserId(token.replace("Bearer ", ""));
            List<CartItem> cartItems = cartService.getUserCart(userId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("cartItems", cartItems);
            response.put("count", cartItems.size());
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Add item to cart
     */
    @PostMapping("/add")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
    public ResponseEntity<?> addToCart(@RequestHeader("Authorization") String token,
                                     @RequestBody AddToCartRequest request) {
        try {
            Long userId = jwtUtil.extractUserId(token.replace("Bearer ", ""));
            CartItem cartItem = cartService.addToCart(userId, request.getProductId(), request.getQuantity());
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Item added to cart successfully");
            response.put("cartItem", cartItem);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Update cart item quantity
     */
    @PutMapping("/{cartItemId}/quantity")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
    public ResponseEntity<?> updateCartItemQuantity(@RequestHeader("Authorization") String token,
                                                  @PathVariable Long cartItemId,
                                                  @RequestBody UpdateQuantityRequest request) {
        try {
            Long userId = jwtUtil.extractUserId(token.replace("Bearer ", ""));
            CartItem cartItem = cartService.updateCartItemQuantity(userId, cartItemId, request.getQuantity());
            
            Map<String, Object> response = new HashMap<>();
            if (cartItem == null) {
                response.put("success", true);
                response.put("message", "Item removed from cart");
            } else {
                response.put("success", true);
                response.put("message", "Cart item quantity updated successfully");
                response.put("cartItem", cartItem);
            }
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Remove item from cart
     */
    @DeleteMapping("/{cartItemId}")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
    public ResponseEntity<?> removeFromCart(@RequestHeader("Authorization") String token,
                                          @PathVariable Long cartItemId) {
        try {
            Long userId = jwtUtil.extractUserId(token.replace("Bearer ", ""));
            cartService.removeFromCart(userId, cartItemId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Item removed from cart successfully");
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Clear user's cart
     */
    @DeleteMapping("/clear")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
    public ResponseEntity<?> clearCart(@RequestHeader("Authorization") String token) {
        try {
            Long userId = jwtUtil.extractUserId(token.replace("Bearer ", ""));
            cartService.clearCart(userId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Cart cleared successfully");
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Get cart total
     */
    @GetMapping("/total")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
    public ResponseEntity<?> getCartTotal(@RequestHeader("Authorization") String token) {
        try {
            Long userId = jwtUtil.extractUserId(token.replace("Bearer ", ""));
            CartService.CartTotal cartTotal = cartService.getCartTotal(userId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("cartTotal", cartTotal);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Move item to saved for later
     */
    @PostMapping("/{cartItemId}/save-for-later")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
    public ResponseEntity<?> moveToSavedForLater(@RequestHeader("Authorization") String token,
                                               @PathVariable Long cartItemId) {
        try {
            Long userId = jwtUtil.extractUserId(token.replace("Bearer ", ""));
            CartItem cartItem = cartService.moveToSavedForLater(userId, cartItemId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Item moved to saved for later");
            response.put("cartItem", cartItem);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Move item back to cart from saved for later
     */
    @PostMapping("/{cartItemId}/move-to-cart")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
    public ResponseEntity<?> moveToCart(@RequestHeader("Authorization") String token,
                                      @PathVariable Long cartItemId) {
        try {
            Long userId = jwtUtil.extractUserId(token.replace("Bearer ", ""));
            CartItem cartItem = cartService.moveToCart(userId, cartItemId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Item moved back to cart");
            response.put("cartItem", cartItem);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Get saved for later items
     */
    @GetMapping("/saved-for-later")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
    public ResponseEntity<?> getSavedForLater(@RequestHeader("Authorization") String token) {
        try {
            Long userId = jwtUtil.extractUserId(token.replace("Bearer ", ""));
            List<CartItem> savedItems = cartService.getSavedForLater(userId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("savedItems", savedItems);
            response.put("count", savedItems.size());
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Check cart item availability
     */
    @GetMapping("/availability")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
    public ResponseEntity<?> checkCartItemAvailability(@RequestHeader("Authorization") String token) {
        try {
            Long userId = jwtUtil.extractUserId(token.replace("Bearer ", ""));
            List<CartService.CartItemAvailability> availability = cartService.checkCartItemAvailability(userId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("availability", availability);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Get cart count
     */
    @GetMapping("/count")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
    public ResponseEntity<?> getCartCount(@RequestHeader("Authorization") String token) {
        try {
            Long userId = jwtUtil.extractUserId(token.replace("Bearer ", ""));
            int count = cartService.getCartCount(userId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("count", count);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Update cart item prices (when product prices change)
     */
    @PostMapping("/update-prices")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
    public ResponseEntity<?> updateCartItemPrices(@RequestHeader("Authorization") String token) {
        try {
            Long userId = jwtUtil.extractUserId(token.replace("Bearer ", ""));
            cartService.updateCartItemPrices(userId);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Cart item prices updated successfully");
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * Get cart statistics (admin only)
     */
    @GetMapping("/admin/statistics")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getCartStatistics() {
        try {
            CartService.CartStatistics statistics = cartService.getCartStatistics();
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("statistics", statistics);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            
            return ResponseEntity.badRequest().body(response);
        }
    }

    // Request classes
    public static class AddToCartRequest {
        private Long productId;
        private int quantity;

        // Getters and Setters
        public Long getProductId() { return productId; }
        public void setProductId(Long productId) { this.productId = productId; }
        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }
    }

    public static class UpdateQuantityRequest {
        private int quantity;

        // Getters and Setters
        public int getQuantity() { return quantity; }
        public void setQuantity(int quantity) { this.quantity = quantity; }
    }
} 