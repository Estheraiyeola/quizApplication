package com.essiecodes.databasequizapplication.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionRequest {
    private String question; // Text of the question
    private String answer; // Correct answer
    private List<CreateOptionRequest> options; // List of options
    private int marks; // Marks for this question
}
