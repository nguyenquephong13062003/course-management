package com.example.course_management.service.impl;

import com.example.course_management.repository.IInstructorRepository;
import com.example.course_management.service.IInstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructorServiceImpl implements IInstructorService {
    private final IInstructorRepository instructorRepository;

    @Autowired
    public InstructorServiceImpl(IInstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

}
