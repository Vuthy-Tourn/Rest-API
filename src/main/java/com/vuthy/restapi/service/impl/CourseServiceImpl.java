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
        return courseRepository.findByStatus(status)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<CourseResponse> getCourses(Boolean status, String title) {
        return courseRepository.findByStatusAndTitleContainingIgnoreCase(status, title)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public CourseResponse getCourseByCode(String code) {
        return courseRepository.findByCode(code)
                .map(this::mapToResponse)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Course code doesn't exist"
                ));
    }

    @Override
    public CourseResponse getCourseById(String id) {
        Long courseId;
        try {
            courseId = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid course id");
        }

        return courseRepository.findById(courseId)
                .map(this::mapToResponse)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Course id doesn't exist"
                ));
    }

    @Override
    public CourseResponse createCourse(CreateCourseRequest createCourseRequest) {
        boolean exists = courseRepository.findByCode(createCourseRequest.code()).isPresent();
        if (exists) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Course already exists");
        }

        Course course = Course.builder()
                .code(createCourseRequest.code())
                .title(createCourseRequest.title())
                .price(createCourseRequest.price())
                .status(false)
                .build();

        Course saved = courseRepository.save(course);

        return mapToResponse(saved);
    }

    @Override
    public void deleteCourse(String code) {
        Course course = courseRepository.findByCode(code)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Course code doesn't exist"
                ));

        courseRepository.delete(course);
    }

    // Helper method to map entity to DTO
    private CourseResponse mapToResponse(Course course) {
        return CourseResponse.builder()
                .code(course.getCode())
                .title(course.getTitle())
                .price(course.getPrice())
                .status(course.getStatus())
                .build();
    }
}

