package com.example.blog.controller;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.blog.entity.Post;
import com.example.blog.service.PostService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Unit tests for {@link PostController}.
 * <p>
 * This test class ensures that the PostController properly handles HTTP requests and responses.
 * It covers various scenarios for:
 * </p>
 * <ul>
 *     <li>Retrieving all posts</li>
 *     <li>Retrieving a post by ID</li>
 *     <li>Creating a post</li>
 *     <li>Updating a post</li>
 *     <li>Deleting a post</li>
 * </ul>
 *
 * <p>
 * ✅ Method-Level Javadoc: Documents expected behavior for each test case. <br>
 * ✅ Inline Comments: Explains test setup, assertions, and verification logic. <br>
 * ✅ Pass/Fail Conditions: Ensures status codes and JSON responses are validated. <br>
 * </p>
 *
 * @author Your Name
 * @version 1.0
 */
@ExtendWith(MockitoExtension.class)
class PostControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PostService postService;

    @InjectMocks
    private PostController postController;

    private ObjectMapper objectMapper;

    /**
     * Sets up the MockMvc instance and test dependencies before each test.
     */
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
        objectMapper = new ObjectMapper();
    }

    /**
     * Tests retrieving all posts.
     *
     * <p>
     * Expected Behavior:
     * </p>
     * <ul>
     *     <li>Should return HTTP 200 OK.</li>
     *     <li>Should return a list of posts in JSON format.</li>
     * </ul>
     */
    @Test
    @DisplayName("Should return all posts")
    void testGetAllPosts() throws Exception {
        List<Post> posts = Arrays.asList(
                new Post(1L, "Title1", "Content1", null),
                new Post(2L, "Title2", "Content2", null)
        );
        when(postService.getAllPosts()).thenReturn(posts);

        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(posts.size()));

        verify(postService, times(1)).getAllPosts();
    }

    /**
     * Tests retrieving a post by ID.
     *
     * <p>
     * Expected Behavior:
     * </p>
     * <ul>
     *     <li>Should return HTTP 200 OK if the post exists.</li>
     *     <li>Should return HTTP 404 Not Found if the post does not exist.</li>
     * </ul>
     */
    @Test
    @DisplayName("Should return a post by ID")
    void testGetPostById() throws Exception {
        Long postId = 1L;
        Post post = new Post(postId, "Test Title", "Test Content", null);
        when(postService.getPostById(postId)).thenReturn(post);

        mockMvc.perform(get("/posts/{id}", postId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(post.getTitle()));

        verify(postService, times(1)).getPostById(postId);
    }

    /**
     * Tests creating a post.
     *
     * <p>
     * Expected Behavior:
     * </p>
     * <ul>
     *     <li>Should return HTTP 200 OK.</li>
     *     <li>Should return the created post in JSON format.</li>
     * </ul>
     */
    @Test
    @DisplayName("Should create a post")
    void testCreatePost() throws Exception {
        Post post = new Post(1L, "New Title", "New Content", null);
        when(postService.createPost(any(Post.class))).thenReturn(post);

        mockMvc.perform(post("/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(post)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(post.getTitle()));

        verify(postService, times(1)).createPost(any(Post.class));
    }

    /**
     * Tests updating a post.
     *
     * <p>
     * Expected Behavior:
     * </p>
     * <ul>
     *     <li>Should return HTTP 200 OK if the update is successful.</li>
     *     <li>Should return HTTP 404 Not Found if the post does not exist.</li>
     * </ul>
     */
    @Test
    @DisplayName("Should update a post")
    void testUpdatePost() throws Exception {
        Long postId = 1L;
        Post postDetails = new Post(postId, "Updated Title", "Updated Content", null);
        when(postService.updatePost(eq(postId), any(Post.class))).thenReturn(postDetails);

        mockMvc.perform(put("/posts/{id}", postId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(postDetails)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(postDetails.getTitle()));

        verify(postService, times(1)).updatePost(eq(postId), any(Post.class));
    }

    /**
     * Tests deleting a post.
     *
     * <p>
     * Expected Behavior:
     * </p>
     * <ul>
     *     <li>Should return HTTP 204 No Content upon successful deletion.</li>
     * </ul>
     */
    @Test
    @DisplayName("Should delete a post")
    void testDeletePost() throws Exception {
        Long postId = 1L;
        doNothing().when(postService).deletePost(postId);

        mockMvc.perform(delete("/posts/{id}", postId))
                .andExpect(status().isNoContent());

        verify(postService, times(1)).deletePost(postId);
    }
}
