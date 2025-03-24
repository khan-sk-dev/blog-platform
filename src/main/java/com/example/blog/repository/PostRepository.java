package com.example.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog.entity.Post;

/**
 * Repository interface for managing {@link Post} entities.
 * <p>
 * This interface extends {@link JpaRepository} to provide CRUD operations 
 * and additional query methods for the Post entity.
 * </p>
 * <p>
 * Spring Data JPA will automatically generate the required implementation at runtime.
 * </p>
 *
 * @author Your Name
 * @version 1.0
 */
public interface PostRepository extends JpaRepository<Post, Long> {
    // JpaRepository provides built-in CRUD operations for Post entities.
    // Additional custom queries can be defined here if needed.
}
