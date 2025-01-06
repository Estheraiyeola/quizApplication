package com.essiecodes.databasequizapplication.util;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ValidationUtil {
    private JwtUtil jwtUtil;
    public void validateFacilitatorRole(String token) {
        String role = extractToken(token);

        if (!"FACILITATOR".equals(role)) {
            throw new IllegalArgumentException("Access denied. You must be a facilitator.");
        }
    }

    private String extractToken(String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid or missing Authorization header");
        }
        String jwt = token.substring(7); // Remove "Bearer " prefix
        return jwtUtil.extractRole(jwt);
    }

    public void validateStudentRole(String token) {
        String role = extractToken(token);
        if (!"STUDENT".equals(role)) {
            throw new IllegalArgumentException("Access denied. You must be a facilitator.");
        }
    }
}
