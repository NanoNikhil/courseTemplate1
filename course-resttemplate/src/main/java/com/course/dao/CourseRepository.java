package com.course.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.course.entity.Course;
public interface CourseRepository extends JpaRepository<Course, Integer> {

}
