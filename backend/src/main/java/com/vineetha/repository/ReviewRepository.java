package com.vineetha.repository;

import com.vineetha.model.Review;
import com.vineetha.model.ReviewStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Repository interface for Review entity
 * 
 * @author Vinish
 */
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    /**
     * Find reviews by product
     */
    List<Review> findByProductId(Long productId);

    /**
     * Find reviews by user
     */
    List<Review> findByUserId(Long userId);

    /**
     * Find reviews by status
     */
    List<Review> findByStatus(ReviewStatus status);

    /**
     * Find reviews by product and status
     */
    List<Review> findByProductIdAndStatus(Long productId, ReviewStatus status);

    /**
     * Find reviews by user and status
     */
    List<Review> findByUserIdAndStatus(Long userId, ReviewStatus status);

    /**
     * Find reviews by rating
     */
    List<Review> findByRating(Integer rating);

    /**
     * Find reviews by product and rating
     */
    List<Review> findByProductIdAndRating(Long productId, Integer rating);

    /**
     * Find verified purchase reviews
     */
    List<Review> findByVerifiedPurchaseTrue();

    /**
     * Find verified purchase reviews by product
     */
    List<Review> findByProductIdAndVerifiedPurchaseTrue(Long productId);

    /**
     * Find reviews with pagination
     */
    Page<Review> findAll(Pageable pageable);

    /**
     * Find reviews by product with pagination
     */
    Page<Review> findByProductId(Long productId, Pageable pageable);

    /**
     * Find reviews by status with pagination
     */
    Page<Review> findByStatus(ReviewStatus status, Pageable pageable);

    /**
     * Find reviews by product and status with pagination
     */
    Page<Review> findByProductIdAndStatus(Long productId, ReviewStatus status, Pageable pageable);

    /**
     * Find reviews by rating with pagination
     */
    Page<Review> findByRating(Integer rating, Pageable pageable);

    /**
     * Find reviews by product and rating with pagination
     */
    Page<Review> findByProductIdAndRating(Long productId, Integer rating, Pageable pageable);

    /**
     * Count reviews by product
     */
    long countByProductId(Long productId);

    /**
     * Count reviews by status
     */
    long countByStatus(ReviewStatus status);

    /**
     * Count reviews by product and status
     */
    long countByProductIdAndStatus(Long productId, ReviewStatus status);

    /**
     * Count reviews by rating
     */
    long countByRating(Integer rating);

    /**
     * Count reviews by product and rating
     */
    long countByProductIdAndRating(Long productId, Integer rating);

    /**
     * Count verified purchase reviews by product
     */
    long countByProductIdAndVerifiedPurchaseTrue(Long productId);

    /**
     * Calculate average rating by product
     */
    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.product.id = :productId AND r.status = 'APPROVED'")
    BigDecimal calculateAverageRatingByProduct(@Param("productId") Long productId);

    /**
     * Find top rated reviews
     */
    @Query("SELECT r FROM Review r WHERE r.status = 'APPROVED' ORDER BY r.rating DESC, r.helpfulVotes DESC")
    List<Review> findTopRatedReviews(Pageable pageable);

    /**
     * Find most helpful reviews
     */
    @Query("SELECT r FROM Review r WHERE r.status = 'APPROVED' ORDER BY r.helpfulVotes DESC")
    List<Review> findMostHelpfulReviews(Pageable pageable);

    /**
     * Find recent reviews
     */
    @Query("SELECT r FROM Review r WHERE r.status = 'APPROVED' ORDER BY r.createdAt DESC")
    List<Review> findRecentReviews(Pageable pageable);

    /**
     * Find reviews by product ordered by helpful votes
     */
    @Query("SELECT r FROM Review r WHERE r.product.id = :productId AND r.status = 'APPROVED' " +
           "ORDER BY r.helpfulVotes DESC, r.createdAt DESC")
    List<Review> findByProductIdOrderByHelpfulVotesDesc(@Param("productId") Long productId, Pageable pageable);

    /**
     * Find reviews by product ordered by rating
     */
    @Query("SELECT r FROM Review r WHERE r.product.id = :productId AND r.status = 'APPROVED' " +
           "ORDER BY r.rating DESC, r.createdAt DESC")
    List<Review> findByProductIdOrderByRatingDesc(@Param("productId") Long productId, Pageable pageable);

    /**
     * Find reviews by product ordered by date
     */
    @Query("SELECT r FROM Review r WHERE r.product.id = :productId AND r.status = 'APPROVED' " +
           "ORDER BY r.createdAt DESC")
    List<Review> findByProductIdOrderByCreatedAtDesc(@Param("productId") Long productId, Pageable pageable);

    /**
     * Find pending reviews
     */
    @Query("SELECT r FROM Review r WHERE r.status = 'PENDING' ORDER BY r.createdAt ASC")
    List<Review> findPendingReviews(Pageable pageable);

    /**
     * Find reviews that need moderation
     */
    @Query("SELECT r FROM Review r WHERE r.status IN ('PENDING', 'SPAM') ORDER BY r.createdAt ASC")
    List<Review> findReviewsNeedingModeration(Pageable pageable);

    /**
     * Find reviews by user and product
     */
    @Query("SELECT r FROM Review r WHERE r.user.id = :userId AND r.product.id = :productId")
    List<Review> findByUserIdAndProductId(@Param("userId") Long userId, @Param("productId") Long productId);

    /**
     * Check if user has reviewed product
     */
    @Query("SELECT COUNT(r) > 0 FROM Review r WHERE r.user.id = :userId AND r.product.id = :productId")
    boolean existsByUserIdAndProductId(@Param("userId") Long userId, @Param("productId") Long productId);
} 