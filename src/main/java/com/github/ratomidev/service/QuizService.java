package com.github.ratomidev.service;

import com.github.ratomidev.entity.Course;
import com.github.ratomidev.entity.Lesson;
import com.github.ratomidev.entity.Quiz;
import com.github.ratomidev.entity.StudentProgress;
import com.github.ratomidev.llm.AiModel;
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

    @Autowired
    private StudentProgressService studentProgressService;

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

    public List<Quiz> getQuizzesByCourseIdAndLessonId(Long courseId, Long lessonId) {
        return quizRepository.findByCourseIdAndLessonId(courseId, lessonId);
    }

    public Quiz createQuiz(Long courseId, Long lessonId, Quiz quiz) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Lesson not found"));

        quiz.setCourse(course);
        quiz.setLesson(lesson);
        AiModel aiModel = new AiModel();
        String questionsGenerated = aiModel.getAnswer(
                "generate to me a quiz with 5 questions in json array that conatain this filed question, ansA, ansB , ansC, ansD, correctAns and didnt write any thing before and after the json object only the object should be returned  and answers based on this text: "
                        + lesson.getContent());
        System.out.println(questionsGenerated);
        quiz.setQuestions(questionsGenerated);

        // Save the quiz first to get the quiz ID
        Quiz savedQuiz = quizRepository.save(quiz);





        return savedQuiz;
    }

    public Quiz updateQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    public void deleteQuiz(Long id) {
        quizRepository.deleteById(id);
    }
}