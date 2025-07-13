package com.vineetha.repository;

import com.vineetha.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for CartItem entity
 * 
 * @author Vinish
 */
@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    /**
     * Find cart items by user
     */
    List<CartItem> findByUserId(Long userId);

    /**
     * Find cart items by user and saved for later status
     */
    List<CartItem> findByUserIdAndSavedForLater(Long userId, boolean savedForLater);

    /**
     * Find cart item by user and product
     */
    Optional<CartItem> findByUserIdAndProductId(Long userId, Long productId);

    /**
     * Check if cart item exists by user and product
     */
    boolean existsByUserIdAndProductId(Long userId, Long productId);

    /**
     * Find active cart items (not saved for later)
     */
    @Query("SELECT ci FROM CartItem ci WHERE ci.user.id = :userId AND ci.savedForLater = false")
    List<CartItem> findActiveCartItemsByUserId(@Param("userId") Long userId);

    /**
     * Find saved for later items
     */
    @Query("SELECT ci FROM CartItem ci WHERE ci.user.id = :userId AND ci.savedForLater = true")
    List<CartItem> findSavedForLaterItemsByUserId(@Param("userId") Long userId);

    /**
     * Count cart items by user
     */
    long countByUserId(Long userId);

    /**
     * Count active cart items by user
     */
    @Query("SELECT COUNT(ci) FROM CartItem ci WHERE ci.user.id = :userId AND ci.savedForLater = false")
    long countActiveCartItemsByUserId(@Param("userId") Long userId);

    /**
     * Count saved for later items by user
     */
    @Query("SELECT COUNT(ci) FROM CartItem ci WHERE ci.user.id = :userId AND ci.savedForLater = true")
    long countSavedForLaterItemsByUserId(@Param("userId") Long userId);

    /**
     * Delete cart items by user
     */
    void deleteByUserId(Long userId);

    /**
     * Delete cart items by user and product
     */
    void deleteByUserIdAndProductId(Long userId, Long productId);

    /**
     * Delete active cart items by user
     */
    @Query("DELETE FROM CartItem ci WHERE ci.user.id = :userId AND ci.savedForLater = false")
    void deleteActiveCartItemsByUserId(@Param("userId") Long userId);

    /**
     * Find cart items with low stock
     */
    @Query("SELECT ci FROM CartItem ci WHERE ci.product.stockQuantity < ci.quantity")
    List<CartItem> findCartItemsWithLowStock();

    /**
     * Find cart items by product
     */
    List<CartItem> findByProductId(Long productId);

    /**
     * Find cart items created after a specific date
     */
    @Query("SELECT ci FROM CartItem ci WHERE ci.createdAt > :date")
    List<CartItem> findByCreatedAtAfter(@Param("date") java.time.LocalDateTime date);

    /**
     * Find cart item by ID and user ID
     */
    @Query("SELECT ci FROM CartItem ci WHERE ci.id = :cartItemId AND ci.user.id = :userId")
    Optional<CartItem> findByIdAndUserId(@Param("cartItemId") Long cartItemId, @Param("userId") Long userId);

    /**
     * Find cart items by user ordered by creation date
     */
    List<CartItem> findByUserOrderByCreatedAtDesc(com.vineetha.model.User user);

    /**
     * Find cart items by user
     */
    List<CartItem> findByUser(com.vineetha.model.User user);

    /**
     * Find cart items by user and saved for later
     */
    List<CartItem> findByUserAndSavedForLaterTrue(com.vineetha.model.User user);

    /**
     * Find cart item by user and product
     */
    Optional<CartItem> findByUserAndProduct(com.vineetha.model.User user, com.vineetha.model.Product product);

    /**
     * Count cart items by user and saved for later status
     */
    long countByUserAndSavedForLaterFalse(com.vineetha.model.User user);

    /**
     * Count cart items by saved for later status
     */
    long countBySavedForLaterFalse();

    /**
     * Count cart items by saved for later status
     */
    long countBySavedForLaterTrue();
} 