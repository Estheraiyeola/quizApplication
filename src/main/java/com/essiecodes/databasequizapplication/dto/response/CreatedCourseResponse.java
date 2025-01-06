package com.essiecodes.databasequizapplication.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreatedCourseResponse {
    private String message;
    private Long courseId;
}
