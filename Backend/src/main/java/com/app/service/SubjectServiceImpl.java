package com.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.SubjectRepository;
import com.app.pojos.Subject;
@Service
@Transactional
public class SubjectServiceImpl implements ISubjectService {

	@Autowired
	private SubjectRepository subjectRepository;
	
	@Override
	public List<String> getAllSubjectNameList() {
		List<Subject> subjectList = subjectRepository.findAll();
		List<String> subjectNameList = new ArrayList<String>();
		for (Subject subject : subjectList) {
			subjectNameList.add(subject.getSubjectName());
		}
		return subjectNameList;
	}

	@Override
	public Subject getSubject(String subjectName) {
		
		return subjectRepository.findBySubjectName(subjectName).orElseThrow(()->new RuntimeException("subject not found"));
	}

	@Override
	public Subject getSubjectId(int subjectId) {
		return subjectRepository.getById(subjectId).orElseThrow(()->new RuntimeException("subject not found"));
	}
	
}
