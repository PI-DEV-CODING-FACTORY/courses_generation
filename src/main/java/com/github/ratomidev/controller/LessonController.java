package com.github.ratomidev.controller;

import com.github.ratomidev.entity.Lesson;
import com.github.ratomidev.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {
    @Autowired
    private LessonService lessonService;

    @GetMapping
    public List<Lesson> getAllLessons() {
        return lessonService.getAllLessons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lesson> getLessonById(@PathVariable Long id) {
        return lessonService.getLessonById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/course/{courseId}")
    public List<Lesson> getLessonsByCourseId(@PathVariable Long courseId) {
        return lessonService.getLessonsByCourseId(courseId);
    }

    @PostMapping("/course/{courseId}")
    public ResponseEntity<Lesson> createLesson(@PathVariable Long courseId, @RequestBody Lesson lesson) {
        return ResponseEntity.ok(lessonService.createLesson(courseId, lesson));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lesson> updateLesson(@PathVariable Long id, @RequestBody Lesson lesson) {
        return lessonService.getLessonById(id)
                .map(existingLesson -> {
                    lesson.setId(id);
                    lesson.setCourse(existingLesson.getCourse());
                    return ResponseEntity.ok(lessonService.updateLesson(lesson));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLesson(@PathVariable Long id) {
        return lessonService.getLessonById(id)
                .map(lesson -> {
                    lessonService.deleteLesson(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}