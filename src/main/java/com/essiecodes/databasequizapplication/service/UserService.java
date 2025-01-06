package com.essiecodes.databasequizapplication.service;

import com.essiecodes.databasequizapplication.data.model.User;
import com.essiecodes.databasequizapplication.dto.request.LoginRequest;
import com.essiecodes.databasequizapplication.dto.request.RegisterUserRequest;
import com.essiecodes.databasequizapplication.dto.response.LoginResponse;
import com.essiecodes.databasequizapplication.dto.response.RegisteredUserResponse;

import java.util.Optional;

public interface UserService {
    RegisteredUserResponse registerUser(RegisterUserRequest registerUserRequest);

    LoginResponse loginUser(LoginRequest loginRequest);

    User findUserByEmail(String facilitatorEmail);

    void deleteAll();

    Optional<User> findById(Long userId);
}
