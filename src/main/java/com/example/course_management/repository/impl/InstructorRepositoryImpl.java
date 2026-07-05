package com.example.course_management.repository.impl;

import com.example.course_management.model.Instructor;
import com.example.course_management.repository.IInstructorRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class InstructorRepositoryImpl implements IInstructorRepository {
    private final List<Instructor> instructors = new ArrayList<>();

    public InstructorRepositoryImpl() {
        instructors.add(new Instructor(1L, "Nguyen Van A", "a@gmail.com"));
        instructors.add(new Instructor(2L, "Tran Thi B", "b@gmail.com"));
    }

}
