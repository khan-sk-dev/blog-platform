package com.example.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.entity.Post;
import com.example.blog.repository.PostRepository;

/**
 * Service layer for handling business logic related to {@link Post} entities.
 * <p>
 * This class provides methods for creating, retrieving, updating, and deleting blog posts.
 * It interacts with the {@link PostRepository} to perform database operations.
 * </p>
 *
 * @author Your Name
 * @version 1.0
 */
@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    /**
     * Retrieves all blog posts from the database.
     *
     * @return A list of all posts.
     */
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    /**
     * Retrieves a blog post by its ID.
     *
     * @param id The ID of the post to retrieve.
     * @return The found {@link Post} or {@code null} if no post exists with the given ID.
     */
    public Post getPostById(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    /**
     * Creates and saves a new blog post.
     *
     * @param post The post entity to be created.
     * @return The newly saved {@link Post}.
     */
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    /**
     * Updates an existing blog post.
     * <p>
     * If the post with the given ID exists, it updates the title and content.
     * Otherwise, it returns {@code null}.
     * </p>
     *
     * @param id          The ID of the post to update.
     * @param postDetails The updated post details.
     * @return The updated {@link Post} or {@code null} if no post exists with the given ID.
     */
    public Post updatePost(Long id, Post postDetails) {
        Post post = postRepository.findById(id).orElse(null);
        if (post != null) {
            post.setTitle(postDetails.getTitle());
            post.setContent(postDetails.getContent());
            return postRepository.save(post);
        }
        return null;
    }

    /**
     * Deletes a blog post by its ID.
     *
     * @param id The ID of the post to be deleted.
     */
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
