package com.github.ratomidev.repository;

import com.github.ratomidev.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByCourseId(Long courseId);
    List<Report> findByStudentId(Long studentId);
}