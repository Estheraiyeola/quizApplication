package com.essiecodes.databasequizapplication.service;

import com.essiecodes.databasequizapplication.data.repository.CohortRepo;
import com.essiecodes.databasequizapplication.dto.request.CreateCohortRequest;
import com.essiecodes.databasequizapplication.dto.response.CreatedCohortResponse;
import com.essiecodes.databasequizapplication.exception.CohortException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CohortServiceTest {
    @Autowired
    private CohortService cohortService;

    @Autowired
    private CohortRepo cohortRepo;

    @BeforeEach
    public void setUp(){
        cohortRepo.deleteAll();
        CreateCohortRequest createCohortRequest = new CreateCohortRequest();
        createCohortRequest.setName("Ace clan");
        createCohortRequest.setNumber(17);

        CreatedCohortResponse response = cohortService.createCohort(createCohortRequest);
        assertThat(response.getMessage(), is("Cohort created successfully"));

    }

    @Test
    public void testThatDuplicateCohortNumberThrowsException() {
        CreateCohortRequest createCohortRequest = new CreateCohortRequest();
        createCohortRequest.setName("Ace clan");
        createCohortRequest.setNumber(17);

        assertThrows(CohortException.class, () -> {
            cohortService.createCohort(createCohortRequest);
        });
    }

    @Test
    public void testThatCohortCanBeDeleted() {
        cohortRepo.deleteAll();
        CreateCohortRequest createCohortRequest = new CreateCohortRequest();
        createCohortRequest.setName("Ace clan");
        createCohortRequest.setNumber(17);

        CreatedCohortResponse response = cohortService.createCohort(createCohortRequest);

        cohortService.deleteCohort(response.getCohort().getId());

        assertThrows(CohortException.class, () -> {
            cohortService.deleteCohort(response.getCohort().getId());
        });
    }

}