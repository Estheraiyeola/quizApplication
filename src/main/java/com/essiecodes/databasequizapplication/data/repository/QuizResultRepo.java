package com.essiecodes.databasequizapplication.data.repository;

import com.essiecodes.databasequizapplication.data.model.Quiz;
import com.essiecodes.databasequizapplication.data.model.QuizResult;
import com.essiecodes.databasequizapplication.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizResultRepo extends JpaRepository<QuizResult, Long> {
    boolean existsByQuizAndUser(Quiz quiz, User user);
}
