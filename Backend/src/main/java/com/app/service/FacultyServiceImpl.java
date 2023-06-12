package com.app.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.FacultyRepository;
import com.app.dao.SubjectRepository;
import com.app.pojos.Faculty;
import com.app.pojos.Subject;
import com.app.pojos.User;

@Service
@Transactional
public class FacultyServiceImpl implements IFacultyService {

	@Autowired
	private SubjectRepository subjectRepo;
	@Autowired
	private FacultyRepository facultyRepo;

	@Override
	public String addFaculty(Faculty faculty, String subject, User user) {
		// get persistent subject
		Subject s1 = subjectRepo.findBySubjectName(subject)
				.orElseThrow(() -> new RuntimeException("Subject not Found !!"));
		//helper method to link subject and faculty
		faculty.setUser(user);
		faculty.setSubject(s1);
		facultyRepo.save(faculty);
		return "Faculty added successfully";
	}

	
	public Faculty getFaculty(String facultyName) {
		return facultyRepo.getFacultyByFacultyName(facultyName).orElseThrow(()-> new RuntimeException("Faculty not found"));
	}

	@Override
	public Faculty findByRegistrationId(User user) {
		return facultyRepo.findByRegistrationId(user).orElseThrow(()-> new RuntimeException("FACULTY NOY FOUND"));
	}

	@Override
	public Faculty getFacultyBySubjectName(Subject subject) {
		return facultyRepo.getFacultyBySubject(subject).orElseThrow(()-> new RuntimeException("FAC NOT FOUND"));
	}

}
