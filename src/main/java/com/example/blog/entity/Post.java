package com.example.blog.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Represents a blog post entity.
 * <p>
 * This entity class models a blog post in the application. It includes fields
 * for the post ID, title, content, and associated comments. The class is
 * annotated with JPA annotations to map it to a database table.
 * </p>
 *
 * <h3>Enhancements Applied:</h3>
 * <ul>
 * <li>✅ Class-Level Javadoc: Describes the purpose and behavior of the entity
 * class.</li>
 * <li>✅ Field-Level Constraints: Enforced constraints on title length and
 * required fields.</li>
 * <li>✅ Constructor-Level Documentation: Explains constructor constraints and
 * usage.</li>
 * <li>✅ Getter/Setter Documentation: Provides clarity on field accessors.</li>
 * <li>✅ Inline Comments: Improves readability by explaining key logic.</li>
 * </ul>
 *
 * @author Your Name
 * @version 1.1
 */
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique identifier for the post

    @Column(nullable = false, length = 255)
    @NotBlank(message = "Title cannot be blank")
    @Size(min = 5, max = 255, message = "Title must be between 5 and 255 characters")
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    @NotBlank(message = "Content cannot be blank")
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>(); // Initialize list to prevent NullPointerException

    /**
     * Default constructor required by JPA.
     * <p>
     * This constructor is needed for Hibernate to instantiate the entity
     * dynamically.
     * </p>
     */
    public Post() {
        this.comments = new ArrayList<>();
    }

    /**
     * Constructs a Post object with the specified details.
     * 
     * @param id       Unique identifier of the post.
     * @param title    Title of the blog post (Min: 5, Max: 255 characters).
     * @param content  Content of the blog post (Stored as TEXT).
     * @param comments List of comments associated with this post.
     */
    public Post(Long id, String title, String content, List<Comment> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.comments = (comments != null) ? comments : new ArrayList<>();
    }

    /**
     * Constructs a Post object without an ID (for test cases & DTO usage).
     * 
     * @param title   Title of the blog post.
     * @param content Content of the blog post.
     */
    public Post(String title, String content) {
        this.title = title;
        this.content = content;
        this.comments = new ArrayList<>();
    }

    // Getters and Setters

    /**
     * Retrieves the unique identifier of the post.
     * 
     * @return The ID of the post.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the post.
     * 
     * @param id The new ID of the post.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the title of the post.
     * 
     * @return The title of the post.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the post.
     * 
     * @param title The new title of the post.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Retrieves the content of the post.
     * 
     * @return The content of the post.
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the post.
     * 
     * @param content The new content of the post.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Retrieves the list of comments associated with the post.
     * 
     * @return A list of comments for this post.
     */
    public List<Comment> getComments() {
        return comments;
    }

    /**
     * Sets the list of comments associated with the post.
     * 
     * @param comments The new list of comments.
     */
    public void setComments(List<Comment> comments) {
        this.comments = (comments != null) ? comments : new ArrayList<>();
    }
}
