package com.app.service;

import com.app.dto.StudentMarksAttendanceDto;
import com.app.pojos.Course;
import com.app.pojos.Student;
import com.app.pojos.StudentMarksAttendance;
import com.app.pojos.Subject;

import java.util.List;

public interface IStudentMarksAttendanceService {

	public StudentMarksAttendance getStudentMarksAttendanceBySubjectIdCourseIdStudentId(Subject subjectId,
			Course courseId, Student studentId);

	public void insertNewAttendanceAndMarks(StudentMarksAttendance transientSMA);

	public void updateAttendanceAndMarks(StudentMarksAttendanceDto smaDto, Subject subject, Course course,
			Student student);
	//tushar
	List<StudentMarksAttendance> getMarksByStudentId(Student student);
	//tushar

	//akruti
	public List<StudentMarksAttendance> getStudentMarksAttendanceListByStudentId(Student studentId);
}
