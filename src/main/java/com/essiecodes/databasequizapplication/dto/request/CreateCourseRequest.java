package com.essiecodes.databasequizapplication.dto.request;

import lombok.Data;

@Data
public class CreateCourseRequest {
    private String name;
    private String facilitatorEmail;
}
