package com.github.ratomidev.controller;

import com.github.ratomidev.entity.Resume;
import com.github.ratomidev.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/resumes")
public class ResumeController {
    @Autowired
    private ResumeService resumeService;

    @GetMapping
    public List<Resume> getAllResumes() {
        return resumeService.getAllResumes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resume> getResumeById(@PathVariable Long id) {
        return resumeService.getResumeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/course/{courseId}")
    public ResponseEntity<Resume> createResume(
            @PathVariable Long courseId,
            @RequestBody Resume resume) {
        try {
            Resume createdResume = resumeService.createResumeForCourse(resume, courseId);
            return ResponseEntity.status(201).body(createdResume); // 201 Created
        } catch (Exception e) {
            // Log the error
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resume> updateResume(
            @PathVariable Long id,
            @RequestBody Resume resume) {
        return resumeService.getResumeById(id)
                .map(existingResume -> {
                    resume.setId(id);
                    resume.setCourse(existingResume.getCourse());
                    return ResponseEntity.ok(resumeService.updateResume(resume));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResume(@PathVariable Long id) {
        return resumeService.getResumeById(id)
                .map(resume -> {
                    resumeService.deleteResume(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
