package com.example.course_management.repository.impl;

import com.example.course_management.model.Enrollment;
import com.example.course_management.repository.IEnrollmentRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EnrollmentRepositoryImpl implements IEnrollmentRepository {
    private final List<Enrollment> enrollments = new ArrayList<>();

    public EnrollmentRepositoryImpl() {
        enrollments.add(new Enrollment(
                1L,
                "Nguyen Van C",
                1L));

        enrollments.add(new Enrollment(
                2L,
                "Tran Thi D",
                2L));
    }

}
