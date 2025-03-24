package com.example.blog.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.example.blog.entity.Comment;
import com.example.blog.service.CommentService;

/**
 * Unit tests for {@link CommentController}.
 * <p>
 * This test class ensures that CommentController correctly handles HTTP
 * requests
 * and delegates the necessary business logic to CommentService.
 * </p>
 *
 * <p>
 * ✅ Method-Level Javadoc: Describes expected behavior and conditions for each
 * test case.<br>
 * ✅ Inline Comments: Clarifies test setup, assertions, and verification
 * logic.<br>
 * ✅ Pass/Fail Conditions: Clearly defined validation logic for each test.<br>
 * </p>
 *
 * <h3>Test Coverage:</h3>
 * <ul>
 * <li>Retrieving comments by post ID</li>
 * <li>Adding a comment successfully</li>
 * <li>Handling failed comment creation</li>
 * </ul>
 *
 * @author Your Name
 * @version 1.0
 */
@ExtendWith(MockitoExtension.class)
class CommentControllerTest {

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;

    private Comment sampleComment;

    /**
     * Initializes mock objects and sample data before each test.
     * <p>
     * Premise:
     * <ul>
     * <li>A mock {@code CommentService} is used since service logic is not being
     * tested.</li>
     * <li>A sample {@code Comment} object is created to simulate database
     * entries.</li>
     * </ul>
     * </p>
     */
    @BeforeEach
    void setUp() {
        sampleComment = new Comment();
        sampleComment.setId(1L);
        sampleComment.setContent("Sample comment");
    }

    /**
     * Tests retrieval of comments for a given post ID.
     * 
     * <p>
     * Expected Behavior:
     * </p>
     * <ul>
     * <li>Should return a list of comments belonging to the specified post.</li>
     * </ul>
     *
     * <p>
     * Pass Condition:
     * </p>
     * <ul>
     * <li>The returned list size matches the expected value.</li>
     * </ul>
     */
    @Test
    @DisplayName("Should return list of comments for given post ID")
    void testGetCommentsByPostId() {
        Long postId = 1L;
        List<Comment> expectedComments = Arrays.asList(sampleComment);

        when(commentService.getCommentsByPostId(postId)).thenReturn(expectedComments);

        List<Comment> actualComments = commentController.getCommentsByPostId(postId);

        assertEquals(expectedComments.size(), actualComments.size(),
                "Returned comment list size should match.");
    }

    /**
     * Tests adding a comment to a post.
     *
     * <p>
     * Expected Behavior:
     * </p>
     * <ul>
     * <li>Should return {@code ResponseEntity} with the created comment if
     * successful.</li>
     * <li>Should return {@code ResponseEntity.notFound()} if creation fails.</li>
     * </ul>
     */
    @Test
    @DisplayName("Should add a comment and return ResponseEntity")
    void testAddComment() {
        Long postId = 1L;
        when(commentService.addComment(postId,
                sampleComment)).thenReturn(sampleComment);

        ResponseEntity<Comment> response = commentController.addComment(postId,
                sampleComment);

        assertEquals(200, response.getStatusCodeValue(),
                "HTTP status should be 200 OK.");
        assertNotNull(response.getBody(),
                "Response body should contain created comment.");
    }

    /**
     * Tests adding a comment when the service returns null.
     *
     * <p>
     * Expected Behavior:
     * </p>
     * <ul>
     * <li>Should return HTTP 404 (Not Found).</li>
     * </ul>
     */
    @Test
    @DisplayName("Should return 404 NOT_FOUND when comment creation fails")
    void testAddCommentFailure() {
        Long postId = 1L;
        when(commentService.addComment(postId, sampleComment)).thenReturn(null);

        ResponseEntity<Comment> response = commentController.addComment(postId,
                sampleComment);

        assertEquals(404, response.getStatusCodeValue(),
                "HTTP status should be 404 NOT FOUND.");
    }
}
