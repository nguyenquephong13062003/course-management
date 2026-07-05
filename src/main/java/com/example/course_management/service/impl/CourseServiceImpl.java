package com.example.course_management.service.impl;

import com.example.course_management.model.Course;
import com.example.course_management.model.Instructor;
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
        return courseRepository.findById(id);
    }

    @Override
    public Course createCourse(Course course) {

        Instructor instructor = instructorService.getInstructorById(course.getInstructorId());

        if (instructor == null) {
            return null;
        }

        return courseRepository.create(course);
    }

    @Override
    public Course updateCourse(Long id, Course course) {

//        Course existingCourse = courseRepository.findById(id);
//
//        if (existingCourse == null) {
//            return null;
//        }

        Instructor instructor = instructorService.getInstructorById(course.getInstructorId());

        if (instructor == null) {
            return null;
        }
        return courseRepository.update(id, course);
    }

    @Override
    public Course deleteCourseById(Long id) {

//        Course existingCourse = courseRepository.findById(id);
//
//        if (existingCourse == null) {
//            return null;
//        }

        return courseRepository.deleteById(id);
    }

}
