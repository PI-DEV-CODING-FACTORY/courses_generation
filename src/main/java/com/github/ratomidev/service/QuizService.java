package com.github.ratomidev.service;

import com.github.ratomidev.entity.Course;
import com.github.ratomidev.entity.Lesson;
import com.github.ratomidev.entity.Quiz;
import com.github.ratomidev.repository.CourseRepository;
import com.github.ratomidev.repository.LessonRepository;
import com.github.ratomidev.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private LessonRepository lessonRepository;

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    public Optional<Quiz> getQuizById(Long id) {
        return quizRepository.findById(id);
    }

    public List<Quiz> getQuizzesByCourseId(Long courseId) {
        return quizRepository.findByCourseId(courseId);
    }

    public List<Quiz> getQuizzesByLessonId(Long lessonId) {
        return quizRepository.findByLessonId(lessonId);
    }

    public Quiz createQuiz(Long courseId, Long lessonId, Quiz quiz) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));
        
        quiz.setCourse(course);
        quiz.setLesson(lesson);
        return quizRepository.save(quiz);
    }

    public Quiz updateQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public void deleteQuiz(Long id) {
        quizRepository.deleteById(id);
    }
}