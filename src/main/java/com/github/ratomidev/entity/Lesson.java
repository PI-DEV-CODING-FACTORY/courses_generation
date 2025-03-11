package com.github.ratomidev.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    @JsonIgnore
    private Course course;

    private String title;

    // this should conatain a very long text
    @Column(columnDefinition = "TEXT")
    private String content;

    // Add after other fields
    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    private List<Quiz> quizzes = new ArrayList<>();
    // Add after the quizzes field
    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
    private List<StudentProgress> studentProgresses = new ArrayList<>();
    // Add after the studentProgresses field
    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ExampleHistory> exampleHistories = new ArrayList<>();

    // Add getter and setter
    public List<StudentProgress> getStudentProgresses() {
        return studentProgresses;
    }

    public void setStudentProgresses(List<StudentProgress> studentProgresses) {
        this.studentProgresses = studentProgresses;
    }

    // Add getter and setter
    public List<ExampleHistory> getExampleHistories() {
        return exampleHistories;
    }

    public void setExampleHistories(List<ExampleHistory> exampleHistories) {
        this.exampleHistories = exampleHistories;
    }

    // Add getter and setter
    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}