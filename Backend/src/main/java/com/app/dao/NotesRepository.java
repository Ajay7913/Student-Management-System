package com.app.dao;

import java.time.LocalDate;
import java.util.Optional;

import com.app.pojos.Notes;
import com.app.pojos.Subject;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NotesRepository extends JpaRepository<Notes, Integer>{
	Optional <Notes> findByUploadDateAndSubjectId(LocalDate uploadDate, Subject subjectId);
}
