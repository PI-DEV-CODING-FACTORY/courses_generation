package com.github.ratomidev.repository;

import com.github.ratomidev.entity.ExampleHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExampleHistoryRepository extends JpaRepository<ExampleHistory, Long> {
    List<ExampleHistory> findByCourseId(Long courseId);

    List<ExampleHistory> findByLessonId(Long lessonId);

    List<ExampleHistory> findByStudentId(Long studentId);

    List<ExampleHistory> findByCourseIdAndLessonId(Long courseId, Long lessonId);
}