package com.example.course_management.controller;

import com.example.course_management.model.Instructor;
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
    public ResponseEntity<List<Instructor>> getAllInstructor(
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

        return ResponseEntity.ok(instructors);

    }

    @PostMapping
    public ResponseEntity<?> createInstructor(
            @RequestBody Instructor instructor) {

        Instructor createdInstructor = instructorService.createInstructor(instructor);

        if (createdInstructor == null) {
            return ResponseEntity
                    .badRequest()
                    .body("Instructor cannot be created.");
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdInstructor);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateInstructor(
            @PathVariable Long id,
            @RequestBody Instructor instructor) {

        Instructor updatedInstructor = instructorService.updateInstructor(id, instructor);

        if (updatedInstructor == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Instructor not found.");
        }

        return ResponseEntity.ok(updatedInstructor);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstructor(
            @PathVariable Long id) {

        Instructor deletedInstructor = instructorService.deleteInstructorById(id);

        if (deletedInstructor == null) {
            return  ResponseEntity
                    .notFound()
                    .build();
        }

        return ResponseEntity
                .noContent()
                .build();

    }

}
