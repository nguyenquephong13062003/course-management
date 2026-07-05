package com.example.course_management.service.impl;

import com.example.course_management.model.Course;
import com.example.course_management.model.Enrollment;
import com.example.course_management.repository.IEnrollmentRepository;
import com.example.course_management.service.ICourseService;
import com.example.course_management.service.IEnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentServiceImpl implements IEnrollmentService {
    private final IEnrollmentRepository enrollmentRepository;
    private final ICourseService courseService;

    @Autowired
    public EnrollmentServiceImpl(IEnrollmentRepository enrollmentRepository, ICourseService courseService) {
        this.enrollmentRepository = enrollmentRepository;
        this.courseService = courseService;
    }

    @Override
    public List<Enrollment> getAllEnrollment() {
        return enrollmentRepository.findAll();
    }

    @Override
    public Enrollment getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id);
    }

    @Override
    public Enrollment createEnrollment(Enrollment enrollment) {

        Course course = courseService.getCourseById(enrollment.getCourseId());

        if (course == null) {
            return null;
        }

        return enrollmentRepository.create(enrollment);
    }

    @Override
    public Enrollment updateEnrollment(Long id, Enrollment enrollment) {

//        Enrollment existingEnrollment = enrollmentRepository.findById(id);
//
//        if (existingEnrollment == null) {
//            return null;
//        }

        Course course = courseService.getCourseById(enrollment.getCourseId());

        if (course == null) {
            return null;
        }

        return enrollmentRepository.update(id, enrollment);
    }

    @Override
    public Enrollment deleteEnrollmentById(Long id) {

//        Enrollment existingEnrollment = enrollmentRepository.findById(id);
//
//        if (existingEnrollment == null) {
//            return null;
//        }

        return enrollmentRepository.deleteById(id);
    }

}
