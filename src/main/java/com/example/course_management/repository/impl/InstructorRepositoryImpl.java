package com.example.course_management.repository.impl;

import com.example.course_management.model.Instructor;
import com.example.course_management.repository.IInstructorRepository;
import com.example.course_management.utils.IdGenerator;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
public class InstructorRepositoryImpl implements IInstructorRepository {
    private final List<Instructor> instructors = new ArrayList<>();

    public InstructorRepositoryImpl() {
        instructors.add(new Instructor(1L, "Nguyen Van A", "a@gmail.com"));
        instructors.add(new Instructor(2L, "Tran Thi B", "b@gmail.com"));
    }

    @Override
    public List<Instructor> findAll() {
        return instructors;
    }

    @Override
    public Optional<Instructor> findById(Long id) {
        return instructors.stream()
                .filter(instructor -> instructor.getId().equals(id))
                .findFirst();
    }

    private final IdGenerator<Instructor> idGenerator = new IdGenerator<>(instructors);

    @Override
    public Optional<Instructor> create(Instructor instructor) {
        instructor.setId(idGenerator.next());
        instructors.add(instructor);
        return Optional.of(instructor);
    }

    @Override
    public Optional<Instructor> update(Long id, Instructor instructor) {

        Optional<Instructor> existingInstructorOpt = findById(id);

        existingInstructorOpt.ifPresent(existingInstructor -> {
            existingInstructor.setName(instructor.getName());
            existingInstructor.setEmail(instructor.getEmail());
        });

        return existingInstructorOpt;
    }

    @Override
    public Optional<Instructor> deleteById(Long id) {

        Optional<Instructor> existingInstructorOpt = findById(id);

        existingInstructorOpt.ifPresent(instructors::remove);

        return existingInstructorOpt;
    }
}
