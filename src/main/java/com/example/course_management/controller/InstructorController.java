package com.example.course_management.controller;

import com.example.course_management.model.Instructor;
import com.example.course_management.response.ApiResponse;
import com.example.course_management.service.IInstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("instructor")
public class InstructorController {
    private final IInstructorService instructorService;

    @Autowired
    public InstructorController(IInstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Instructor>>> getAllInstructor(
            @RequestParam(required = false) String search) {

        List<Instructor> instructors = instructorService.getAllInstructor();

        if (search != null && !search.isBlank()) {
            instructors = instructors.stream()
                    .filter(instructor ->
                            instructor.getName()
                                    .toLowerCase()
                                    .contains(search.toLowerCase()))
                    .toList();
        }

        ApiResponse<List<Instructor>> response = new ApiResponse<>(
                true,
                "Get all instructors successfully.",
                instructors
        );

        return ResponseEntity.ok(response);

    }

    @PostMapping
    public ResponseEntity<ApiResponse<Instructor>> createInstructor(
            @RequestBody Instructor instructor) {

        Instructor createdInstructor = instructorService.createInstructor(instructor);

        if (createdInstructor == null) {
            return ResponseEntity
                    .badRequest()
                    .body(new ApiResponse<>(
                            false,
                            "Instructor cannot be created.",
                            null
                    ));
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "Instructor created successfully.",
                        createdInstructor
                ));

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Instructor>> updateInstructor(
            @PathVariable Long id,
            @RequestBody Instructor instructor) {

        Instructor updatedInstructor = instructorService.updateInstructor(id, instructor);

        if (updatedInstructor == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(
                            false,
                            "Instructor not found.",
                            null
                    ));
        }

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Instructor updated successfully.",
                        updatedInstructor
                )
        );

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteInstructor(
            @PathVariable Long id) {

        Instructor deletedInstructor = instructorService.deleteInstructorById(id);

        if (deletedInstructor == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(
                            false,
                            "Instructor not found.",
                            null
                    ));
        }

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Instructor deleted successfully.",
                        null
                )
        );

    }

}
