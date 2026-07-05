package com.example.course_management.controller;

import com.example.course_management.model.Course;
import com.example.course_management.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("course")
public class CourseController {
    private final ICourseService courseService;

    @Autowired
    public CourseController(ICourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourse(
            @RequestParam(required = false) String search) {

        List<Course> courses = courseService.getAllCourse();

        if (search != null && !search.isBlank()) {
            courses = courses.stream()
                    .filter(course ->
                            course.getTitle()
                                    .toLowerCase()
                                    .contains(search.toLowerCase()))
                    .toList();
        }

        return ResponseEntity.ok(courses);
    }

    @PostMapping
    public ResponseEntity<?> createCourse(
            @RequestBody Course course) {

        Course createdCourse = courseService.createCourse(course);

        if (createdCourse == null) {
            return ResponseEntity
                    .badRequest()
                    .body("Instructor does not exist.");
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdCourse);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourse(
            @PathVariable Long id,
            @RequestBody Course course) {

        Course updatedCourse = courseService.updateCourse(id, course);

        if (updatedCourse == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Course not found or Instructor does not exist.");
        }

        return ResponseEntity.ok(updatedCourse);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(
            @PathVariable Long id) {

        Course deletedCourse = courseService.deleteCourseById(id);

        if (deletedCourse == null) {
            return  ResponseEntity
                    .notFound()
                    .build();
        }

        return ResponseEntity
                .noContent()
                .build();

    }

}
