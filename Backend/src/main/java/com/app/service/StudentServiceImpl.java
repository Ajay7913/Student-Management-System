package com.app.service;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.app.dao.CourseRepository;
import com.app.dao.StudentRepository;
import com.app.pojos.Course;
import com.app.pojos.Student;
import com.app.pojos.User;

@Service
@Transactional
public class StudentServiceImpl implements IStudentService{

	@Autowired
	private StudentRepository studentRepo;
	@Autowired
	private CourseRepository courseRepo;


	@Override
	public String addStudent(MultipartFile file,Student student, String courseName, User user) {
		//register student under course
		//get persistent course
		Course c1 = courseRepo.findByCourseName(courseName).orElseThrow(()-> new RuntimeException("Course not Found !!"));
		//helper method to link course and student
		student.setUser(user);
		String fileName=StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains("..")) {
			System.out.println("not a valid file");
		}
		try {

			student.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
		}
		catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		c1.addStudent(student);
		//add student in students table
		studentRepo.save(student);
		return "Student added successfully";
	}//

	@Override
	public List<Student> getStudentsByCourseId(Course course) {
		//return studentRepo.getStudentsByCourseId(id);
		return studentRepo.findByCourse(course);
	}

	@Override
	public Student getStudentById(int studentId) {
		Student student =  studentRepo.getById(studentId).orElseThrow(()->new RuntimeException("Student not found"));
		System.out.println("STUDENT//////////" +student);
		student.getImage().getBytes();
		return student;
	}


	@Override
	public Student findByRegistrationId(User u) {
		return studentRepo.findByRegistrationId(u).orElseThrow(()->new RuntimeException("STUDENT NOT FOUND"));
	}

}
