package com.example.course_management.service.impl;

import com.example.course_management.model.Course;
import com.example.course_management.repository.ICourseRepository;
import com.example.course_management.service.ICourseService;
import com.example.course_management.service.IInstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService {
    private final ICourseRepository courseRepository;
    private final IInstructorService instructorService;

    @Autowired
    public CourseServiceImpl(ICourseRepository courseRepository, IInstructorService instructorService) {
        this.courseRepository = courseRepository;
        this.instructorService = instructorService;
    }

    @Override
    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found."));
    }

    @Override
    public Course createCourse(Course course) {

        try {
            instructorService.getInstructorById(course.getInstructorId());
        } catch (RuntimeException e) {
            throw new RuntimeException("Instructor of course not found.");
        }

        return courseRepository.create(course)
                .orElseThrow(() -> new RuntimeException("Course cannot be created."));
    }

    @Override
    public Course updateCourse(Long id, Course course) {

//        Course existingCourse = courseRepository.findById(id);
//
//        if (existingCourse == null) {
//            return null;
//        }

        try {
            instructorService.getInstructorById(course.getInstructorId());
        } catch (RuntimeException e) {
            throw new RuntimeException("Instructor of course not found.");
        }

        return courseRepository.update(id, course)
                .orElseThrow(() -> new RuntimeException("Course not found."));
    }

    @Override
    public Course deleteCourseById(Long id) {

//        Course existingCourse = courseRepository.findById(id);
//
//        if (existingCourse == null) {
//            return null;
//        }

        return courseRepository.deleteById(id)
                .orElseThrow(() -> new RuntimeException("Course not found."));
    }

}
