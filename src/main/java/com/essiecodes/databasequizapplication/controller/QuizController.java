package com.essiecodes.databasequizapplication.controller;

import com.essiecodes.databasequizapplication.data.model.Quiz;
import com.essiecodes.databasequizapplication.dto.request.CreateQuizRequest;
import com.essiecodes.databasequizapplication.dto.request.SubmitQuizRequest;
import com.essiecodes.databasequizapplication.dto.request.TakeQuizRequest;
import com.essiecodes.databasequizapplication.dto.response.CreatedQuizResponse;
import com.essiecodes.databasequizapplication.dto.response.QuizScoreResponse;
import com.essiecodes.databasequizapplication.exception.QuizException;
import com.essiecodes.databasequizapplication.service.QuizService;
import com.essiecodes.databasequizapplication.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/quizzes")
@RequiredArgsConstructor
public class QuizController {

    private final QuizService quizService;
    private final ValidationUtil validationUtil;

    @PostMapping
    public ResponseEntity<?> createQuiz(
            @RequestHeader("Authorization") String token,
            @RequestBody CreateQuizRequest request) {
        try {
            validationUtil.validateFacilitatorRole(token);  // Validating facilitator role
            CreatedQuizResponse quiz = quizService.createQuiz(request);  // Creating quiz
            return new ResponseEntity<>(quiz, HttpStatus.CREATED);  // Returning created quiz response
        } catch (QuizException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);  // Error handling
        }
    }

    @PostMapping("/{quizId}/evaluate")
    public ResponseEntity<?> evaluateQuiz(
            @RequestHeader("Authorization") String token,
            @PathVariable Long quizId,
            @RequestBody SubmitQuizRequest request) {
        try {
            validationUtil.validateStudentRole(token);  // Validating user role
            QuizScoreResponse scoreResponse = quizService.evaluateQuiz(quizId, request);  // Evaluating quiz
            return new ResponseEntity<>(scoreResponse, HttpStatus.OK);  // Returning score response
        } catch (QuizException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);  // Error handling
        }
    }

    @GetMapping("/get/{quizId}")
    public ResponseEntity<?> getQuiz(
            @RequestHeader("Authorization") String token,
            @PathVariable Long quizId) {
        try {
            validationUtil.validateStudentRole(token);  // Validating user role
            Quiz quiz = quizService.findQuizBy(quizId);  // Fetching quiz by ID
            return new ResponseEntity<>(quiz, HttpStatus.OK);  // Returning quiz details
        } catch (QuizException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);  // Error handling
        }
    }

    @PostMapping("/take")
    public ResponseEntity<?> takeQuiz(
            @RequestHeader("Authorization") String token,
            @RequestBody TakeQuizRequest takeQuizRequest) {
        try {
            validationUtil.validateStudentRole(token);  // Validating user role
            Quiz quiz = quizService.takeQuiz(takeQuizRequest);  // Fetching quiz by ID
            return new ResponseEntity<>(quiz, HttpStatus.OK);  // Returning quiz details
        } catch (QuizException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);  // Error handling
        }
    }
}
