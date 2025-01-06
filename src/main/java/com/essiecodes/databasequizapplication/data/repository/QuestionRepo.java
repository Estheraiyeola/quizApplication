package com.essiecodes.databasequizapplication.data.repository;

import com.essiecodes.databasequizapplication.data.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepo extends JpaRepository<Question, Long> {
}
