package com.example.course_management.repository;

import com.example.course_management.model.Enrollment;

import java.util.List;

public interface IEnrollmentRepository {
    List<Enrollment> findAll();

    Enrollment findById(Long id);

    Enrollment create(Enrollment enrollment);

    Enrollment update(Long id, Enrollment enrollment);

    Enrollment deleteById(Long id);
}
