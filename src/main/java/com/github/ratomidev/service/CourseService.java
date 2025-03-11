package com.github.ratomidev.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.ratomidev.entity.Course;
import com.github.ratomidev.entity.Lesson;
import com.github.ratomidev.entity.ExampleHistory;
import com.github.ratomidev.llm.AiModel;
import com.github.ratomidev.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    public Course createCourse(Course course) throws Exception {
        AiModel aiModel = new AiModel();
        String templateTitle = "Base on this description: " + course.getDescription()
                + " generate a title for this course it shouldn't be more than 5 words without cotes";
        course.setTitle(aiModel.getAnswer(templateTitle));

        String templateLessons = "Generate a JSON array of lessons based on this description at least return 10 lessons for this course: "
                + course.getDescription() +
                ". Each lesson should have these fields: title (string), content (detailed string and try to be a long content), createdAt (date), updatedAt (date).  "
                +
                "Return only the JSON array without any additional text.";

        String aiResponse = aiModel.getAnswer(templateLessons);
        ObjectMapper mapper = new ObjectMapper();

        try {
            ArrayNode lessonsArray = (ArrayNode) mapper.readTree(aiResponse);
            List<Lesson> lessonsList = new ArrayList<>();

            // Convert each JSON lesson to Lesson entity
            for (JsonNode lessonNode : lessonsArray) {
                Lesson lesson = new Lesson();
                lesson.setTitle(lessonNode.get("title").asText());
                lesson.setContent(lessonNode.get("content").asText());
                lesson.setCourse(course);

                // Generate example for the lesson
                String templateExample = "Generate a practical example to explain this lesson content: " + lesson.getContent()+ "it should be a string  maximum 30 words and it can contains code.";
                String exampleResponse = aiModel.getAnswer(templateExample);
               // String exampleResponse = "This is an example for the lesson content";
                // Create ExampleHistory
                ExampleHistory exampleHistory = new ExampleHistory();
                exampleHistory.setStudentId(1101L); // Using default student ID
                exampleHistory.setNewExample(exampleResponse);
                exampleHistory.setCourse(course);
                exampleHistory.setLesson(lesson);

                // Set the example history to lesson
                List<ExampleHistory> exampleHistories = new ArrayList<>();
                exampleHistories.add(exampleHistory);
                lesson.setExampleHistories(exampleHistories);

                lessonsList.add(lesson);
            }

            // Set the lessons list to the course
            course.setLessons(lessonsList);

            // Debug output
            System.out.println("Created " + lessonsList.size() + " lessons for the course");

        } catch (Exception e) {
            System.err.println("Error parsing JSON response: " + e.getMessage());
            System.err.println("AI Response was: " + aiResponse);
            throw new Exception("Failed to parse lessons JSON from AI model");
        }

        return courseRepository.save(course);
    }

    public Course updateCourse(Course course) {
        return courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}