package com.essiecodes.databasequizapplication.controller;

import com.essiecodes.databasequizapplication.dto.request.CreateCohortRequest;
import com.essiecodes.databasequizapplication.dto.response.CreatedCohortResponse;
import com.essiecodes.databasequizapplication.exception.CohortException;
import com.essiecodes.databasequizapplication.service.CohortService;
import com.essiecodes.databasequizapplication.util.ValidationUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/cohorts")
@AllArgsConstructor
public class CohortController {

    private final CohortService cohortService;
    private final ValidationUtil validationUtil;

    @PostMapping
    public ResponseEntity<?> createCohort(
            @RequestHeader("Authorization") String token,
            @Valid @RequestBody CreateCohortRequest createCohortRequest) {
        try {
            validationUtil.validateFacilitatorRole(token);
            CreatedCohortResponse response = cohortService.createCohort(createCohortRequest);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (CohortException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCohort(
            @RequestHeader("Authorization") String token,
            @Valid @PathVariable Long id) {
        try {
            validationUtil.validateFacilitatorRole(token);
            cohortService.deleteCohort(id);
            return new ResponseEntity<>("Cohort deleted successfully", HttpStatus.OK);
        } catch (CohortException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }


}
