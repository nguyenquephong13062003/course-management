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

    @PostMapping
    public ResponseEntity<ApiResponse<Enrollment>> createEnrollment(
            @RequestBody Enrollment enrollment) {

        Enrollment createdEnrollment =
                enrollmentService.createEnrollment(enrollment);

        if (createdEnrollment == null) {
            return ResponseEntity
                    .badRequest()
                    .body(new ApiResponse<>(
                            false,
                            "Course does not exist.",
                            null
                    ));
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "Enrollment created successfully.",
                        createdEnrollment
                ));

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> updateEnrollment(
            @PathVariable Long id,
            @RequestBody Enrollment enrollment) {

        Enrollment updatedEnrollment =
                enrollmentService.updateEnrollment(id, enrollment);

        if (updatedEnrollment == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(
                            false,
                            "Enrollment not found or Course does not exist.",
                            null
                    ));
        }

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Enrollment updated successfully.",
                        updatedEnrollment
                )
        );

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteEnrollment(
            @PathVariable Long id) {

        Enrollment deletedEnrollment =
                enrollmentService.deleteEnrollmentById(id);

        if (deletedEnrollment == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(
                            false,
                            "Enrollment not found.",
                            null
                    ));
        }

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Enrollment deleted successfully.",
                        null
                )
        );

    }
}