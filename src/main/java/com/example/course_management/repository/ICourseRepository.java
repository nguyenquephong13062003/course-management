package com.example.course_management.repository;

import com.example.course_management.model.Course;

import java.util.List;

public interface ICourseRepository {
    List<Course> findAll();

    Course findById(Long id);

    Course create(Course course);

    Course update(Long id, Course course);

    Course deleteById(Long id);
}
