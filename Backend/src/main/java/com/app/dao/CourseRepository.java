package com.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>{
	Optional<Course> findByCourseName(String courseName);
	List<Course> findAll();
	Optional<Course> getById(int courseId);
}
