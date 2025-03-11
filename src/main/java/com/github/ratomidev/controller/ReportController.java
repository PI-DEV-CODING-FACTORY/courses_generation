package com.github.ratomidev.controller;

import com.github.ratomidev.entity.Report;
import com.github.ratomidev.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping
    public List<Report> getAllReports() {
        return reportService.getAllReports();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable Long id) {
        return reportService.getReportById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/course/{courseId}")
    public List<Report> getReportsByCourseId(@PathVariable Long courseId) {
        return reportService.getReportsByCourseId(courseId);
    }

    @GetMapping("/student/{studentId}")
    public List<Report> getReportsByStudentId(@PathVariable Long studentId) {
        return reportService.getReportsByStudentId(studentId);
    }

    @PostMapping("/course/{courseId}")
    public ResponseEntity<Report> createReport(
            @PathVariable Long courseId,
            @RequestBody Report report) {
        return ResponseEntity.ok(reportService.createReport(courseId, report));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Report> updateReport(
            @PathVariable Long id,
            @RequestBody Report report) {
        return reportService.getReportById(id)
                .map(existingReport -> {
                    report.setId(id);
                    report.setCourse(existingReport.getCourse());
                    return ResponseEntity.ok(reportService.updateReport(report));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable Long id) {
        return reportService.getReportById(id)
                .map(report -> {
                    reportService.deleteReport(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}