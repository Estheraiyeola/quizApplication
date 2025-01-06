package com.essiecodes.databasequizapplication.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class QuizScoreResponse {
    private int score;           // The user's score
    private int totalQuestions;  // Total number of questions in the quiz
    private double percentage;   // Percentage score
    private String feedback;     // Optional feedback or message
    private int correctAnswers;
    private int totalMarks;


    // Constructor with fields
    public QuizScoreResponse(int score, int totalQuestions, double percentage, String feedback) {
        this.score = score;
        this.totalQuestions = totalQuestions;
        this.percentage = percentage;
        this.feedback = feedback;
    }
}
