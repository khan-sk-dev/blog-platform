package com.example.blog.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link Comment} entity.
 * Ensures that entity constraints, getters, and setters function as expected.
 */
class CommentTest {

    private Comment comment;
    private Post post;

    /**
     * Sets up test data before each test case.
     */
    @BeforeEach
    void setUp() {
        post = new Post(1L, "Sample Title", "Sample Content", null); // Mock Post instance
        comment = new Comment(1L, "This is a comment", post);
    }

    /**
     * Tests the constructor and getter methods of the Comment class.
     */
    @Test
    void testConstructorAndGetters() {
        assertEquals(1L, comment.getId());
        assertEquals("This is a comment", comment.getContent());
        assertEquals(post, comment.getPost());
    }

    /**
     * Tests the setter methods of the Comment class.
     */
    @Test
    void testSetters() {
        Post newPost = new Post(2L, "New Title", "New Content", null);
        comment.setId(2L);
        comment.setContent("Updated comment content");
        comment.setPost(newPost);

        assertEquals(2L, comment.getId());
        assertEquals("Updated comment content", comment.getContent());
        assertEquals(newPost, comment.getPost());
    }

    /**
     * Tests that setting content to null throws an exception.
     */
    @Test
    void testContentCannotBeNull() {
        Exception exception = assertThrows(NullPointerException.class, () -> comment.setContent(null));
        assertEquals("cannot set null content", exception.getMessage());
    }
}
