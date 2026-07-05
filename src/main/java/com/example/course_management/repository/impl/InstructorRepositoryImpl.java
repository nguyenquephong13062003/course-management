package com.example.course_management.repository.impl;

import com.example.course_management.model.Instructor;
import com.example.course_management.repository.IInstructorRepository;
import com.example.course_management.utils.IdGenerator;
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

    @Override
    public List<Instructor> findAll() {
        return instructors;
    }

    @Override
    public Instructor findById(Long id) {
        return instructors.stream()
                .filter(instructor -> instructor.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    private final IdGenerator<Instructor> idGenerator = new IdGenerator<>(instructors);

    @Override
    public Instructor create(Instructor instructor) {
        instructor.setId(idGenerator.next());
        instructors.add(instructor);
        return instructor;
    }

    @Override
    public Instructor update(Long id, Instructor instructor) {

        Instructor existingInstructor = findById(id);

        if (existingInstructor == null) {
            return null;
        }

        existingInstructor.setName(instructor.getName());
        existingInstructor.setEmail(instructor.getEmail());

        return existingInstructor;
    }

    @Override
    public Instructor deleteById(Long id) {

        Instructor existingInstructor = findById(id);

        if (existingInstructor == null) {
            return null;
        }

        instructors.remove(existingInstructor);

        return existingInstructor;
    }
}
