package com.essiecodes.databasequizapplication.service;

import com.essiecodes.databasequizapplication.data.model.Quiz;
import com.essiecodes.databasequizapplication.dto.request.CreateQuizRequest;
import com.essiecodes.databasequizapplication.dto.request.SubmitQuizRequest;
import com.essiecodes.databasequizapplication.dto.request.TakeQuizRequest;
import com.essiecodes.databasequizapplication.dto.response.CreatedQuizResponse;
import com.essiecodes.databasequizapplication.dto.response.QuizScoreResponse;

public interface QuizService {
    CreatedQuizResponse createQuiz(CreateQuizRequest createQuizRequest);

    Quiz findQuizBy(Long id);

    Quiz takeQuiz(TakeQuizRequest request);

    QuizScoreResponse evaluateQuiz(Long quizId, SubmitQuizRequest submitQuizRequest);
}
