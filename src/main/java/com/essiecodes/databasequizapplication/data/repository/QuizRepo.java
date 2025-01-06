package com.essiecodes.databasequizapplication.data.repository;

import com.essiecodes.databasequizapplication.data.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepo extends JpaRepository<Quiz, Long> {
}
