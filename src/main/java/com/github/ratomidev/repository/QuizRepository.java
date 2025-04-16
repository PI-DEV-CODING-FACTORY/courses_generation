package com.github.ratomidev.repository;

import com.github.ratomidev.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    List<Quiz> findByCourseId(Long courseId);

    List<Quiz> findByLessonId(Long lessonId);

    List<Quiz> findByCourseIdAndLessonId(Long courseId, Long lessonId);
}