package com.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.CourseRepository;
import com.app.dao.SubjectRepository;
import com.app.pojos.Course;
import com.app.pojos.Subject;

@Service
@Transactional
public class CourseServiceImpl implements  ICourseService{

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    
    @Override
    public void addNewCourse(Course course) {
        courseRepository.save(course);
    }
    
     @Override
    public void addSubject(Course course , Subject subject) {
        //first add subject in the list of subject
        course.addSubject(subject);
			subjectRepository.save(subject);
    }

	@Override
	public List<String> getAllCourseNameList() {
		List<Course> courseList = courseRepository.findAll();
		List<String> courseNameList = new ArrayList<String>();
		for (Course course : courseList) {
			courseNameList.add(course.getCourseName());
		}
		return courseNameList;
	}

	@Override
	public Course getCourse(String courseName) {
		return courseRepository.findByCourseName(courseName).orElseThrow(() -> new RuntimeException("Course not found"));
	}

	@Override
	public List<Course> getAllCourses() {
		
		return courseRepository.findAll();
	}

	@Override
	public Course getCourseById(int courseId) {
		return courseRepository.getById(courseId).orElseThrow(()->new RuntimeException("Course not found"));
	}
	
}
