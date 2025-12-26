package com.vuthy.restapi.service;

import com.vuthy.restapi.dto.CourseResponse;
import com.vuthy.restapi.dto.CreateCourseRequest;

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


    /**
     * បង្កើតវគ្គសិក្សាថ្មី
     * @author Vuthy Tourn
     * @return CourseResponse
     */

    CourseResponse createCourse(CreateCourseRequest createCourseRequest);

    /**
     * លុបវគ្គសិក្សាតាមរយ: course code
     * delete success response 204
     * if code doesn't exist response 404, "Course code doesn't exist"
     * @param code of course
     */
    void deleteCourse(String code);
}
