package com.pablo.crudclub.models;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name="books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty (message="Title is required")
    @Size(min=3, message="Title should be at least three characters long ")
    private String title;

    @NotEmpty (message="Author name is required")
    @Size(min=3, message="Name should be at least three characters long ")
    private String author;

    @NotEmpty (message="Thoughts are required")
    @Size(min=8, message="Thoughts should be at least eight characters  ")
    private String myThoughts;

    // This will not allow the createdAt column to be updated after creation
    @Column(updatable = false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;

    // constructor assigns values to objects
    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User submittedBy;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMyThoughts() {
        return myThoughts;
    }

    public void setMyThoughts(String myThoughts) {
        this.myThoughts = myThoughts;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

    public User getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(User submittedBy) {
        this.submittedBy = submittedBy;
    }

}
