package com.github.ratomidev.controller;

import com.github.ratomidev.entity.StudentProgress;
import com.github.ratomidev.service.StudentProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/progress")
public class StudentProgressController {
    @Autowired
    private StudentProgressService studentProgressService;

    @GetMapping
    public List<StudentProgress> getAllProgress() {
        return studentProgressService.getAllProgress();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentProgress> getProgressById(@PathVariable Long id) {
        return studentProgressService.getProgressById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/course/{courseId}")
    public List<StudentProgress> getProgressByCourseId(@PathVariable Long courseId) {
        return studentProgressService.getProgressByCourseId(courseId);
    }

    @GetMapping("/lesson/{lessonId}")
    public List<StudentProgress> getProgressByLessonId(@PathVariable Long lessonId) {
        return studentProgressService.getProgressByLessonId(lessonId);
    }

    @GetMapping("/student/{studentId}")
    public List<StudentProgress> getProgressByStudentId(@PathVariable Long studentId) {
        return studentProgressService.getProgressByStudentId(studentId);
    }

    @PostMapping("/course/{courseId}/lesson/{lessonId}")
    public ResponseEntity<StudentProgress> createProgress(
            @PathVariable Long courseId,
            @PathVariable Long lessonId,
            @RequestBody StudentProgress progress) {
        return ResponseEntity.ok(studentProgressService.createProgress(courseId, lessonId, progress));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentProgress> updateProgress(@PathVariable Long id, @RequestBody StudentProgress progress) {
        return studentProgressService.getProgressById(id)
                .map(existingProgress -> {
                    progress.setId(id);
                    progress.setCourse(existingProgress.getCourse());
                    progress.setLesson(existingProgress.getLesson());
                    return ResponseEntity.ok(studentProgressService.updateProgress(progress));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProgress(@PathVariable Long id) {
        return studentProgressService.getProgressById(id)
                .map(progress -> {
                    studentProgressService.deleteProgress(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}