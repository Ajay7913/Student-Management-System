package com.app.service;

import com.app.pojos.Faculty;
import com.app.pojos.Subject;
import com.app.pojos.User;

import java.util.Optional;

public interface IFacultyService {
	public String addFaculty(Faculty faculty, String subject,User user);
	public Faculty getFaculty(String facultyName);
	public Faculty findByRegistrationId(User user);
	public Faculty getFacultyBySubjectName(Subject subject);
}
