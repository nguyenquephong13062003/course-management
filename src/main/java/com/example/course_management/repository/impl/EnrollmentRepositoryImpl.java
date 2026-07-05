package com.example.course_management.repository.impl;

import com.example.course_management.model.Enrollment;
import com.example.course_management.repository.IEnrollmentRepository;
import com.example.course_management.utils.IdGenerator;
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

    @Override
    public List<Enrollment> findAll() {
        return enrollments;
    }

    @Override
    public Enrollment findById(Long id) {
        return enrollments.stream()
                .filter(enrollment -> enrollment.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    private final IdGenerator<Enrollment> idGenerator = new IdGenerator<>(enrollments);

    @Override
    public Enrollment create(Enrollment enrollment) {
        enrollment.setId(idGenerator.next());
        enrollments.add(enrollment);
        return enrollment;
    }

    @Override
    public Enrollment update(Long id, Enrollment enrollment) {

        Enrollment existingEnrollment = findById(id);

        if (existingEnrollment == null) {
            return null;
        }

        existingEnrollment.setStudentName(enrollment.getStudentName());
        existingEnrollment.setCourseId(enrollment.getCourseId());

        return existingEnrollment;
    }

    @Override
    public Enrollment deleteById(Long id) {

        Enrollment existingEnrollment = findById(id);

        if (existingEnrollment == null) {
            return null;
        }

        enrollments.remove(existingEnrollment);

        return existingEnrollment;
    }

}
