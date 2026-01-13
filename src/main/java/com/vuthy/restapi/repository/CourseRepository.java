package com.vuthy.restapi.repository;

import com.vuthy.restapi.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    // Get all courses by status
    List<Course> findByStatus(Boolean status);

    // Find course by exact code
    Optional<Course> findByCode(String code);

    // Find courses by status and partial title match (case-insensitive)
    List<Course> findByStatusAndTitleContainingIgnoreCase(Boolean status, String title);

    // Optional: Find courses by title only (partial match)
    List<Course> findByTitleContainingIgnoreCase(String title);

    // Optional: Find course by id (already exists in JpaRepository)
    // Optional<Course> findById(Long id); // inherited
}
