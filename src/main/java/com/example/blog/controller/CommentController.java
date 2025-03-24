package com.example.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.entity.Comment;
import com.example.blog.service.CommentService;



/**
 * REST Controller for handling comment-related operations.
 * Provides endpoints to fetch and add comments associated with a blog post.
 *
 * <p>
 * This controller maps requests to "posts/{postId}/comments" and interacts with
 * the {@link CommentService} to manage comment data.
 * </p>
 *
 * <p>
 * <b>Performance:</b> Optimized for retrieving and adding comments efficiently.
 * </p>
 *
 * @author Sohaib Khan
 */
@RestController
@RequestMapping("/posts/{postId}/comments")
public class CommentController {

    /**
     * Service layer dependency for handling comment-related business logic.
     */
    @Autowired
    private CommentService commentService;

    /**
     * Retrieves all comments associated with a specific post.
     *
     * @param postId The unique identifier of the post.
     * @return List of comments belonging to the specified post.
     * @throws IllegalArgumentException if postId is null or invalid.
     *
     *                                  <p>
     *                                  <b>Time Complexity:</b> O(n), where n is the
     *                                  number of comments for the given post.
     *                                  </p>
     */
    @GetMapping
    public List<Comment> getCommentsByPostId(@PathVariable Long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    /**
     * Adds a new comment to a specific post.
     *
     * @param postId  The unique identifier of the post.
     * @param comment The comment entity containing the content.
     * @return ResponseEntity containing the created comment or a 404 Not Found if
     *         the post does not exist.
     *
     *         <p>
     *         <b>Constraints:</b>
     *         - Comment content must be between 1 and 500 characters.
     *         - postId must be a valid existing post.
     *         </p>
     *
     *         <p>
     *         <b>Time Complexity:</b> O(1), since inserting a comment is a direct
     *         database operation.
     *         </p>
     */
    @PostMapping
    public ResponseEntity<Comment> addComment(@PathVariable Long postId, @RequestBody Comment comment) {
        Comment createdComment = commentService.addComment(postId, comment);
        return createdComment != null ? ResponseEntity.ok(createdComment) : ResponseEntity.notFound().build();
    }
}
