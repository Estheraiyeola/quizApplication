package com.essiecodes.databasequizapplication.controller;

import com.essiecodes.databasequizapplication.dto.request.CreateCourseRequest;
import com.essiecodes.databasequizapplication.dto.response.CreatedCourseResponse;
import com.essiecodes.databasequizapplication.exception.CourseException;
import com.essiecodes.databasequizapplication.service.CourseService;
import com.essiecodes.databasequizapplication.util.ValidationUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/courses")
@AllArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final ValidationUtil validationUtil;

    @PostMapping
    public ResponseEntity<?> createCourse(
            @RequestHeader("Authorization") String token,
            @RequestBody CreateCourseRequest createCourseRequest) {
        try {
            validationUtil.validateFacilitatorRole(token);
            CreatedCourseResponse response = courseService.createCourse(createCourseRequest);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (CourseException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id) {
        try {
            validationUtil.validateFacilitatorRole(token);
            courseService.deleteCourse(id);
            return new ResponseEntity<>("Course deleted successfully", HttpStatus.OK);
        }  catch (CourseException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
