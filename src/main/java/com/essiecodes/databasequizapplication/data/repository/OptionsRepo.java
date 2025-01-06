package com.essiecodes.databasequizapplication.data.repository;

import com.essiecodes.databasequizapplication.data.model.Options;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionsRepo extends JpaRepository<Options, Long> {
}
