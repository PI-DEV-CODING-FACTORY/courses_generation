package com.github.ratomidev.repository;

import com.github.ratomidev.entity.StudentProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentProgressRepository extends JpaRepository<StudentProgress, Long> {
    // Use course.id instead of courseId
    List<StudentProgress> findByCourse_Id(Long courseId);

    // Use lesson.id instead of lessonId
    List<StudentProgress> findByLesson_Id(Long lessonId);

    List<StudentProgress> findByStudentId(Long studentId);

    // Use course.id and lesson.id instead of courseId and lessonId
    List<StudentProgress> findByCourse_IdAndLesson_Id(Long courseId, Long lessonId);

    // If needed later, add this for a quiz reference
    // Optional<StudentProgress> findByCourse_IdAndLesson_IdAndQuiz_Id(Long
    // courseId, Long lessonId, Long quizId);
}