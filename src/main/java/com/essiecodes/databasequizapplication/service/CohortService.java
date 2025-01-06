package com.essiecodes.databasequizapplication.service;

import com.essiecodes.databasequizapplication.dto.request.CreateCohortRequest;
import com.essiecodes.databasequizapplication.dto.response.CreatedCohortResponse;

public interface CohortService {
    CreatedCohortResponse createCohort(CreateCohortRequest createCohortRequest);

    void deleteCohort(Long cohortId);
}
