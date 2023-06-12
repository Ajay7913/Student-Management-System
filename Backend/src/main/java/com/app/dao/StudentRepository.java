package com.app.dao;

import java.util.List;
import java.util.Optional;

import com.app.pojos.User;
import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Course;
import com.app.pojos.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{	
	public List<Student> findByCourse(Course course);
	Optional<Student> getById(int studentId);
//	Optional<Student> findByEmailIdAndPassword(String emailId, String password);
	Optional<Student> findByRegistrationId(User u);

}
