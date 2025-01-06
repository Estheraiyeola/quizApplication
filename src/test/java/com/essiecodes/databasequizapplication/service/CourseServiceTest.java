package com.essiecodes.databasequizapplication.service;

import com.essiecodes.databasequizapplication.data.model.UserRole;
import com.essiecodes.databasequizapplication.data.repository.CourseRepo;
import com.essiecodes.databasequizapplication.dto.request.CreateCourseRequest;
import com.essiecodes.databasequizapplication.dto.request.RegisterUserRequest;
import com.essiecodes.databasequizapplication.dto.response.CreatedCourseResponse;
import com.essiecodes.databasequizapplication.dto.response.RegisteredUserResponse;
import com.essiecodes.databasequizapplication.exception.CourseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
class CourseServiceTest {

    @Autowired
    private CourseRepo courseRepo;
    @Autowired
    private CourseService courseService;
    @Autowired
    private UserService userService;

    @BeforeEach
    void setUp() {
        courseRepo.deleteAll();
        userService.deleteAll();
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setFirstName("Esther");
        registerUserRequest.setLastName("Aiyeola");
        registerUserRequest.setSemicolonEmail("esther@semicolon.africa");
        registerUserRequest.setUserRole(UserRole.FACILITATOR);
        registerUserRequest.setPassword("password");

        RegisteredUserResponse response = userService.registerUser(registerUserRequest);
        assertThat(response.getMessage(), is("Facilitator registered"));

        CreateCourseRequest request = new CreateCourseRequest();
        request.setName("Database Basics");
        request.setFacilitatorEmail("esther@semicolon.africa");


        CreatedCourseResponse courseResponse = courseService.createCourse(request);
        assertThat(courseResponse.getMessage(), is("Course created successfully"));
    }

    @Test
    void testCreateCourseWhenCourseAlreadyExists() {
        CreateCourseRequest request = new CreateCourseRequest();
        request.setName("Database Basics");
        request.setFacilitatorEmail("esther@semicolon.africa");

        assertThrows(CourseException.class, () -> courseService.createCourse(request));
    }

    @Test
    void testDeleteCourseSuccessfully() {
        RegisterUserRequest registerUserRequest2 = new RegisterUserRequest();
        registerUserRequest2.setFirstName("Chibuzo");
        registerUserRequest2.setLastName("Ekejiuba");
        registerUserRequest2.setSemicolonEmail("chibuzo@semicolon.africa");
        registerUserRequest2.setUserRole(UserRole.FACILITATOR);
        registerUserRequest2.setPassword("password");

        RegisteredUserResponse response2 = userService.registerUser(registerUserRequest2);
        assertThat(response2.getMessage(), is("Facilitator registered"));

        CreateCourseRequest request = new CreateCourseRequest();
        request.setName("Java");
        request.setFacilitatorEmail("chibuzo@semicolon.africa");


        CreatedCourseResponse response = courseService.createCourse(request);
        assertThat(response.getMessage(), is("Course created successfully"));

        courseService.deleteCourse(response.getCourseId());
        assertThrows(CourseException.class, () -> courseService.findById(response.getCourseId()));

    }

    @Test
    void testDeleteNonExistentCourse() {

        assertThrows(CourseException.class, () -> courseService.deleteCourse(0L));
    }
}
