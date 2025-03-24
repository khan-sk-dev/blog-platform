package com.example.blog.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Represents a comment entity in the blogging system.
 * <p>
 * A comment is associated with a specific blog post and contains user-generated
 * content.
 * </p>
 *
 * <h3>Entity Properties:</h3>
 * <ul>
 * <li>{@code id} - Unique identifier for each comment.</li>
 * <li>{@code content} - The text content of the comment.</li>
 * <li>{@code post} - The {@link Post} that the comment belongs to.</li>
 * </ul>
 *
 * <h3>Constraints & Behavior:</h3>
 * <ul>
 * <li>{@code content} is required (cannot be null).</li>
 * <li>{@code post} is required (cannot be null).</li>
 * <li>Auto-generated ID using {@code GenerationType.IDENTITY}.</li>
 * </ul>
 *
 * @author Your Name
 * @version 1.0
 */
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    /**
     * Default constructor for JPA.
     */
    public Comment() {
    }

    /**
     * Parameterized constructor for creating a new Comment instance.
     *
     * @param id      The unique ID of the comment.
     * @param content The text content of the comment (cannot be null).
     * @param post    The associated {@link Post} (cannot be null).
     * @throws NullPointerException if {@code content} or {@code post} is null.
     */
    public Comment(Long id, String content, Post post) {
        this.id = id;
        this.content = Objects.requireNonNull(content, "cannot set null content");
        this.post = Objects.requireNonNull(post, "Post cannot be null");
    }

    /**
     * Retrieves the unique identifier of the comment.
     *
     * @return The ID of the comment.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the comment.
     *
     * @param id The new ID to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the text content of the comment.
     *
     * @return The comment content.
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the text content of the comment.
     * 
     * @param content The new comment content (cannot be null).
     * @throws NullPointerException if {@code content} is null.
     */
    public void setContent(String content) {
        this.content = Objects.requireNonNull(content, "cannot set null content");
    }

    /**
     * Retrieves the associated post.
     *
     * @return The post to which this comment belongs.
     */
    public Post getPost() {
        return post;
    }

    /**
     * Sets the post associated with this comment.
     * 
     * @param post The post to associate (cannot be null).
     * @throws NullPointerException if {@code post} is null.
     */
    public void setPost(Post post) {
        this.post = Objects.requireNonNull(post, "Post cannot be null");
    }
}
