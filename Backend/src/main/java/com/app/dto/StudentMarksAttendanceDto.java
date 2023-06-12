package com.app.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class StudentMarksAttendanceDto {
	
	private int subjectId;	
	private int courseId;
	private int studentId;
	private double marks;
	private double attendance;
	//tushar
	private String courseName;
	private String subjectName;
	private String message;
	//tushar


	public StudentMarksAttendanceDto(int courseId, double marks, double attendance, String subjectName, int subjectId, int studentId) {
		this.courseId = courseId;
		this.marks = marks;
		this.attendance = attendance;
		this.subjectName = subjectName;
		this.subjectId = subjectId;
		this.studentId = studentId;
	}
}
