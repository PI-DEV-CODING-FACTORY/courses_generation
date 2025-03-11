package com.github.ratomidev.service;

import com.github.ratomidev.entity.Course;
import com.github.ratomidev.entity.Lesson;
import com.github.ratomidev.repository.CourseRepository;
import com.github.ratomidev.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LessonService {
    @Autowired
    private LessonRepository lessonRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    public Optional<Lesson> getLessonById(Long id) {
        return lessonRepository.findById(id);
    }

    public List<Lesson> getLessonsByCourseId(Long courseId) {
        return lessonRepository.findByCourseId(courseId);
    }

    public Lesson createLesson(Long courseId, Lesson lesson) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        lesson.setCourse(course);
        return lessonRepository.save(lesson);
    }

    public Lesson updateLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    public void deleteLesson(Long id) {
        lessonRepository.deleteById(id);
    }
}