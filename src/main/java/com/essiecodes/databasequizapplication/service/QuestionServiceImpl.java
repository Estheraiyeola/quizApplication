package com.essiecodes.databasequizapplication.service;

import com.essiecodes.databasequizapplication.data.model.Question;
import com.essiecodes.databasequizapplication.data.repository.QuestionRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService{
    private final QuestionRepo questionRepo;
    @Override
    public Question saveQuestion(Question question) {
        return questionRepo.save(question);
    }
}
