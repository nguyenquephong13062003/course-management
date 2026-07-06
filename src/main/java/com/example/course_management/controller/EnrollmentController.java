package com.example.course_management.controller;

import com.example.course_management.model.Enrollment;
import com.example.course_management.response.ApiResponse;
import com.example.course_management.service.IEnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("enrollment")
public class EnrollmentController {

    private final IEnrollmentService enrollmentService;

    @Autowired
    public EnrollmentController(IEnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Enrollment>>> getAllEnrollment(
            @RequestParam(required = false) String search) {

        List<Enrollment> enrollments = enrollmentService.getAllEnrollment();

        if (search != null && !search.isBlank()) {
            enrollments = enrollments.stream()
                    .filter(enrollment ->
                            enrollment.getStudentName()
                                    .toLowerCase()
                                    .contains(search.toLowerCase()))
                    .toList();
        }

        ApiResponse<List<Enrollment>> response = new ApiResponse<>(
                true,
                "Get all enrollments successfully.",
                enrollments
        );

        return ResponseEntity.ok(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> getEnrollment(
            @PathVariable Long id) {

        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Fetched successfully!",
                            enrollmentService.getEnrollmentById(id)
                    )
            );
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(
                            false,
                            e.getMessage(),
                            null
                    ));
        }

    }

    @PostMapping
    public ResponseEntity<ApiResponse<Enrollment>> createEnrollment(
            @RequestBody Enrollment enrollment) {

        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(
                            true,
                            "Enrollment created successfully.",
                            enrollmentService.createEnrollment(enrollment)
                    ));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(new ApiResponse<>(
                            false,
                            e.getMessage(),
                            null
                    ));
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> updateEnrollment(
            @PathVariable Long id,
            @RequestBody Enrollment enrollment) {

        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Enrollment updated successfully.",
                            enrollmentService.updateEnrollment(id, enrollment)
                    )
            );
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(
                            false,
                            e.getMessage(),
                            null
                    ));
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> deleteEnrollment(
            @PathVariable Long id) {

        try {


            return ResponseEntity.ok(
                    new ApiResponse<>(
                            true,
                            "Enrollment deleted successfully.",
                            enrollmentService.deleteEnrollmentById(id)
                    )
            );
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(
                            false,
                            e.getMessage(),
                            null
                    ));
        }

    }
}