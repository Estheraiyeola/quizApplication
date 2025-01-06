package com.essiecodes.databasequizapplication.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateQuizRequest {
    private String title;
    private List<QuestionRequest> questions;
}
