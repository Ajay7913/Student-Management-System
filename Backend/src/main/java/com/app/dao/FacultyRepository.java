package com.app.dao;

import java.util.Optional;

import com.app.pojos.Student;
import com.app.pojos.Subject;
import com.app.pojos.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.pojos.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Integer>{
	@Query(value = "select * from faculties where name =?1", nativeQuery = true)
	public Optional<Faculty> getFacultyByFacultyName(String facultyName);

	@Query(value = "select * from faculties f left outer join subjects s on s.subject_name =?1", nativeQuery = true)
	Optional< Faculty> getFacultyBySubjectName(String subjectName);

	Optional<Faculty> getFacultyBySubject(Subject subject);

//	Optional<Faculty> findByEmailIdAndPassword(String emailId, String password);
	Optional<Faculty> findByRegistrationId(User user);
}
