package com.essiecodes.databasequizapplication.controller;

import com.essiecodes.databasequizapplication.dto.request.LoginRequest;
import com.essiecodes.databasequizapplication.dto.request.RegisterUserRequest;
import com.essiecodes.databasequizapplication.dto.response.LoginResponse;
import com.essiecodes.databasequizapplication.dto.response.RegisteredUserResponse;
import com.essiecodes.databasequizapplication.exception.UserException;
import com.essiecodes.databasequizapplication.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegisteredUserResponse> registerUser(@Valid @RequestBody RegisterUserRequest registerUserRequest) {
        RegisteredUserResponse response = userService.registerUser(registerUserRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        LoginResponse response = userService.loginUser(loginRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Catch UserException and return a meaningful response
    @ExceptionHandler(UserException.class)
    public ResponseEntity<String> handleUserException(UserException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Catch other exceptions (e.g., IllegalArgumentException)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // General exception handler
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>("An unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
