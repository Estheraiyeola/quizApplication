package com.essiecodes.databasequizapplication.service;

import com.essiecodes.databasequizapplication.data.model.User;
import com.essiecodes.databasequizapplication.data.model.UserRole;
import com.essiecodes.databasequizapplication.data.repository.UserRepo;
import com.essiecodes.databasequizapplication.dto.request.LoginRequest;
import com.essiecodes.databasequizapplication.dto.request.RegisterUserRequest;
import com.essiecodes.databasequizapplication.dto.response.LoginResponse;
import com.essiecodes.databasequizapplication.dto.response.RegisteredUserResponse;
import com.essiecodes.databasequizapplication.exception.UserException;
import com.essiecodes.databasequizapplication.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;
    private JwtUtil jwtUtil;
    @Override
    public RegisteredUserResponse registerUser(RegisterUserRequest registerUserRequest) {
        validateUserExistence(registerUserRequest);

        User newUser = mapRequestToUserObject(registerUserRequest);

        User savedUser = userRepo.save(newUser);

        return buildResponseForRegistration(savedUser);
    }

    @Override
    public LoginResponse loginUser(LoginRequest loginRequest) {
        Optional<User> optionalUser = validateUserExistence(loginRequest);

        User user = optionalUser.get();

        verifyPassword(loginRequest, user);

        String token = jwtUtil.generateToken(user.getSemicolonEmail(), user.getUserRole().toString());

        return buildResponseForAuthenticatedUser(token, user);
    }

    @Override
    public User findUserByEmail(String facilitatorEmail) {
        return userRepo.findBySemicolonEmail(facilitatorEmail).get();
    }

    @Override
    public void deleteAll() {
        userRepo.deleteAll();
    }

    @Override
    public Optional<User> findById(Long userId) {
        return userRepo.findById(userId);
    }

    private static LoginResponse buildResponseForAuthenticatedUser(String token, User user) {
        LoginResponse response = new LoginResponse();
        response.setToken(token);
        if (user.getUserRole()== UserRole.FACILITATOR){
            response.setMessage("Facilitator logged in");
        }
        else {
            response.setMessage("Student logged in");
        }
        return response;
    }

    private void verifyPassword(LoginRequest loginRequest, User user) {
        boolean isPasswordMatch = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
        if (!isPasswordMatch) {
            throw new IllegalArgumentException("Invalid email or password");
        }
    }

    private Optional<User> validateUserExistence(LoginRequest loginRequest) {
        Optional<User> optionalUser = userRepo.findBySemicolonEmail(loginRequest.getSemicolonEmail());
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        return optionalUser;
    }

    private static RegisteredUserResponse buildResponseForRegistration(User savedUser) {
        RegisteredUserResponse response = new RegisteredUserResponse();
        if (savedUser.getUserRole()== UserRole.FACILITATOR){
            response.setMessage("Facilitator registered");
        }
        else {
            response.setMessage("Student registered");
        }
        response.setUser(savedUser);
        return response;
    }

    private User mapRequestToUserObject(RegisterUserRequest registerUserRequest) {
        User newUser = new User();
        newUser.setFirstName(registerUserRequest.getFirstName());
        newUser.setLastName(registerUserRequest.getLastName());
        newUser.setSemicolonEmail(registerUserRequest.getSemicolonEmail());

        String encodedPassword = passwordEncoder.encode(registerUserRequest.getPassword());
        newUser.setPassword(encodedPassword);
        newUser.setUserRole(registerUserRequest.getUserRole());
        return newUser;
    }

    private void validateUserExistence(RegisterUserRequest registerUserRequest) {
        Optional<User> existingUser = userRepo.findBySemicolonEmail(registerUserRequest.getSemicolonEmail());
        if (existingUser.isPresent()) {
            throw new UserException("User with email " + registerUserRequest.getSemicolonEmail() + " already exists!");
        }
    }
}
