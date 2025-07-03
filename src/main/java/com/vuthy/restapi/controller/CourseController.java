package com.vuthy.restapi.controller;

import com.vuthy.restapi.domain.Course;
import com.vuthy.restapi.dto.CourseResponse;
import com.vuthy.restapi.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/v1/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
//    @GetMapping
//    public List<CourseResponse> getAllCoursesByStatus(@RequestParam( required = false, defaultValue = "true") Boolean status) {
//        return courseService.getAllCourses(status);
//    }

    @GetMapping
    public List<CourseResponse> getCourses(@RequestParam( required = false, defaultValue = "true") Boolean status,
                                                   @RequestParam( required = false, defaultValue = "") String title) {
        return courseService.getCourses(status, title);

    }

    @GetMapping("/{code}")
    public CourseResponse getCourseByCode(@PathVariable String code){
        return courseService.getCourseByCode(code);
    }

    @GetMapping("/{id}")
    public CourseResponse getCourseById(@PathVariable String id){
        return courseService.getCourseById(id);
    }
}
