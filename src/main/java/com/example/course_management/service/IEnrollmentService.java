package com.example.course_management.service;

import com.example.course_management.model.Enrollment;

import java.util.List;

public interface IEnrollmentService {
    List<Enrollment> getAllEnrollment();

    Enrollment getEnrollmentById(Long id);

    Enrollment createEnrollment(Enrollment enrollment);

    Enrollment updateEnrollment(Long id, Enrollment enrollment);

    Enrollment deleteEnrollmentById(Long id);
}
