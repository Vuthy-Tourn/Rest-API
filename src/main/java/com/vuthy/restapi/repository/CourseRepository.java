package com.vuthy.restapi.repository;

import com.vuthy.restapi.domain.Course;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Repository
@Getter
@Setter
public class CourseRepository {
    private List<Course> courses;
    public CourseRepository() {
        courses = new ArrayList<>();
        courses.add(Course.builder()
                .id(UUID.randomUUID().toString())
                .code("ISTAD-001")
                .title("Spring Boot REST API")
                .price(100.00)
                .status(true)
                .build());
        courses.add(Course.builder()
                .id(UUID.randomUUID().toString())
                .code("ISTAD-002")
                .title("NEXT.JS")
                .price(60.00)
                .status(true)
                .build());

        courses.add(Course.builder()
                        .id(UUID.randomUUID().toString())
                        .code("ISTAD-003")
                        .title("Javascript")
                        .price(80.00)
                        .status(true)
                .build());

        courses.add(Course.builder()
                .id(UUID.randomUUID().toString())
                .code("ISTAD-004")
                .title("PHP")
                .price(90.00)
                .status(true)
                .build());
        courses.add(Course.builder()
                .id(UUID.randomUUID().toString())
                .code("ISTAD-005")
                .title("CSS")
                .price(70.00)
                .status(false)
                .build());

        courses.add(Course.builder()
                .id(UUID.randomUUID().toString())
                .code("ISTAD-006")
                .title("HTML")
                .price(80.00)
                .status(false)
                .build());

        courses.add(Course.builder()
                .id(UUID.randomUUID().toString())
                .code("ISTAD-007")
                .title("Java")
                .price(90.00)
                .status(true)
                .build());

        courses.add(Course.builder()
                .id(UUID.randomUUID().toString())
                .code("ISTAD-008")
                .title("Laravel")
                .price(80.00)
                .status(true)
                .build());

    }
}
