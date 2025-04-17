package com.github.ratomidev.service;

import com.github.ratomidev.entity.Course;
import com.github.ratomidev.entity.Resume;

import com.github.ratomidev.repository.CourseRepository;
import com.github.ratomidev.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResumeService {
    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<Resume> getAllResumes() {
        return resumeRepository.findAll();
    }

    public Optional<Resume> getResumeById(Long id) {
        return resumeRepository.findById(id);
    }

    public Resume createResumeForCourse(Resume resume, Long courseId) {
        try {
            // Get the course by ID
            Course course = courseRepository.findById(courseId)
                    .orElseThrow(() -> new Exception("Course not found with id: " + courseId));

            // Set the course for the resume
            resume.setCourse(course);

            // Make sure the ID is null to create a new entity
            resume.setId(null);

            // Save and return the resume
            Resume savedResume = resumeRepository.save(resume);

            // Ensure persistence by fetching the newly created resume
            return resumeRepository.findById(savedResume.getId())
                    .orElseThrow(() -> new RuntimeException("Failed to retrieve saved resume"));
        } catch (Exception e) {
            throw new RuntimeException("Failed to create resume: " + e.getMessage(), e);
        }
    }

    public Resume updateResume(Resume resume) {
        return resumeRepository.save(resume);
    }

    public void deleteResume(Long id) {
        resumeRepository.deleteById(id);
    }
}
