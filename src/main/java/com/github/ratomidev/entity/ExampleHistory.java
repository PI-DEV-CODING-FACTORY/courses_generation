package com.github.ratomidev.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ExampleHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId = 1101L;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    @JsonIgnore
    private Course course;

    @ManyToOne
    @JoinColumn(name = "lesson_id", nullable = false)
    @JsonIgnore
    private Lesson lesson;

    // Update the newExample field to handle large text content
    @Column(columnDefinition = "TEXT")
    private String newExample;

    private LocalDateTime regeneratedAt;

    @PrePersist
    protected void onCreate() {
        regeneratedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public String getNewExample() {
        return newExample;
    }

    public void setNewExample(String newExample) {
        this.newExample = newExample;
    }

    public LocalDateTime getRegeneratedAt() {
        return regeneratedAt;
    }

    public void setRegeneratedAt(LocalDateTime regeneratedAt) {
        this.regeneratedAt = regeneratedAt;
    }
}