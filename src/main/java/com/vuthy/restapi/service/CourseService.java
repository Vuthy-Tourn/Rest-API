package com.vuthy.restapi.service;

import com.vuthy.restapi.dto.CourseResponse;

import java.util.List;

public interface CourseService {
    /**
     * ទាញព័ត៌មានវគ្គសិក្សាទាំងអស់
     * @author Vuthy Tourn
     * @return List<CourseResponse>
     */

    List<CourseResponse> getAllCourses(Boolean status);

    List<CourseResponse> getCourses(Boolean status, String title);

    CourseResponse getCourseByCode(String code);

    CourseResponse getCourseById(String id);
}
