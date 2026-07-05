package com.example.course_management.controller;

import com.example.course_management.model.Enrollment;
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
    public ResponseEntity<List<Enrollment>> getAllEnrollment(
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

        return ResponseEntity.ok(enrollments);
    }

    @PostMapping
    public ResponseEntity<?> createEnrollment(
            @RequestBody Enrollment enrollment) {

        Enrollment createdEnrollment =
                enrollmentService.createEnrollment(enrollment);

        if (createdEnrollment == null) {
            return ResponseEntity
                    .badRequest()
                    .body("Course does not exist.");
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdEnrollment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEnrollment(
            @PathVariable Long id,
            @RequestBody Enrollment enrollment) {

        Enrollment updatedEnrollment =
                enrollmentService.updateEnrollment(id, enrollment);

        if (updatedEnrollment == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Enrollment not found or Course does not exist.");
        }

        return ResponseEntity.ok(updatedEnrollment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollment(
            @PathVariable Long id) {

        Enrollment deletedEnrollment =
                enrollmentService.deleteEnrollmentById(id);

        if (deletedEnrollment == null) {
            return ResponseEntity
                    .notFound()
                    .build();
        }

        return ResponseEntity
                .noContent()
                .build();
    }
}