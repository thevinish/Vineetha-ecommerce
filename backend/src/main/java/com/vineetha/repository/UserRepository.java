package com.vineetha.repository;

import com.vineetha.model.User;
import com.vineetha.model.UserRole;
import com.vineetha.model.UserStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for User entity
 * 
 * @author Vinish
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find user by email
     */
    Optional<User> findByEmail(String email);

    /**
     * Check if user exists by email
     */
    boolean existsByEmail(String email);

    /**
     * Find users by role
     */
    List<User> findByRole(UserRole role);

    /**
     * Find users by status
     */
    List<User> findByStatus(UserStatus status);

    /**
     * Find users by role and status
     */
    List<User> findByRoleAndStatus(UserRole role, UserStatus status);

    /**
     * Find users created after a specific date
     */
    List<User> findByCreatedAtAfter(LocalDateTime date);

    /**
     * Find users by city
     */
    List<User> findByCity(String city);

    /**
     * Find users by state
     */
    List<User> findByState(String state);

    /**
     * Find users who subscribed to newsletter
     */
    List<User> findByNewsletterSubscribedTrue();

    /**
     * Find users with verified email
     */
    List<User> findByEmailVerifiedTrue();

    /**
     * Find users by last login date range
     */
    @Query("SELECT u FROM User u WHERE u.lastLogin BETWEEN :startDate AND :endDate")
    List<User> findByLastLoginBetween(@Param("startDate") LocalDateTime startDate, 
                                     @Param("endDate") LocalDateTime endDate);

    /**
     * Find users by name pattern (first name or last name)
     */
    @Query("SELECT u FROM User u WHERE LOWER(u.firstName) LIKE LOWER(CONCAT('%', :name, '%')) " +
           "OR LOWER(u.lastName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<User> findByNameContainingIgnoreCase(@Param("name") String name);

    /**
     * Find users with pagination
     */
    Page<User> findAll(Pageable pageable);

    /**
     * Find users by role with pagination
     */
    Page<User> findByRole(UserRole role, Pageable pageable);

    /**
     * Find users by status with pagination
     */
    Page<User> findByStatus(UserStatus status, Pageable pageable);

    /**
     * Count users by role
     */
    long countByRole(UserRole role);

    /**
     * Count users by status
     */
    long countByStatus(UserStatus status);

    /**
     * Count users created in a specific month
     */
    @Query("SELECT COUNT(u) FROM User u WHERE YEAR(u.createdAt) = :year AND MONTH(u.createdAt) = :month")
    long countByCreatedYearAndMonth(@Param("year") int year, @Param("month") int month);

    /**
     * Find users who haven't logged in for a while
     */
    @Query("SELECT u FROM User u WHERE u.lastLogin < :date OR u.lastLogin IS NULL")
    List<User> findInactiveUsers(@Param("date") LocalDateTime date);

    /**
     * Find top users by order count
     */
    @Query("SELECT u FROM User u JOIN u.orders o GROUP BY u ORDER BY COUNT(o) DESC")
    List<User> findTopUsersByOrderCount(Pageable pageable);
} 