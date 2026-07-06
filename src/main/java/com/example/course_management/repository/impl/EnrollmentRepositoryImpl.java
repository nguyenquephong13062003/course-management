package com.example.course_management.repository.impl;

import com.example.course_management.model.Enrollment;
import com.example.course_management.repository.IEnrollmentRepository;
import com.example.course_management.utils.IdGenerator;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Optional<Enrollment> findById(Long id) {
        return enrollments.stream()
                .filter(enrollment -> enrollment.getId().equals(id))
                .findFirst();
    }

    private final IdGenerator<Enrollment> idGenerator = new IdGenerator<>(enrollments);

    @Override
    public Optional<Enrollment> create(Enrollment enrollment) {
        enrollment.setId(idGenerator.next());
        enrollments.add(enrollment);
        return Optional.of(enrollment);
    }

    @Override
    public Optional<Enrollment> update(Long id, Enrollment enrollment) {

        Optional<Enrollment> existingEnrollmentOpt = findById(id);

        existingEnrollmentOpt.ifPresent(existingEnrollment-> {
            existingEnrollment.setStudentName(enrollment.getStudentName());
            existingEnrollment.setCourseId(enrollment.getCourseId());
        });

        return existingEnrollmentOpt;
    }

    @Override
    public Optional<Enrollment> deleteById(Long id) {

        Optional<Enrollment> existingEnrollmentOpt = findById(id);

        existingEnrollmentOpt.ifPresent(enrollments::remove);

        return existingEnrollmentOpt;
    }

}
