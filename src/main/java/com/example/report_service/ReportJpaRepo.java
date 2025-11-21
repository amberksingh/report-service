package com.example.report_service;

import com.example.report_service.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportJpaRepo extends JpaRepository<Report, Long> {
}
