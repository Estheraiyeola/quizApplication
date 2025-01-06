package com.essiecodes.databasequizapplication.data.repository;

import com.essiecodes.databasequizapplication.data.model.Cohort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CohortRepo extends JpaRepository<Cohort, Long> {
    Optional<Cohort> findByNumber(long number);
}
