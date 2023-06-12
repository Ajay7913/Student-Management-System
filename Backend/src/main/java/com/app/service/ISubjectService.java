package com.app.service;

import java.util.List;

import com.app.pojos.Subject;

public interface ISubjectService {
	public List<String> getAllSubjectNameList();
	public Subject getSubject(String subjectName);
	public Subject getSubjectId(int subjectId);
}
