package com.vuthy.restapi.controller;

import com.vuthy.restapi.dto.CourseResponse;
import com.vuthy.restapi.dto.CreateCourseRequest;
import com.vuthy.restapi.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    // get course by status
    @GetMapping
    public List<CourseResponse> getAllCoursesByStatus(@RequestParam( required = false, defaultValue = "true") Boolean status) {
        return courseService.getAllCourses(status);
    }

    // get course by status and title
    @GetMapping(params = { "title" })
    public List<CourseResponse> getCoursesByStatusAndTitle(
            @RequestParam( required = false, defaultValue = "true") Boolean status,
            @RequestParam String title) {

        return courseService.getCourses(status, title);
    }

    // get course by code
    @GetMapping("/{code}")
    public CourseResponse getCourseByCode(@PathVariable String code){
        return courseService.getCourseByCode(code);
    }

    // get course by id
    @GetMapping("/{id}")
    public CourseResponse getCourseById(@PathVariable String id){
        return courseService.getCourseById(id);
    }

    // Create course
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CourseResponse createCourse(@RequestBody CreateCourseRequest createCourseRequest){
        return courseService.createCourse(createCourseRequest);
    }

    // Delete course
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{code}")
    public void deleteCourse(@PathVariable String code){
        courseService.deleteCourse(code);
    }
}
