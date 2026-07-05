package com.example.course_management.repository.impl;

import com.example.course_management.enums.CourseStatus;
import com.example.course_management.model.Course;
import com.example.course_management.repository.ICourseRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseRepositoryImpl implements ICourseRepository {
    private final List<Course> courses = new ArrayList<>();

    public CourseRepositoryImpl() {
        courses.add(new Course(
                1L,
                "Java Spring Boot",
                CourseStatus.ACTIVE,
                1L));

        courses.add(new Course(
                2L,
                "Java Core",
                CourseStatus.INACTIVE,
                2L));
    }

}
