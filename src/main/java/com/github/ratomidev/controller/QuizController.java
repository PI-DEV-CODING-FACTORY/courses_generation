package com.github.ratomidev.controller;

import com.github.ratomidev.entity.Quiz;
import com.github.ratomidev.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/quizzes")
public class QuizController {
    @Autowired
    private QuizService quizService;

    @GetMapping
    public List<Quiz> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Long id) {
        return quizService.getQuizById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/course/{courseId}")
    public List<Quiz> getQuizzesByCourseId(@PathVariable Long courseId) {
        return quizService.getQuizzesByCourseId(courseId);
    }

    @GetMapping("/lesson/{lessonId}")
    public List<Quiz> getQuizzesByLessonId(@PathVariable Long lessonId) {
        return quizService.getQuizzesByLessonId(lessonId);
    }

    @GetMapping("/course/{courseId}/lesson/{lessonId}")
    public List<Quiz> getQuizzesByCourseIdAndLessonId(
            @PathVariable Long courseId,
            @PathVariable Long lessonId) {
        return quizService.getQuizzesByCourseIdAndLessonId(courseId, lessonId);
    }

    @PostMapping("/course/{courseId}/lesson/{lessonId}")
    public ResponseEntity<Quiz> createQuiz(
            @PathVariable Long courseId,
            @PathVariable Long lessonId,
            @RequestBody Quiz quiz) {
        return ResponseEntity.ok(quizService.createQuiz(courseId, lessonId, quiz));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quiz> updateQuiz(@PathVariable Long id, @RequestBody Quiz quiz) {
        return quizService.getQuizById(id)
                .map(existingQuiz -> {
                    quiz.setId(id);
                    quiz.setCourse(existingQuiz.getCourse());
                    quiz.setLesson(existingQuiz.getLesson());
                    return ResponseEntity.ok(quizService.updateQuiz(quiz));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuiz(@PathVariable Long id) {
        return quizService.getQuizById(id)
                .map(quiz -> {
                    quizService.deleteQuiz(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}