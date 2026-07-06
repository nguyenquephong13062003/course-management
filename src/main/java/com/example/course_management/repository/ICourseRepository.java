package com.example.course_management.repository;

import com.example.course_management.model.Course;

import java.util.List;
import java.util.Optional;

public interface ICourseRepository {
    List<Course> findAll();

    Optional<Course> findById(Long id);

    Optional<Course> create(Course course);

    Optional<Course> update(Long id, Course course);

    Optional<Course> deleteById(Long id);
}
