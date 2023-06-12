package com.app.service;

import java.time.LocalDate;

import com.app.pojos.Notes;
import com.app.pojos.Subject;

import org.springframework.web.multipart.MultipartFile;

import com.app.dto.NotesHandlingDto;

public interface INotesService {

	public String addNotes(MultipartFile file,NotesHandlingDto notes);
	
	public Notes getNotesBySubjectIdAndUploadDate(Subject subjectId, LocalDate uploadDate);
}
