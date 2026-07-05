package com.example.course_management.controller;

import com.example.course_management.model.Course;
import com.example.course_management.response.ApiResponse;
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
    public ResponseEntity<ApiResponse<List<Course>>> getAllCourse(
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

        ApiResponse<List<Course>> response = new ApiResponse<>(
                true,
                "Get all courses successfully.",
                courses
        );

        return ResponseEntity.ok(response);

    }

    @PostMapping
    public ResponseEntity<ApiResponse<Course>> createCourse(
            @RequestBody Course course) {

        Course createdCourse = courseService.createCourse(course);

        if (createdCourse == null) {
            return ResponseEntity
                    .badRequest()
                    .body(new ApiResponse<>(
                            false,
                            "Instructor does not exist.",
                            null
                    ));
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse<>(
                        true,
                        "Course created successfully.",
                        createdCourse
                ));

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> updateCourse(
            @PathVariable Long id,
            @RequestBody Course course) {

        Course updatedCourse = courseService.updateCourse(id, course);

        if (updatedCourse == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(
                            false,
                            "Course not found or Instructor does not exist.",
                            null
                    ));
        }

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Course updated successfully.",
                        updatedCourse
                )
        );

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteCourse(
            @PathVariable Long id) {

        Course deletedCourse = courseService.deleteCourseById(id);

        if (deletedCourse == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(
                            false,
                            "Course not found.",
                            null
                    ));
        }

        return ResponseEntity.ok(
                new ApiResponse<>(
                        true,
                        "Course deleted successfully.",
                        null
                )
        );

    }

}
