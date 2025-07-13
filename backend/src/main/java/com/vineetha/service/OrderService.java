package com.vineetha.service;

import com.vineetha.model.*;
import com.vineetha.repository.OrderRepository;
import com.vineetha.repository.ProductRepository;
import com.vineetha.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartService cartService;

    /**
     * Create a new order from cart
     */
    public Order createOrder(Long userId, Order orderDetails) {
        // Validate user
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Create order
        Order order = new Order();
        order.setUser(user);
        order.setOrderNumber(generateOrderNumber());
        order.setStatus(OrderStatus.PENDING);
        order.setPaymentStatus(PaymentStatus.PENDING);
        order.setTotalAmount(orderDetails.getTotalAmount());
        order.setSubtotal(orderDetails.getSubtotal());
        order.setTaxAmount(orderDetails.getTaxAmount());
        order.setShippingAmount(orderDetails.getShippingAmount());
        order.setDiscountAmount(orderDetails.getDiscountAmount());
        order.setPaymentMethod(orderDetails.getPaymentMethod());
        order.setShippingAddress(orderDetails.getShippingAddress());
        order.setBillingAddress(orderDetails.getBillingAddress());
        order.setShippingCity(orderDetails.getShippingCity());
        order.setShippingState(orderDetails.getShippingState());
        order.setShippingPostalCode(orderDetails.getShippingPostalCode());
        order.setShippingCountry(orderDetails.getShippingCountry());
        order.setNotes(orderDetails.getNotes());
        order.setPromoCode(orderDetails.getPromoCode());
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        // Set order items
        order.setOrderItems(orderDetails.getOrderItems());

        // Validate and update stock for each order item
        for (OrderItem item : order.getOrderItems()) {
            Product product = productRepository.findById(item.getProduct().getId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            if (product.getStockQuantity() < item.getQuantity()) {
                throw new RuntimeException("Insufficient stock for product: " + product.getName());
            }

            // Update stock
            product.setStockQuantity(product.getStockQuantity() - item.getQuantity());
            if (product.getStockQuantity() <= 0) {
                product.setStatus(ProductStatus.OUT_OF_STOCK);
            }
            productRepository.save(product);

            // Set order item details
            item.setOrder(order);
            item.setProduct(product);
            item.setUnitPrice(product.getPrice());
            item.setTotalPrice(product.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        }

        // Save order
        Order savedOrder = orderRepository.save(order);

        // Clear user's cart after successful order creation
        cartService.clearCart(userId);

        return savedOrder;
    }

    /**
     * Get order by ID
     */
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    /**
     * Get order by order number
     */
    public Optional<Order> getOrderByOrderNumber(String orderNumber) {
        return orderRepository.findByOrderNumber(orderNumber);
    }

    /**
     * Get user's orders
     */
    public List<Order> getUserOrders(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return orderRepository.findByUserId(userId);
    }

    /**
     * Get all orders (admin only)
     */
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * Get orders by status
     */
    public List<Order> getOrdersByStatus(OrderStatus status) {
        return orderRepository.findByStatus(status);
    }

    /**
     * Get orders by payment status
     */
    public List<Order> getOrdersByPaymentStatus(PaymentStatus paymentStatus) {
        return orderRepository.findByPaymentStatus(paymentStatus);
    }

    /**
     * Update order status
     */
    public Order updateOrderStatus(Long orderId, OrderStatus status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(status);
        order.setUpdatedAt(LocalDateTime.now());

        // Update status timestamp based on status
        if (status == OrderStatus.DELIVERED) {
            order.setDeliveredAt(LocalDateTime.now());
        }

        return orderRepository.save(order);
    }

    /**
     * Update payment status
     */
    public Order updatePaymentStatus(Long orderId, PaymentStatus paymentStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setPaymentStatus(paymentStatus);
        order.setUpdatedAt(LocalDateTime.now());

        if (paymentStatus == PaymentStatus.PAID) {
            // Update order status to confirmed when payment is received
            if (order.getStatus() == OrderStatus.PENDING) {
                order.setStatus(OrderStatus.CONFIRMED);
            }
        }

        return orderRepository.save(order);
    }

    /**
     * Update tracking number
     */
    public Order updateTrackingNumber(Long orderId, String trackingNumber) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setTrackingNumber(trackingNumber);
        order.setUpdatedAt(LocalDateTime.now());

        return orderRepository.save(order);
    }

    /**
     * Process order (admin only)
     */
    public Order processOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (order.getStatus() != OrderStatus.CONFIRMED) {
            throw new RuntimeException("Order must be confirmed before processing");
        }

        order.setStatus(OrderStatus.PROCESSING);
        order.setUpdatedAt(LocalDateTime.now());

        return orderRepository.save(order);
    }

    /**
     * Ship order (admin only)
     */
    public Order shipOrder(Long orderId, String trackingNumber) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (order.getStatus() != OrderStatus.PROCESSING) {
            throw new RuntimeException("Order must be processing before shipping");
        }

        order.setStatus(OrderStatus.SHIPPED);
        order.setTrackingNumber(trackingNumber);
        order.setUpdatedAt(LocalDateTime.now());

        return orderRepository.save(order);
    }

    /**
     * Mark order as delivered
     */
    public Order markAsDelivered(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (order.getStatus() != OrderStatus.SHIPPED) {
            throw new RuntimeException("Order must be shipped before marking as delivered");
        }

        order.setStatus(OrderStatus.DELIVERED);
        order.setDeliveredAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        return orderRepository.save(order);
    }

    /**
     * Cancel order
     */
    public Order cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (order.getStatus() == OrderStatus.DELIVERED) {
            throw new RuntimeException("Cannot cancel delivered order");
        }

        // Restore product stock
        for (OrderItem item : order.getOrderItems()) {
            Product product = item.getProduct();
            product.setStockQuantity(product.getStockQuantity() + item.getQuantity());
            
            // Update product status if it was out of stock
            if (product.getStatus() == ProductStatus.OUT_OF_STOCK && product.getStockQuantity() > 0) {
                product.setStatus(ProductStatus.ACTIVE);
            }
            
            productRepository.save(product);
        }

        order.setStatus(OrderStatus.CANCELLED);
        order.setUpdatedAt(LocalDateTime.now());

        return orderRepository.save(order);
    }

    /**
     * Get orders by date range
     */
    public List<Order> getOrdersByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return orderRepository.findByCreatedAtBetween(startDate, endDate);
    }

    /**
     * Get orders by user and status
     */
    public List<Order> getOrdersByUserAndStatus(Long userId, OrderStatus status) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return orderRepository.findByUserIdAndStatus(userId, status);
    }

    /**
     * Get order statistics (admin only)
     */
    public OrderStatistics getOrderStatistics() {
        long totalOrders = orderRepository.count();
        long pendingOrders = orderRepository.countByStatus(OrderStatus.PENDING);
        long confirmedOrders = orderRepository.countByStatus(OrderStatus.CONFIRMED);
        long processingOrders = orderRepository.countByStatus(OrderStatus.PROCESSING);
        long shippedOrders = orderRepository.countByStatus(OrderStatus.SHIPPED);
        long deliveredOrders = orderRepository.countByStatus(OrderStatus.DELIVERED);
        long cancelledOrders = orderRepository.countByStatus(OrderStatus.CANCELLED);

        return new OrderStatistics(totalOrders, pendingOrders, confirmedOrders, 
                                 processingOrders, shippedOrders, deliveredOrders, cancelledOrders);
    }

    /**
     * Get revenue statistics (admin only)
     */
    public RevenueStatistics getRevenueStatistics() {
        BigDecimal totalRevenue = orderRepository.calculateTotalRevenue();
        // For now, return zero for other revenue calculations since methods don't exist
        BigDecimal todayRevenue = BigDecimal.ZERO;
        BigDecimal thisMonthRevenue = BigDecimal.ZERO;
        BigDecimal thisYearRevenue = BigDecimal.ZERO;

        return new RevenueStatistics(totalRevenue, todayRevenue, thisMonthRevenue, thisYearRevenue);
    }

    /**
     * Generate unique order number
     */
    private String generateOrderNumber() {
        return "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    // Order Statistics class
    public static class OrderStatistics {
        private final long totalOrders;
        private final long pendingOrders;
        private final long confirmedOrders;
        private final long processingOrders;
        private final long shippedOrders;
        private final long deliveredOrders;
        private final long cancelledOrders;

        public OrderStatistics(long totalOrders, long pendingOrders, long confirmedOrders,
                             long processingOrders, long shippedOrders, long deliveredOrders, long cancelledOrders) {
            this.totalOrders = totalOrders;
            this.pendingOrders = pendingOrders;
            this.confirmedOrders = confirmedOrders;
            this.processingOrders = processingOrders;
            this.shippedOrders = shippedOrders;
            this.deliveredOrders = deliveredOrders;
            this.cancelledOrders = cancelledOrders;
        }

        // Getters
        public long getTotalOrders() { return totalOrders; }
        public long getPendingOrders() { return pendingOrders; }
        public long getConfirmedOrders() { return confirmedOrders; }
        public long getProcessingOrders() { return processingOrders; }
        public long getShippedOrders() { return shippedOrders; }
        public long getDeliveredOrders() { return deliveredOrders; }
        public long getCancelledOrders() { return cancelledOrders; }
    }

    // Revenue Statistics class
    public static class RevenueStatistics {
        private final BigDecimal totalRevenue;
        private final BigDecimal todayRevenue;
        private final BigDecimal thisMonthRevenue;
        private final BigDecimal thisYearRevenue;

        public RevenueStatistics(BigDecimal totalRevenue, BigDecimal todayRevenue,
                               BigDecimal thisMonthRevenue, BigDecimal thisYearRevenue) {
            this.totalRevenue = totalRevenue;
            this.todayRevenue = todayRevenue;
            this.thisMonthRevenue = thisMonthRevenue;
            this.thisYearRevenue = thisYearRevenue;
        }

        // Getters
        public BigDecimal getTotalRevenue() { return totalRevenue; }
        public BigDecimal getTodayRevenue() { return todayRevenue; }
        public BigDecimal getThisMonthRevenue() { return thisMonthRevenue; }
        public BigDecimal getThisYearRevenue() { return thisYearRevenue; }
    }
} 