package com.essiecodes.databasequizapplication.service;

import com.essiecodes.databasequizapplication.data.model.Course;
import com.essiecodes.databasequizapplication.data.model.User;
import com.essiecodes.databasequizapplication.data.repository.CourseRepo;
import com.essiecodes.databasequizapplication.dto.request.CreateCourseRequest;
import com.essiecodes.databasequizapplication.dto.response.CreatedCourseResponse;
import com.essiecodes.databasequizapplication.exception.CourseException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepo courseRepo;
    private final UserService userService;

    @Override
    public CreatedCourseResponse createCourse(CreateCourseRequest createCourseRequest) {
        User facilitator = userService.findUserByEmail(createCourseRequest.getFacilitatorEmail());
        if (courseRepo.existsByName(createCourseRequest.getName())) {
            throw new CourseException("Course with name " + createCourseRequest.getName() + " already exists.");
        }


        Course course = new Course();
        course.setName(createCourseRequest.getName());
        course.setUser(facilitator);

        Course savedCourse = courseRepo.save(course);

        return new CreatedCourseResponse("Course created successfully", savedCourse.getId());
    }

    @Override
    public void deleteCourse(Long courseId) {
        if (!courseRepo.existsById(courseId)) {
            throw new CourseException("Course with ID " + courseId + " does not exist.");
        }
        courseRepo.deleteById(courseId);
    }

    @Override
    public void deleteAll() {
        courseRepo.deleteAll();
    }

    @Override
    public Course findById(Long courseId) {
        return courseRepo.findById(courseId).orElseThrow(()-> new CourseException("Course not found"));
    }
}
