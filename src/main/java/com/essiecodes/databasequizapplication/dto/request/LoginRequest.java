package com.essiecodes.databasequizapplication.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String semicolonEmail;
    private String password;
}
