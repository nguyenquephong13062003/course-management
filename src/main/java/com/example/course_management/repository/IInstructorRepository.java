package com.example.course_management.repository;

import com.example.course_management.model.Instructor;

import java.util.List;

public interface IInstructorRepository {
    List<Instructor> findAll();

    Instructor findById(Long id);

    Instructor create(Instructor instructor);

    Instructor update(Long id, Instructor instructor);

    Instructor deleteById(Long id);
}
