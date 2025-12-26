package com.vuthy.restapi.service.impl;

import com.vuthy.restapi.domain.Course;
import com.vuthy.restapi.dto.CourseResponse;
import com.vuthy.restapi.dto.CreateCourseRequest;
import com.vuthy.restapi.repository.CourseRepository;
import com.vuthy.restapi.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    @Override
    public List<CourseResponse> getAllCourses(Boolean status) {

        // filter by status
        // mapping from course to courseResponse
        List<CourseResponse> filteredCourses = courseRepository.getCourses()
                .stream()
                .filter(c-> c.getStatus().equals(status))
                .map(c->CourseResponse.builder()
                        .code(c.getCode())
                        .title(c.getTitle())
                        .price(c.getPrice())
                        .status(c.getStatus())
                        .build())
                .toList();

        return filteredCourses;
    }

    @Override
    public List<CourseResponse> getCourses(Boolean status, String title) {
        List<CourseResponse> searchCourses = courseRepository.getCourses()
                .stream()
                .filter(c->c.getStatus().equals(status))
                .filter(c->c.getTitle().toLowerCase().contains(title.toLowerCase()))
                .map(c->CourseResponse.builder()
                        .code(c.getCode())
                        .title(c.getTitle())
                        .price(c.getPrice())
                        .status(c.getStatus())
                        .build())
                .toList();
        return searchCourses;
    }

    @Override
    public CourseResponse getCourseByCode(String code) {
        CourseResponse filteredByCode = courseRepository.getCourses()
                .stream()
                .filter(c-> c.getCode().equals(code))
                .map(c->CourseResponse.builder()
                        .code(c.getCode())
                        .title(c.getTitle())
                        .price(c.getPrice())
                        .status(c.getStatus())
                        .build())
                .findFirst()
                .orElse(null);
        return filteredByCode;
    }

    @Override
    public CourseResponse getCourseById(String id) {
        CourseResponse filteredById = courseRepository.getCourses()
                .stream()
                .filter(c-> c.getId().equals(id))
                .map(c->CourseResponse.builder()
                        .code(c.getCode())
                        .title(c.getTitle())
                        .price(c.getPrice())
                        .status(c.getStatus())
                        .build())
                .findFirst()
                .orElse(null);
        return filteredById;
    }

    @Override
    public CourseResponse createCourse(CreateCourseRequest createCourseRequest) {
        boolean isCourseExisted = courseRepository.getCourses()
                .stream()
                .anyMatch(course -> course.getCode().equals(createCourseRequest.code()));

        if (isCourseExisted) {
            // Conflict
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Course already exists");
        }

        // Map from dto to domain model
        Course course = Course.builder()
                .id(UUID.randomUUID().toString())
                .code(createCourseRequest.code())
                .title(createCourseRequest.title())
                .price(createCourseRequest.price())
                .status(false)
                .build();

        courseRepository.getCourses().add(course);

        // Return - map from domain model to dto
        return CourseResponse.builder()
                .code(course.getCode())
                .title(course.getTitle())
                .price(course.getPrice())
                .status(false)
                .build();
    }

    @Override
    public void deleteCourse(String code) {
        boolean isCourseExisted = courseRepository.getCourses()
                .stream()
                .anyMatch(course -> course.getCode().equals(code));

        if (!isCourseExisted) {
            // Not found
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Course code doesn't exist");
        }
        courseRepository.getCourses().removeIf(course->course.getCode().equals(code));

    }
}
