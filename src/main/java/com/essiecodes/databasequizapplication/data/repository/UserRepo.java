package com.essiecodes.databasequizapplication.data.repository;

import com.essiecodes.databasequizapplication.data.model.User;
import com.essiecodes.databasequizapplication.data.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findBySemicolonEmail(String semicolonEmail);
}
