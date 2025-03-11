package com.github.ratomidev.repository;

import com.github.ratomidev.entity.StudentProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentProgressRepository extends JpaRepository<StudentProgress, Long> {
    List<StudentProgress> findByCourseId(Long courseId);
    List<StudentProgress> findByLessonId(Long lessonId);
    List<StudentProgress> findByStudentId(Long studentId);
}