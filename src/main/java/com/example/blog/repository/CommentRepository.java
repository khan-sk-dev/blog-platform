package com.example.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blog.entity.Comment;

/**
 * Repository interface for managing {@link Comment} entities.
 * <p>
 * This interface extends {@link JpaRepository} to provide CRUD operations
 * and additional query methods for the Comment entity.
 * </p>
 * <p>
 * Spring Data JPA will automatically generate the required implementation at
 * runtime.
 * </p>
 *
 * @author Your Name
 * @version 1.0
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * Retrieves a list of comments associated with a specific post.
     *
     * @param postId The ID of the post for which comments should be fetched.
     * @return A list of comments linked to the given post.
     */
    List<Comment> findByPostId(Long postId);
}
