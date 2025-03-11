package com.github.ratomidev.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long studentId = 1101L;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    @JsonIgnore
    private Course course;

    private Boolean generatedByAi;

    private String weakPoints;


    private String recommendations;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
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

    public Boolean getGeneratedByAi() {
        return generatedByAi;
    }

    public void setGeneratedByAi(Boolean generatedByAi) {
        this.generatedByAi = generatedByAi;
    }

    public String getWeakPoints() {
        return weakPoints;
    }

    public void setWeakPoints(String weakPoints) {
        this.weakPoints = weakPoints;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}