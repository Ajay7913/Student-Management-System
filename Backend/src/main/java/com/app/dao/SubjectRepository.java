package com.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.pojos.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer>{
	Optional<Subject> findBySubjectName(String subjectName);
	List<Subject> findAll();
	Optional<Subject> getById(int subjectId);
	

}
