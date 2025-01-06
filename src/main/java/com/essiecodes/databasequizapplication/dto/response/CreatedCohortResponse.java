package com.essiecodes.databasequizapplication.dto.response;

import com.essiecodes.databasequizapplication.data.model.Cohort;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreatedCohortResponse {
    private String message;
    private Cohort cohort;
}
