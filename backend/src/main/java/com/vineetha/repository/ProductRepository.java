package com.vineetha.repository;

import com.vineetha.model.Product;
import com.vineetha.model.ProductCategory;
import com.vineetha.model.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Product entity
 * 
 * @author Vinish
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Find product by SKU
     */
    Optional<Product> findBySku(String sku);

    /**
     * Check if product exists by SKU
     */
    boolean existsBySku(String sku);

    /**
     * Find products by category
     */
    List<Product> findByCategory(ProductCategory category);

    /**
     * Find products by status
     */
    List<Product> findByStatus(ProductStatus status);

    /**
     * Find products by category and status
     */
    List<Product> findByCategoryAndStatus(ProductCategory category, ProductStatus status);

    /**
     * Find products by brand
     */
    List<Product> findByBrand(String brand);

    /**
     * Find products by price range
     */
    List<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);

    /**
     * Find products by category and price range
     */
    List<Product> findByCategoryAndPriceBetween(ProductCategory category, BigDecimal minPrice, BigDecimal maxPrice);

    /**
     * Find featured products
     */
    List<Product> findByFeaturedTrue();

    /**
     * Find bestseller products
     */
    List<Product> findByBestsellerTrue();

    /**
     * Find products in stock
     */
    @Query("SELECT p FROM Product p WHERE p.stockQuantity > 0")
    List<Product> findInStockProducts();

    /**
     * Find products out of stock
     */
    @Query("SELECT p FROM Product p WHERE p.stockQuantity = 0")
    List<Product> findOutOfStockProducts();

    /**
     * Find products with low stock
     */
    @Query("SELECT p FROM Product p WHERE p.stockQuantity > 0 AND p.stockQuantity <= :threshold")
    List<Product> findLowStockProducts(@Param("threshold") Integer threshold);

    /**
     * Find products by rating
     */
    @Query("SELECT p FROM Product p WHERE p.rating >= :minRating")
    List<Product> findByRatingGreaterThanEqual(@Param("minRating") BigDecimal minRating);

    /**
     * Find products by name pattern
     */
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')) " +
           "OR LOWER(p.description) LIKE LOWER(CONCAT('%', :name, '%')) " +
           "OR LOWER(p.brand) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Product> findByNameOrDescriptionOrBrandContainingIgnoreCase(@Param("name") String name);

    /**
     * Find products on sale
     */
    @Query("SELECT p FROM Product p WHERE p.originalPrice IS NOT NULL AND p.originalPrice > p.price")
    List<Product> findOnSaleProducts();

    /**
     * Find products with pagination
     */
    Page<Product> findAll(Pageable pageable);

    /**
     * Find products by category with pagination
     */
    Page<Product> findByCategory(ProductCategory category, Pageable pageable);

    /**
     * Find products by status with pagination
     */
    Page<Product> findByStatus(ProductStatus status, Pageable pageable);

    /**
     * Find products by category and status with pagination
     */
    Page<Product> findByCategoryAndStatus(ProductCategory category, ProductStatus status, Pageable pageable);

    /**
     * Find products by price range with pagination
     */
    Page<Product> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);

    /**
     * Find products by name pattern with pagination
     */
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')) " +
           "OR LOWER(p.description) LIKE LOWER(CONCAT('%', :name, '%')) " +
           "OR LOWER(p.brand) LIKE LOWER(CONCAT('%', :name, '%'))")
    Page<Product> findByNameOrDescriptionOrBrandContainingIgnoreCase(@Param("name") String name, Pageable pageable);

    /**
     * Search products with pagination
     */
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :query, '%')) " +
           "OR LOWER(p.description) LIKE LOWER(CONCAT('%', :query, '%')) " +
           "OR LOWER(p.brand) LIKE LOWER(CONCAT('%', :query, '%'))")
    Page<Product> searchProducts(@Param("query") String query, Pageable pageable);

    /**
     * Find products by price range and status with pagination
     */
    Page<Product> findByPriceBetweenAndStatus(BigDecimal minPrice, BigDecimal maxPrice, ProductStatus status, Pageable pageable);

    /**
     * Find products by brand and status with pagination
     */
    Page<Product> findByBrandAndStatus(String brand, ProductStatus status, Pageable pageable);

    /**
     * Find products with discount and status
     */
    Page<Product> findByDiscountPercentageGreaterThanAndStatus(BigDecimal discountPercentage, ProductStatus status, Pageable pageable);

    /**
     * Find featured products by status
     */
    List<Product> findByFeaturedTrueAndStatus(ProductStatus status);

    /**
     * Find best selling products
     */
    @Query("SELECT p FROM Product p ORDER BY p.soldCount DESC")
    List<Product> findBestSellingProducts();

    /**
     * Find new arrivals
     */
    @Query("SELECT p FROM Product p ORDER BY p.createdAt DESC")
    List<Product> findNewArrivals();

    /**
     * Find products by stock quantity less than or equal
     */
    List<Product> findByStockQuantityLessThanEqual(int quantity);

    /**
     * Count featured products
     */
    long countByFeaturedTrue();

    /**
     * Find products by multiple categories and status
     */
    Page<Product> findByCategoryInAndStatus(List<ProductCategory> categories, ProductStatus status, Pageable pageable);

    /**
     * Find products by status ordered by price ascending
     */
    Page<Product> findByStatusOrderByPriceAsc(ProductStatus status, Pageable pageable);

    /**
     * Find products by status ordered by price descending
     */
    Page<Product> findByStatusOrderByPriceDesc(ProductStatus status, Pageable pageable);

    /**
     * Find products by status ordered by average rating descending
     */
    @Query("SELECT p FROM Product p WHERE p.status = :status ORDER BY p.rating DESC")
    Page<Product> findByStatusOrderByAverageRatingDesc(@Param("status") ProductStatus status, Pageable pageable);

    /**
     * Find products by status ordered by creation date descending
     */
    Page<Product> findByStatusOrderByCreatedAtDesc(ProductStatus status, Pageable pageable);

    /**
     * Count products by category
     */
    long countByCategory(ProductCategory category);

    /**
     * Count products by status
     */
    long countByStatus(ProductStatus status);

    /**
     * Count products in stock
     */
    @Query("SELECT COUNT(p) FROM Product p WHERE p.stockQuantity > 0")
    long countInStockProducts();

    /**
     * Count products out of stock
     */
    @Query("SELECT COUNT(p) FROM Product p WHERE p.stockQuantity = 0")
    long countOutOfStockProducts();

    /**
     * Find top selling products
     */
    @Query("SELECT p FROM Product p ORDER BY p.soldCount DESC")
    List<Product> findTopSellingProducts(Pageable pageable);

    /**
     * Find products by tags
     */
    @Query("SELECT p FROM Product p WHERE p.tags LIKE CONCAT('%', :tag, '%')")
    List<Product> findByTagsContaining(@Param("tag") String tag);

    /**
     * Find products with highest ratings
     */
    @Query("SELECT p FROM Product p WHERE p.rating > 0 ORDER BY p.rating DESC")
    List<Product> findTopRatedProducts(Pageable pageable);

    /**
     * Find products with most reviews
     */
    @Query("SELECT p FROM Product p WHERE p.reviewCount > 0 ORDER BY p.reviewCount DESC")
    List<Product> findMostReviewedProducts(Pageable pageable);
} 