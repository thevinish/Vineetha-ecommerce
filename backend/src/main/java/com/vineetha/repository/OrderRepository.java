package com.vineetha.repository;

import com.vineetha.model.Order;
import com.vineetha.model.OrderStatus;
import com.vineetha.model.PaymentStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Order entity
 * 
 * @author Vinish
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * Find order by order number
     */
    Optional<Order> findByOrderNumber(String orderNumber);

    /**
     * Check if order exists by order number
     */
    boolean existsByOrderNumber(String orderNumber);

    /**
     * Find orders by user
     */
    List<Order> findByUserId(Long userId);

    /**
     * Find orders by status
     */
    List<Order> findByStatus(OrderStatus status);

    /**
     * Find orders by payment status
     */
    List<Order> findByPaymentStatus(PaymentStatus paymentStatus);

    /**
     * Find orders by status and payment status
     */
    List<Order> findByStatusAndPaymentStatus(OrderStatus status, PaymentStatus paymentStatus);

    /**
     * Find orders by user and status
     */
    List<Order> findByUserIdAndStatus(Long userId, OrderStatus status);

    /**
     * Find orders by user and payment status
     */
    List<Order> findByUserIdAndPaymentStatus(Long userId, PaymentStatus paymentStatus);

    /**
     * Find orders created after a specific date
     */
    List<Order> findByCreatedAtAfter(LocalDateTime date);

    /**
     * Find orders created between dates
     */
    List<Order> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Find orders by total amount range
     */
    List<Order> findByTotalAmountBetween(BigDecimal minAmount, BigDecimal maxAmount);

    /**
     * Find orders by shipping city
     */
    List<Order> findByShippingCity(String city);

    /**
     * Find orders by shipping state
     */
    List<Order> findByShippingState(String state);

    /**
     * Find orders with tracking number
     */
    @Query("SELECT o FROM Order o WHERE o.trackingNumber IS NOT NULL AND o.trackingNumber != ''")
    List<Order> findOrdersWithTracking();

    /**
     * Find orders without tracking number
     */
    @Query("SELECT o FROM Order o WHERE o.trackingNumber IS NULL OR o.trackingNumber = ''")
    List<Order> findOrdersWithoutTracking();

    /**
     * Find orders by promo code
     */
    List<Order> findByPromoCode(String promoCode);

    /**
     * Find orders with pagination
     */
    Page<Order> findAll(Pageable pageable);

    /**
     * Find orders by user with pagination
     */
    Page<Order> findByUserId(Long userId, Pageable pageable);

    /**
     * Find orders by status with pagination
     */
    Page<Order> findByStatus(OrderStatus status, Pageable pageable);

    /**
     * Find orders by payment status with pagination
     */
    Page<Order> findByPaymentStatus(PaymentStatus paymentStatus, Pageable pageable);

    /**
     * Find orders by user and status with pagination
     */
    Page<Order> findByUserIdAndStatus(Long userId, OrderStatus status, Pageable pageable);

    /**
     * Find orders created between dates with pagination
     */
    Page<Order> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);

    /**
     * Count orders by status
     */
    long countByStatus(OrderStatus status);

    /**
     * Count orders by payment status
     */
    long countByPaymentStatus(PaymentStatus paymentStatus);

    /**
     * Count orders by user
     */
    long countByUserId(Long userId);

    /**
     * Count orders created in a specific month
     */
    @Query("SELECT COUNT(o) FROM Order o WHERE YEAR(o.createdAt) = :year AND MONTH(o.createdAt) = :month")
    long countByCreatedYearAndMonth(@Param("year") int year, @Param("month") int month);

    /**
     * Calculate total revenue
     */
    @Query("SELECT SUM(o.totalAmount) FROM Order o WHERE o.paymentStatus = 'PAID'")
    BigDecimal calculateTotalRevenue();

    /**
     * Calculate total revenue for a date range
     */
    @Query("SELECT SUM(o.totalAmount) FROM Order o WHERE o.paymentStatus = 'PAID' " +
           "AND o.createdAt BETWEEN :startDate AND :endDate")
    BigDecimal calculateTotalRevenueBetween(@Param("startDate") LocalDateTime startDate, 
                                           @Param("endDate") LocalDateTime endDate);

    /**
     * Find recent orders
     */
    @Query("SELECT o FROM Order o ORDER BY o.createdAt DESC")
    List<Order> findRecentOrders(Pageable pageable);

    /**
     * Find orders by delivery date range
     */
    @Query("SELECT o FROM Order o WHERE o.estimatedDelivery BETWEEN :startDate AND :endDate")
    List<Order> findByEstimatedDeliveryBetween(@Param("startDate") LocalDateTime startDate, 
                                              @Param("endDate") LocalDateTime endDate);

    /**
     * Find delivered orders
     */
    @Query("SELECT o FROM Order o WHERE o.deliveredAt IS NOT NULL")
    List<Order> findDeliveredOrders();

    /**
     * Find orders that need tracking updates
     */
    @Query("SELECT o FROM Order o WHERE o.status IN ('SHIPPED', 'OUT_FOR_DELIVERY') " +
           "AND o.estimatedDelivery <= :currentDate")
    List<Order> findOrdersNeedingTrackingUpdate(@Param("currentDate") LocalDateTime currentDate);

    /**
     * Find high-value orders
     */
    @Query("SELECT o FROM Order o WHERE o.totalAmount >= :minAmount ORDER BY o.totalAmount DESC")
    List<Order> findHighValueOrders(@Param("minAmount") BigDecimal minAmount, Pageable pageable);
} 