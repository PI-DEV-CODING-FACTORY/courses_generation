package com.github.ratomidev.service;

import com.github.ratomidev.entity.Course;
import com.github.ratomidev.entity.Report;
import com.github.ratomidev.repository.CourseRepository;
import com.github.ratomidev.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportService {
    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private CourseRepository courseRepository;

    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    public Optional<Report> getReportById(Long id) {
        return reportRepository.findById(id);
    }

    public List<Report> getReportsByCourseId(Long courseId) {
        return reportRepository.findByCourseId(courseId);
    }

    public List<Report> getReportsByStudentId(Long studentId) {
        return reportRepository.findByStudentId(studentId);
    }

    public Report createReport(Long courseId, Report report) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        report.setCourse(course);
        return reportRepository.save(report);
    }

    public Report updateReport(Report report) {
        return reportRepository.save(report);
    }

    public void deleteReport(Long id) {
        reportRepository.deleteById(id);
    }
}