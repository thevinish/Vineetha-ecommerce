package com.vineetha.service;

import com.vineetha.model.Product;
import com.vineetha.model.ProductCategory;
import com.vineetha.model.ProductStatus;
import com.vineetha.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Create a new product
     */
    public Product createProduct(Product product) {
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        product.setStatus(ProductStatus.ACTIVE);
        
        return productRepository.save(product);
    }

    /**
     * Get product by ID
     */
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    /**
     * Get all products with pagination
     */
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findByStatus(ProductStatus.ACTIVE, pageable);
    }

    /**
     * Get products by category
     */
    public Page<Product> getProductsByCategory(ProductCategory category, Pageable pageable) {
        return productRepository.findByCategoryAndStatus(category, ProductStatus.ACTIVE, pageable);
    }

    /**
     * Search products by name or description
     */
    public Page<Product> searchProducts(String query, Pageable pageable) {
        return productRepository.searchProducts(query, pageable);
    }

    /**
     * Get products by price range
     */
    public Page<Product> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable) {
        return productRepository.findByPriceBetweenAndStatus(minPrice, maxPrice, ProductStatus.ACTIVE, pageable);
    }

    /**
     * Get products by brand
     */
    public Page<Product> getProductsByBrand(String brand, Pageable pageable) {
        return productRepository.findByBrandAndStatus(brand, ProductStatus.ACTIVE, pageable);
    }

    /**
     * Get products with discount
     */
    public Page<Product> getProductsWithDiscount(Pageable pageable) {
        return productRepository.findByDiscountPercentageGreaterThanAndStatus(BigDecimal.ZERO, ProductStatus.ACTIVE, pageable);
    }

    /**
     * Get featured products
     */
    public List<Product> getFeaturedProducts() {
        return productRepository.findByFeaturedTrueAndStatus(ProductStatus.ACTIVE);
    }

    /**
     * Get best selling products
     */
    public List<Product> getBestSellingProducts() {
        return productRepository.findBestSellingProducts();
    }

    /**
     * Get new arrivals
     */
    public List<Product> getNewArrivals() {
        return productRepository.findNewArrivals();
    }

    /**
     * Update product
     */
    public Product updateProduct(Long id, Product updatedProduct) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Update fields
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setOriginalPrice(updatedProduct.getOriginalPrice());
        existingProduct.setDiscountPercentage(updatedProduct.getDiscountPercentage());
        existingProduct.setCategory(updatedProduct.getCategory());
        existingProduct.setBrand(updatedProduct.getBrand());
        existingProduct.setStockQuantity(updatedProduct.getStockQuantity());
        existingProduct.setStatus(updatedProduct.getStatus());
        existingProduct.setFeatured(updatedProduct.isFeatured());
        existingProduct.setUpdatedAt(LocalDateTime.now());

        return productRepository.save(existingProduct);
    }

    /**
     * Update product stock
     */
    public Product updateProductStock(Long id, int quantity) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setStockQuantity(quantity);
        product.setUpdatedAt(LocalDateTime.now());

        // Update status based on stock
        if (quantity <= 0) {
            product.setStatus(ProductStatus.OUT_OF_STOCK);
        } else if (product.getStatus() == ProductStatus.OUT_OF_STOCK) {
            product.setStatus(ProductStatus.ACTIVE);
        }

        return productRepository.save(product);
    }

    /**
     * Decrease product stock (for orders)
     */
    public Product decreaseStock(Long id, int quantity) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getStockQuantity() < quantity) {
            throw new RuntimeException("Insufficient stock");
        }

        product.setStockQuantity(product.getStockQuantity() - quantity);
        product.setUpdatedAt(LocalDateTime.now());

        // Update status if out of stock
        if (product.getStockQuantity() <= 0) {
            product.setStatus(ProductStatus.OUT_OF_STOCK);
        }

        return productRepository.save(product);
    }

    /**
     * Increase product stock
     */
    public Product increaseStock(Long id, int quantity) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setStockQuantity(product.getStockQuantity() + quantity);
        product.setUpdatedAt(LocalDateTime.now());

        // Update status if back in stock
        if (product.getStatus() == ProductStatus.OUT_OF_STOCK && product.getStockQuantity() > 0) {
            product.setStatus(ProductStatus.ACTIVE);
        }

        return productRepository.save(product);
    }

    /**
     * Update product status
     */
    public Product updateProductStatus(Long id, ProductStatus status) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setStatus(status);
        product.setUpdatedAt(LocalDateTime.now());

        return productRepository.save(product);
    }

    /**
     * Toggle product featured status
     */
    public Product toggleFeatured(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setFeatured(!product.isFeatured());
        product.setUpdatedAt(LocalDateTime.now());

        return productRepository.save(product);
    }

    /**
     * Delete product (soft delete)
     */
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setStatus(ProductStatus.DISCONTINUED);
        product.setUpdatedAt(LocalDateTime.now());
        productRepository.save(product);
    }

    /**
     * Get products by status (admin only)
     */
    public Page<Product> getProductsByStatus(ProductStatus status, Pageable pageable) {
        return productRepository.findByStatus(status, pageable);
    }

    /**
     * Get all products (admin only)
     */
    public Page<Product> getAllProductsAdmin(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    /**
     * Get low stock products (admin only)
     */
    public List<Product> getLowStockProducts(int threshold) {
        return productRepository.findByStockQuantityLessThanEqual(threshold);
    }

    /**
     * Get out of stock products (admin only)
     */
    public List<Product> getOutOfStockProducts() {
        return productRepository.findByStatus(ProductStatus.OUT_OF_STOCK);
    }

    /**
     * Get product statistics (admin only)
     */
    public ProductStatistics getProductStatistics() {
        long totalProducts = productRepository.count();
        long activeProducts = productRepository.countByStatus(ProductStatus.ACTIVE);
        long outOfStockProducts = productRepository.countByStatus(ProductStatus.OUT_OF_STOCK);
        long discontinuedProducts = productRepository.countByStatus(ProductStatus.DISCONTINUED);
        long featuredProducts = productRepository.countByFeaturedTrue();

        return new ProductStatistics(totalProducts, activeProducts, outOfStockProducts, discontinuedProducts, featuredProducts);
    }

    /**
     * Get products by multiple categories
     */
    public Page<Product> getProductsByCategories(List<ProductCategory> categories, Pageable pageable) {
        return productRepository.findByCategoryInAndStatus(categories, ProductStatus.ACTIVE, pageable);
    }

    /**
     * Get products sorted by price
     */
    public Page<Product> getProductsSortedByPrice(boolean ascending, Pageable pageable) {
        if (ascending) {
            return productRepository.findByStatusOrderByPriceAsc(ProductStatus.ACTIVE, pageable);
        } else {
            return productRepository.findByStatusOrderByPriceDesc(ProductStatus.ACTIVE, pageable);
        }
    }

    /**
     * Get products sorted by rating
     */
    public Page<Product> getProductsSortedByRating(Pageable pageable) {
        return productRepository.findByStatusOrderByAverageRatingDesc(ProductStatus.ACTIVE, pageable);
    }

    /**
     * Get products sorted by creation date
     */
    public Page<Product> getProductsSortedByDate(Pageable pageable) {
        return productRepository.findByStatusOrderByCreatedAtDesc(ProductStatus.ACTIVE, pageable);
    }

    // Statistics class
    public static class ProductStatistics {
        private final long totalProducts;
        private final long activeProducts;
        private final long outOfStockProducts;
        private final long discontinuedProducts;
        private final long featuredProducts;

        public ProductStatistics(long totalProducts, long activeProducts, long outOfStockProducts, 
                               long discontinuedProducts, long featuredProducts) {
            this.totalProducts = totalProducts;
            this.activeProducts = activeProducts;
            this.outOfStockProducts = outOfStockProducts;
            this.discontinuedProducts = discontinuedProducts;
            this.featuredProducts = featuredProducts;
        }

        // Getters
        public long getTotalProducts() { return totalProducts; }
        public long getActiveProducts() { return activeProducts; }
        public long getOutOfStockProducts() { return outOfStockProducts; }
        public long getDiscontinuedProducts() { return discontinuedProducts; }
        public long getFeaturedProducts() { return featuredProducts; }
    }
} 