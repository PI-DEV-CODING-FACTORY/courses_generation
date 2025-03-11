package com.github.ratomidev.controller;

import com.github.ratomidev.entity.ExampleHistory;

import com.github.ratomidev.service.ExampleHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/example-histories")

public class ExampleHistoryController {
    @Autowired
    private ExampleHistoryService exampleHistoryService;

    @GetMapping
    public List<ExampleHistory> getAllExampleHistories() {
        return exampleHistoryService.getAllExampleHistories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExampleHistory> getExampleHistoryById(@PathVariable Long id) {
        return exampleHistoryService.getExampleHistoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/course/{courseId}")
    public List<ExampleHistory> getExampleHistoriesByCourseId(@PathVariable Long courseId) {
        return exampleHistoryService.getExampleHistoriesByCourseId(courseId);
    }

    @GetMapping("/lesson/{lessonId}")
    public List<ExampleHistory> getExampleHistoriesByLessonId(@PathVariable Long lessonId) {
        return exampleHistoryService.getExampleHistoriesByLessonId(lessonId);
    }

    @GetMapping("/student/{studentId}")
    public List<ExampleHistory> getExampleHistoriesByStudentId(@PathVariable Long studentId) {
        return exampleHistoryService.getExampleHistoriesByStudentId(studentId);
    }

    @GetMapping("/course/{courseId}/lesson/{lessonId}")
    public List<ExampleHistory> getExampleHistoriesByCourseIdAndLessonId(
            @PathVariable Long courseId,
            @PathVariable Long lessonId) {
        return exampleHistoryService.getExampleHistoriesByCourseIdAndLessonId(courseId, lessonId);
    }

    @PostMapping("/course/{courseId}/lesson/{lessonId}")
    public ResponseEntity<ExampleHistory> createExampleHistory(
            @PathVariable Long courseId,
            @PathVariable Long lessonId,
            @RequestBody ExampleHistory exampleHistory) {
        return ResponseEntity.ok(exampleHistoryService.createExampleHistory(courseId, lessonId, exampleHistory));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExampleHistory> updateExampleHistory(
            @PathVariable Long id,
            @RequestBody ExampleHistory exampleHistory) {
        return exampleHistoryService.getExampleHistoryById(id)
                .map(existingHistory -> {
                    exampleHistory.setId(id);
                    exampleHistory.setCourse(existingHistory.getCourse());
                    exampleHistory.setLesson(existingHistory.getLesson());
                    return ResponseEntity.ok(exampleHistoryService.updateExampleHistory(exampleHistory));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExampleHistory(@PathVariable Long id) {
        return exampleHistoryService.getExampleHistoryById(id)
                .map(history -> {
                    exampleHistoryService.deleteExampleHistory(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}