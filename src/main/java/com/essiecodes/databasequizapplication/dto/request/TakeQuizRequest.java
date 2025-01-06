package com.essiecodes.databasequizapplication.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TakeQuizRequest {
    private Long quizId;
    private Long userId;
}
