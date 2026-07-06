package com.example.course_management.repository.impl;

import com.example.course_management.enums.CourseStatus;
import com.example.course_management.model.Course;
import com.example.course_management.repository.ICourseRepository;
import com.example.course_management.utils.IdGenerator;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Course> findAll() {
        return courses;
    }

    @Override
    public Optional<Course> findById(Long id) {
        return courses.stream()
                .filter(course -> course.getId().equals(id))
                .findFirst();
    }

    private final IdGenerator<Course> idGenerator = new IdGenerator<>(courses);

    @Override
    public Optional<Course> create(Course course) {
        course.setId(idGenerator.next());
        courses.add(course);
        return Optional.of(course);
    }

    @Override
    public Optional<Course> update(Long id, Course course) {

        Optional<Course> existingCourseOpt = findById(id);

        existingCourseOpt.ifPresent(existingCourse -> {
            existingCourse.setTitle(course.getTitle());
            existingCourse.setStatus(course.getStatus());
            existingCourse.setInstructorId(course.getInstructorId());
        });

        return existingCourseOpt;
    }

    @Override
    public Optional<Course> deleteById(Long id) {

        Optional<Course> existingCourseOpt = findById(id);

        existingCourseOpt.ifPresent(courses::remove);

        return existingCourseOpt;
    }
}
