package com.essiecodes.databasequizapplication.service;

import com.essiecodes.databasequizapplication.data.model.UserRole;
import com.essiecodes.databasequizapplication.data.repository.UserRepo;
import com.essiecodes.databasequizapplication.dto.request.LoginRequest;
import com.essiecodes.databasequizapplication.dto.request.RegisterUserRequest;
import com.essiecodes.databasequizapplication.dto.response.LoginResponse;
import com.essiecodes.databasequizapplication.dto.response.RegisteredUserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private CourseService courseService;


    @BeforeEach
    public void setUp(){
        courseService.deleteAll();
        userRepo.deleteAll();
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setFirstName("Esther");
        registerUserRequest.setLastName("Aiyeola");
        registerUserRequest.setSemicolonEmail("esther@semicolon.africa");
        registerUserRequest.setUserRole(UserRole.FACILITATOR);
        registerUserRequest.setPassword("password");

        RegisteredUserResponse response = userService.registerUser(registerUserRequest);
        assertThat(response.getMessage(), is("Facilitator registered"));

    }

    @Test
    public void testThatUserCanLogin(){

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setSemicolonEmail("esther@semicolon.africa");
        loginRequest.setPassword("password");

        LoginResponse response = userService.loginUser(loginRequest);
        assertThat(response.getMessage(), is("Facilitator logged in"));



    }

}