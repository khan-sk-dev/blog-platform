package com.example.blog.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.blog.entity.Comment;
import com.example.blog.entity.Post;
import com.example.blog.repository.CommentRepository;
import com.example.blog.repository.PostRepository;

/**
 * Unit test class for {@link CommentService}.
 * <p>
 * This test class ensures that the comment retrieval and creation logic in
 * {@link CommentService} functions as expected. It uses JUnit 5 and Mockito for
 * dependency mocking.
 * </p>
 *
 * <h3>Test Coverage:</h3>
 * <ul>
 *     <li>Retrieving comments by post ID</li>
 *     <li>Adding a comment to an existing post</li>
 *     <li>Handling cases where the post does not exist</li>
 * </ul>
 *
 * <p>
 *  Uses Mockito for dependency isolation.<br>
 *  Ensures service behavior aligns with business logic.<br>
 *  Verifies method calls and prevents unintended interactions.<br>
 * </p>
 */
@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private CommentService commentService;

    private Post post;
    private Comment comment1;
    private Comment comment2;

    /**
     * Sets up test data before each test case execution.
     * <p>
     * Premise:
     * <ul>
     *     <li>A sample post is created with two associated comments.</li>
     *     <li>This ensures a consistent test environment across all cases.</li>
     * </ul>
     * </p>
     */
    @BeforeEach
    void setUp() {
        post = new Post();
        post.setId(1L);

        comment1 = new Comment();
        comment1.setId(1L);
        comment1.setContent("First comment");
        comment1.setPost(post);

        comment2 = new Comment();
        comment2.setId(2L);
        comment2.setContent("Second comment");
        comment2.setPost(post);
    }

    /**
     * Tests the retrieval of comments for a given post ID.
     * <p>
     * Expected Behavior:
     * </p>
     * <ul>
     *     <li>Should return a list of comments associated with the post.</li>
     *     <li>The repository method {@code findByPostId()} should be called exactly once.</li>
     * </ul>
     */
    @Test
    void testGetCommentsByPostId() {
        when(commentRepository.findByPostId(1L)).thenReturn(Arrays.asList(comment1, comment2));
        List<Comment> comments = commentService.getCommentsByPostId(1L);

        assertNotNull(comments); // Ensures the returned list is not null
        assertEquals(2, comments.size()); // Verifies that two comments are returned
        verify(commentRepository, times(1)).findByPostId(1L); // Ensures method call occurred once
    }

    /**
     * Tests the successful addition of a comment to an existing post.
     * <p>
     * Expected Behavior:
     * </p>
     * <ul>
     *     <li>The comment should be saved and returned.</li>
     *     <li>The repository methods {@code findById()} and {@code save()} should be called.</li>
     * </ul>
     */
    @Test
    void testAddComment_Success() {
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(commentRepository.save(any(Comment.class))).thenReturn(comment1);

        Comment savedComment = commentService.addComment(1L, comment1);

        assertNotNull(savedComment); // Ensures a comment is successfully saved
        assertEquals("First comment", savedComment.getContent()); // Verifies content correctness
        verify(postRepository, times(1)).findById(1L); // Ensures post lookup occurred
        verify(commentRepository, times(1)).save(comment1); // Ensures comment was saved
    }

    /**
     * Tests adding a comment to a non-existent post.
     * <p>
     * Expected Behavior:
     * </p>
     * <ul>
     *     <li>Should return {@code null} if the post does not exist.</li>
     *     <li>The repository method {@code findById()} should be called.</li>
     *     <li>The repository method {@code save()} should never be called.</li>
     * </ul>
     */
    @Test
    void testAddComment_PostNotFound() {
        when(postRepository.findById(1L)).thenReturn(Optional.empty());

        Comment savedComment = commentService.addComment(1L, comment1);

        assertNull(savedComment); // Ensures null is returned for a non-existent post
        verify(postRepository, times(1)).findById(1L); // Ensures post lookup was attempted
        verify(commentRepository, never()).save(any(Comment.class)); // Ensures comment was NOT saved
    }
}
