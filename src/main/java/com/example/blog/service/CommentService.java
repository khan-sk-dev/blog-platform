package com.example.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.blog.entity.Comment;
import com.example.blog.repository.CommentRepository;
import com.example.blog.repository.PostRepository;

/**
 * Service class for managing comments associated with blog posts.
 * <p>
 * This service provides methods for retrieving and adding comments to a
 * specific post.
 * It interacts with {@link CommentRepository} and {@link PostRepository} to
 * perform database operations.
 * </p>
 *
 * @author Your Name
 * @version 1.0
 */
@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    /**
     * Retrieves all comments for a given post.
     *
     * @param postId ID of the post whose comments need to be fetched.
     * @return List of {@link Comment} associated with the given post.
     */
    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    /**
     * Adds a new comment to a specific post.
     * <p>
     * If the post exists, the comment is associated with it and saved.
     * Otherwise, returns {@code null}.
     * </p>
     *
     * @param postId  ID of the post to which the comment will be added.
     * @param comment The {@link Comment} object to be saved.
     * @return The saved {@link Comment} if successful, otherwise {@code null}.
     */
    public Comment addComment(Long postId, Comment comment) {
        return postRepository.findById(postId).map(post -> {
            comment.setPost(post);
            return commentRepository.save(comment);
        }).orElse(null);
    }
}
