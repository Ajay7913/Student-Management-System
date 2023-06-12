package com.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Course;
import com.app.pojos.Student;
import com.app.pojos.StudentMarksAttendance;
import com.app.pojos.Subject;

public interface StudentAttendanceMarksRepository extends JpaRepository<StudentMarksAttendance, Integer>{
	Optional<StudentMarksAttendance> findBySubjectIdAndCourseIdAndStudentId(Subject subjectId,Course courseId,Student studentId);
	//tushar
	List<StudentMarksAttendance> findByStudentId(Student student);
	//tushar

}
