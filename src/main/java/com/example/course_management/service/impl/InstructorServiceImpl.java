package com.example.course_management.service.impl;

import com.example.course_management.model.Instructor;
import com.example.course_management.repository.IInstructorRepository;
import com.example.course_management.service.IInstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorServiceImpl implements IInstructorService {
    private final IInstructorRepository instructorRepository;

    @Autowired
    public InstructorServiceImpl(IInstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    @Override
    public List<Instructor> getAllInstructor() {
        return instructorRepository.findAll();
    }

    @Override
    public Instructor getInstructorById(Long id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor not found."));
    }

    @Override
    public Instructor createInstructor(Instructor instructor) {
        return instructorRepository.create(instructor)
                .orElseThrow(() -> new RuntimeException("Instructor cannot be created."));
    }

    @Override
    public Instructor updateInstructor(Long id, Instructor instructor) {

//        Instructor existingInstructor = instructorRepository.findById(id);
//
//        if (existingInstructor == null) {
//            return null;
//        }

        return instructorRepository.update(id, instructor)
                .orElseThrow(() -> new RuntimeException("Instructor not found."));
    }

    @Override
    public Instructor deleteInstructorById(Long id) {

//        Instructor existingInstructor = instructorRepository.findById(id);
//
//        if (existingInstructor == null) {
//            return null;
//        }

        return instructorRepository.deleteById(id)
                .orElseThrow(() -> new RuntimeException("Instructor not found."));
    }

}
