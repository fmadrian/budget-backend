package com.example.expenseTracker.repository;

import com.example.expenseTracker.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Report> findByNameContainsIgnoreCase(String name);
    List<Report> findByDateBetween(Instant since, Instant until);
    List<Report> findByNameContainsIgnoreCaseAndDateBetween(String name, Instant since, Instant until);
    List<Report> findByNameContainsIgnoreCaseAndDateGreaterThanEqual(String name, Instant since);
    List<Report> findByNameContainsIgnoreCaseAndDateLessThanEqual(String name, Instant until);
}
