package com.essiecodes.databasequizapplication.service;

import com.essiecodes.databasequizapplication.data.model.Cohort;
import com.essiecodes.databasequizapplication.data.repository.CohortRepo;
import com.essiecodes.databasequizapplication.dto.request.CreateCohortRequest;
import com.essiecodes.databasequizapplication.dto.response.CreatedCohortResponse;
import com.essiecodes.databasequizapplication.exception.CohortException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CohortServiceImpl implements CohortService{

    private final CohortRepo cohortRepo;

    @Override
    public CreatedCohortResponse createCohort(CreateCohortRequest createCohortRequest) {
        // Check if a cohort with the same number exists
        if (cohortRepo.findByNumber(createCohortRequest.getNumber()).isPresent()) {
            throw new CohortException("Cohort with number " + createCohortRequest.getNumber() + " already exists!");
        }

        // Create and save the cohort
        Cohort cohort = new Cohort();
        cohort.setName(createCohortRequest.getName());
        cohort.setNumber(createCohortRequest.getNumber());
        Cohort savedCohort = cohortRepo.save(cohort);

        // Build and return the response
        CreatedCohortResponse response = new CreatedCohortResponse();
        response.setMessage("Cohort created successfully");
        response.setCohort(savedCohort);
        return response;
    }

    @Override
    public void deleteCohort(Long cohortId) {
        if (!cohortRepo.existsById(cohortId)) {
            throw new CohortException("Cohort with ID " + cohortId + " does not exist!");
        }
        cohortRepo.deleteById(cohortId);
    }
}
