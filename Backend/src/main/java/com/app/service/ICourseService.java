package com.app.service;

import java.util.List;

import com.app.pojos.Course;
import com.app.pojos.Subject;

public interface ICourseService {

    public void addNewCourse(Course course);
    public void addSubject(Course course , Subject subject);
    public List<String> getAllCourseNameList();
    public Course getCourse(String courseName);
    public List<Course> getAllCourses();
	public Course getCourseById(int courseId);
}
