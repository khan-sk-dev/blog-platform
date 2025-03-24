package com.example.blog.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.blog.entity.Post;
import com.example.blog.repository.PostRepository;

/**
 * Unit tests for {@link PostService} using JUnit 5 and Mockito.
 * <p>
 * Ensures all service methods function correctly with various inputs,
 * handling both valid and invalid cases.
 * </p>
 */
@ExtendWith(MockitoExtension.class)
class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private PostService postService;

    private Post post;

    /**
     * Initializes test data before each test case.
     */
    @BeforeEach
    void setUp() {
        post = new Post();
        post.setId(1L);
        post.setTitle("Test Title");
        post.setContent("Test Content");
    }

    /**
     * Tests retrieval of all posts.
     * Expected: Should return a list containing all posts.
     */
    @Test
    void testGetAllPosts() {
        List<Post> mockPosts = Arrays.asList(post);
        when(postRepository.findAll()).thenReturn(mockPosts);

        List<Post> result = postService.getAllPosts();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test Title", result.get(0).getTitle());
    }

    /**
     * Tests retrieval of a post by ID when it exists.
     * Expected: Should return the correct post.
     */
    @Test
    void testGetPostById_Exists() {
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));

        Post result = postService.getPostById(1L);
        assertNotNull(result);
        assertEquals("Test Title", result.getTitle());
    }

    /**
     * Tests retrieval of a post by ID when it does not exist.
     * Expected: Should return null.
     */
    @Test
    void testGetPostById_NotExists() {
        when(postRepository.findById(1L)).thenReturn(Optional.empty());

        Post result = postService.getPostById(1L);
        assertNull(result);
    }

    /**
     * Tests the creation of a new post.
     * Expected: Should return the saved post.
     */
    @Test
    void testCreatePost() {
        when(postRepository.save(post)).thenReturn(post);

        Post result = postService.createPost(post);
        assertNotNull(result);
        assertEquals("Test Title", result.getTitle());
    }

    /**
     * Tests updating an existing post.
     * Expected: Should return the updated post with new values.
     */
    @Test
    void testUpdatePost_Exists() {
        Post updatedPostDetails = new Post();
        updatedPostDetails.setTitle("Updated Title");
        updatedPostDetails.setContent("Updated Content");

        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(postRepository.save(any(Post.class))).thenReturn(updatedPostDetails);

        Post result = postService.updatePost(1L, updatedPostDetails);
        assertNotNull(result);
        assertEquals("Updated Title", result.getTitle());
    }

    /**
     * Tests updating a post that does not exist.
     * Expected: Should return null.
     */
    @Test
    void testUpdatePost_NotExists() {
        when(postRepository.findById(1L)).thenReturn(Optional.empty());

        Post result = postService.updatePost(1L, post);
        assertNull(result);
    }

    /**
     * Tests deleting a post.
     * Expected: Should invoke deleteById() method of repository once.
     */
    @Test
    void testDeletePost() {
        doNothing().when(postRepository).deleteById(1L);

        assertDoesNotThrow(() -> postService.deletePost(1L));
        verify(postRepository, times(1)).deleteById(1L);
    }
}
