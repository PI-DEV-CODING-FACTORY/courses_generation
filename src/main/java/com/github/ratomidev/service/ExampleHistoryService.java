package com.github.ratomidev.service;

import com.github.ratomidev.entity.Course;
import com.github.ratomidev.entity.ExampleHistory;
import com.github.ratomidev.entity.Lesson;

import com.github.ratomidev.repository.CourseRepository;
import com.github.ratomidev.repository.LessonRepository;
import com.github.ratomidev.repository.ExampleHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExampleHistoryService {
    @Autowired
    private ExampleHistoryRepository exampleHistoryRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private LessonRepository lessonRepository;

    public List<ExampleHistory> getAllExampleHistories() {
        return exampleHistoryRepository.findAll();
    }

    public Optional<ExampleHistory> getExampleHistoryById(Long id) {
        return exampleHistoryRepository.findById(id);
    }

    public List<ExampleHistory> getExampleHistoriesByCourseId(Long courseId) {
        return exampleHistoryRepository.findByCourseId(courseId);
    }

    public List<ExampleHistory> getExampleHistoriesByLessonId(Long lessonId) {
        return exampleHistoryRepository.findByLessonId(lessonId);
    }

    public List<ExampleHistory> getExampleHistoriesByStudentId(Long studentId) {
        return exampleHistoryRepository.findByStudentId(studentId);
    }

    public List<ExampleHistory> getExampleHistoriesByCourseIdAndLessonId(Long courseId, Long lessonId) {
        return exampleHistoryRepository.findByCourseIdAndLessonId(courseId, lessonId);
    }

    public ExampleHistory createExampleHistory(Long courseId, Long lessonId, ExampleHistory exampleHistory) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        exampleHistory.setCourse(course);
        exampleHistory.setLesson(lesson);
        return exampleHistoryRepository.save(exampleHistory);
    }

    public ExampleHistory updateExampleHistory(ExampleHistory exampleHistory) {
        return exampleHistoryRepository.save(exampleHistory);
    }

    public void deleteExampleHistory(Long id) {
        exampleHistoryRepository.deleteById(id);
    }
}