package com.essiecodes.databasequizapplication.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class SubmitQuizRequest {
    private Long userId; // ID of the user submitting the quiz
    private List<SubmittedAnswer> answers; // List of answers submitted by the user

    @Data
    public static class SubmittedAnswer {
        private Long questionId; // ID of the question
        private String answer;   // Submitted answer

        public SubmittedAnswer(Long id, String answer) {
            this.answer = answer;
            this.questionId = id;
        }
    }

}
