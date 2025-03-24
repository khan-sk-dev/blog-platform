package com.example.blog.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit test for {@link Post} entity.
 * <p>
 * Ensures the correctness of entity behavior, including constructors,
 * getters, setters, and constraints.
 * </p>
 *
 * <h3>Test Coverage:</h3>
 * <ul>
 * <li>Constructor validation (with and without ID)</li>
 * <li>Getter and setter methods</li>
 * <li>Handling of empty and null comment lists</li>
 * </ul>
 *
 * <p>
 * ✅ Structured test cases for clarity.<br>
 * ✅ Ensures proper handling of edge cases.<br>
 * ✅ Verifies compliance with entity constraints.<br>
 * </p>
 */
class PostTest {

    private Post post;
    private List<Comment> comments;

    /**
     * Sets up a fresh Post instance before each test case.
     * <p>
     * - Creates an empty list of comments.<br>
     * - Initializes a {@code Post} instance with sample data.
     * </p>
     */
    @BeforeEach
    void setUp() {
        comments = new ArrayList<>();
        post = new Post(1L, "Sample Title", "Sample Content", comments);
    }

    /**
     * Tests the constructor that initializes all fields.
     * <p>
     * Expected Behavior:
     * </p>
     * <ul>
     * <li>Correct assignment of ID, title, and content.</li>
     * <li>Non-null comment list with zero initial size.</li>
     * </ul>
     */
    @Test
    void testConstructorWithAllFields() {
        assertEquals(1L, post.getId(), "ID should be correctly assigned");
        assertEquals("Sample Title", post.getTitle(), "Title should be correctly assigned");
        assertEquals("Sample Content", post.getContent(), "Content should be correctly assigned");
        assertNotNull(post.getComments(), "Comment list should not be null");
        assertEquals(0, post.getComments().size(), "Comment list should be empty initially");
    }

    /**
     * Tests the constructor that excludes the ID.
     * <p>
     * Expected Behavior:
     * </p>
     * <ul>
     * <li>ID should be null for newly created posts.</li>
     * <li>Title and content should be correctly assigned.</li>
     * <li>Comment list should not be null.</li>
     * </ul>
     */
    @Test
    void testConstructorWithoutId() {
        Post newPost = new Post("New Title", "New Content");
        assertNull(newPost.getId(), "ID should be null for new posts");
        assertEquals("New Title", newPost.getTitle(), "Title should be correctly assigned");
        assertEquals("New Content", newPost.getContent(), "Content should be correctly assigned");
        assertNotNull(newPost.getComments(), "Comment list should not be null");
        assertEquals(0, newPost.getComments().size(), "Comment list should be empty initially");
    }

    /**
     * Tests setter and getter for title.
     * <p>
     * Expected Behavior:
     * </p>
     * <ul>
     * <li>Title should be updated correctly.</li>
     * </ul>
     */
    @Test
    void testSetTitle() {
        post.setTitle("Updated Title");
        assertEquals("Updated Title", post.getTitle(), "Title should be updated");
    }

    /**
     * Tests setter and getter for content.
     * <p>
     * Expected Behavior:
     * </p>
     * <ul>
     * <li>Content should be updated correctly.</li>
     * </ul>
     */
    @Test
    void testSetContent() {
        post.setContent("Updated Content");
        assertEquals("Updated Content", post.getContent(), "Content should be updated");
    }

    /**
     * Tests setter and getter for comments.
     * <p>
     * Expected Behavior:
     * </p>
     * <ul>
     * <li>Comment list should not be null.</li>
     * <li>Size should reflect the number of comments added.</li>
     * </ul>
     */
    @Test
    void testSetComments() {
        List<Comment> newComments = new ArrayList<>();
        newComments.add(new Comment());
        post.setComments(newComments);
        assertNotNull(post.getComments(), "Comment list should not be null");
        assertEquals(1, post.getComments().size(), "Comment list should have one comment");
    }

    /**
     * Ensures the constructor handles a null comments list properly.
     * <p>
     * Expected Behavior:
     * </p>
     * <ul>
     * <li>Comment list should be initialized as empty.</li>
     * </ul>
     */
    @Test
    void testConstructorHandlesNullCommentList() {
        Post newPost = new Post(2L, "Another Title", "Another Content", null);
        assertNotNull(newPost.getComments(), "Comment list should be initialized as empty");
        assertEquals(0, newPost.getComments().size(), "Comment list should be empty");
    }
}
