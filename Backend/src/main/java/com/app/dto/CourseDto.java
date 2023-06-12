package com.app.dto;

import java.util.List;

public class CourseDto {
	private String courseName;
	private int capacity;
	private List<String> subjects;
	private int courseId;

	public CourseDto(String courseName, int courseId) {
		this.courseName = courseName;
		this.courseId = courseId;
	}

	public List<String> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<String> subjects) {
		this.subjects = subjects;
	}

	public String getCourseName() {
		return courseName;
	}

	public int getCapacity() {
		return capacity;
	}
}
