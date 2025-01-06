package com.essiecodes.databasequizapplication.dto.response;

import com.essiecodes.databasequizapplication.data.model.Question;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class CreatedQuizResponse {
    private Long id;
    private String title;
    private List<Question> questionList;
    private String message;

}
