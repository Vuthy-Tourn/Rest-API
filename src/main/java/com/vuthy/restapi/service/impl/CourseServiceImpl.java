package com.vuthy.restapi.service.impl;

import com.vuthy.restapi.dto.CourseResponse;
import com.vuthy.restapi.repository.CourseRepository;
import com.vuthy.restapi.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
