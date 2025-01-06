package com.essiecodes.databasequizapplication.dto.response;

import com.essiecodes.databasequizapplication.data.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisteredUserResponse {
    private String message;
    private User user;
}
