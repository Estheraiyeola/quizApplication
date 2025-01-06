package com.essiecodes.databasequizapplication.dto.request;

import com.essiecodes.databasequizapplication.data.model.UserRole;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterUserRequest {
    private String firstName;
    private String lastName;
    private String semicolonEmail;
    private String password;
    private UserRole userRole;
}
