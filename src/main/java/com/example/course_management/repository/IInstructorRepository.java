package com.example.course_management.repository;

import com.example.course_management.model.Instructor;

import java.util.List;
import java.util.Optional;

public interface IInstructorRepository {
    List<Instructor> findAll();

    Optional<Instructor> findById(Long id);

    Optional<Instructor> create(Instructor instructor);

    Optional<Instructor> update(Long id, Instructor instructor);

    Optional<Instructor> deleteById(Long id);
}
