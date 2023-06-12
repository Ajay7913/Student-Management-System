package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.app.pojos.Course;
import com.app.pojos.Student;
import com.app.pojos.User;

public interface IStudentService {
	public String addStudent(MultipartFile file,Student student, String courseName, User user);

	public List<Student> getStudentsByCourseId(Course course);

	public Student getStudentById(int studentId);


//	public Student findByEmailIdAndPassword(String emailId, String password);

	public Student findByRegistrationId(User u);
	
}