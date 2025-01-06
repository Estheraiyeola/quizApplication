package com.essiecodes.databasequizapplication.service;

import com.essiecodes.databasequizapplication.data.model.Course;
import com.essiecodes.databasequizapplication.dto.request.CreateCourseRequest;
import com.essiecodes.databasequizapplication.dto.response.CreatedCourseResponse;

public interface CourseService {
    CreatedCourseResponse createCourse(CreateCourseRequest createCourseRequest);
    void deleteCourse(Long courseId);

    void deleteAll();

    Course findById(Long courseId);
}
