package com.github.ratomidev.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.JdbcTypeCode;

import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    // Add this field in the Course class after other fields
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Lesson> lessons = new ArrayList<>();
    // Add after the lessons field
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Quiz> quizzes = new ArrayList<>();
    // Add getter and setter
    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }
    // Add these getter and setter
    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
    // Add after the quizzes field
        @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
        private List<StudentProgress> studentProgresses = new ArrayList<>();
    // Add getter and setter
    public List<StudentProgress> getStudentProgresses() {
        return studentProgresses;
    }

    public void setStudentProgresses(List<StudentProgress> studentProgresses) {
        this.studentProgresses = studentProgresses;
    }
    // Add after the studentProgresses field
        @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
        private List<ExampleHistory> exampleHistories = new ArrayList<>();
    // Add getter and setter
    public List<ExampleHistory> getExampleHistories() {
        return exampleHistories;
    }

    public void setExampleHistories(List<ExampleHistory> exampleHistories) {
        this.exampleHistories = exampleHistories;
    }
    private String description;

    private Boolean generatedByAi;

    @Enumerated(EnumType.STRING)
    private DifficultyLevel difficultyLevel;

    private String examples;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getGeneratedByAi() {
        return generatedByAi;
    }

    public void setGeneratedByAi(Boolean generatedByAi) {
        this.generatedByAi = generatedByAi;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }



    public String getExamples() {
        return examples;
    }

    public void setExamples(String examples) {
        this.examples = examples;
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