package com.example.course_management.repository;

import com.example.course_management.model.Enrollment;

import java.util.List;
import java.util.Optional;

public interface IEnrollmentRepository {
    List<Enrollment> findAll();

    Optional<Enrollment> findById(Long id);

    Optional<Enrollment> create(Enrollment enrollment);

    Optional<Enrollment> update(Long id, Enrollment enrollment);

    Optional<Enrollment> deleteById(Long id);
}
