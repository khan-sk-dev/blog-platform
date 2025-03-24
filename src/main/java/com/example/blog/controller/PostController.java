package com.example.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.entity.Post;
import com.example.blog.service.PostService;

/**
 * Controller for handling CRUD operations for blog posts.
 * Provides RESTful endpoints for creating, retrieving, updating, and deleting
 * blog posts.
 * Uses {@link PostService} to interact with the data layer.
 */
@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    /**
     * Retrieves all posts from the database.
     *
     * @return a list of {@link Post} objects.
     */
    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    /**
     * Retrieves a specific post by its ID.
     *
     * @param id The ID of the post to retrieve.
     * @return {@link ResponseEntity} containing the post if found, or 404 Not Found
     *         if the post does not exist.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Post post = postService.getPostById(id);
        return post != null ? ResponseEntity.ok(post) : ResponseEntity.notFound().build();
    }

    /**
     * Creates a new blog post.
     *
     * @param post The {@link Post} object to be created.
     * @return The created {@link Post} object.
     */
    @PostMapping
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    /**
     * Updates an existing blog post.
     *
     * @param id          The ID of the post to update.
     * @param postDetails The updated post details.
     * @return {@link ResponseEntity} containing the updated post if successful, or
     *         404 Not Found if the post does not exist.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post postDetails) {
        Post updatedPost = postService.updatePost(id, postDetails);
        return updatedPost != null ? ResponseEntity.ok(updatedPost) : ResponseEntity.notFound().build();
    }

    /**
     * Deletes a blog post by ID.
     *
     * @param id The ID of the post to delete.
     * @return {@link ResponseEntity<Void>} with 204 No Content if deletion is
     *         successful.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
