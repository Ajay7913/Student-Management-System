package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.StudentAttendanceMarksRepository;
import com.app.dto.StudentMarksAttendanceDto;
import com.app.pojos.Course;
import com.app.pojos.Student;
import com.app.pojos.StudentMarksAttendance;
import com.app.pojos.Subject;

import java.util.List;

@Service
@Transactional
public class StudentMarksAttendanceServiceImpl implements IStudentMarksAttendanceService {

	@Autowired
	StudentAttendanceMarksRepository samRepo;
//	@Autowired
//	IStudentMarksAttendanceService smaService;

	@Override
	public StudentMarksAttendance getStudentMarksAttendanceBySubjectIdCourseIdStudentId(Subject subjectId,
			Course courseId, Student studentId) {

		return samRepo.findBySubjectIdAndCourseIdAndStudentId(subjectId, courseId, studentId)
				.orElseThrow(() -> new RuntimeException("Student Marks Attendance not found"));
	}

	@Override
	public void insertNewAttendanceAndMarks(StudentMarksAttendance transientSMA) {

		samRepo.save(transientSMA);

	}

	@Override
	public void updateAttendanceAndMarks(StudentMarksAttendanceDto smaDto, Subject subject, Course course,
			Student student) {
		StudentMarksAttendance sma = this.getStudentMarksAttendanceBySubjectIdCourseIdStudentId(subject, course,
				student);
		sma.setAttendance(smaDto.getAttendance());
		sma.setMarks(smaDto.getMarks());

	}
	//tushar
	@Override
	public List<StudentMarksAttendance> getMarksByStudentId(Student student) {
		return samRepo.findByStudentId(student);
	}
	//tushar

	//akruti
	@Override
	public List<StudentMarksAttendance> getStudentMarksAttendanceListByStudentId(Student studentId) {
		return samRepo.findByStudentId(studentId);
	}

}
