package com.app.service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;

import javax.transaction.Transactional;

import com.app.pojos.Notes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.app.dao.NotesRepository;
import com.app.dao.SubjectRepository;
import com.app.dto.NotesHandlingDto;
import com.app.pojos.Subject;
@Service
@Transactional
public class NotesServiceImpl implements INotesService {

	@Autowired
	NotesRepository notesRepo;
	@Autowired
	SubjectRepository subjectRepo;
	@Override
	public String addNotes(MultipartFile file, NotesHandlingDto transientNotes) {
		
		String fileName=StringUtils.cleanPath(file.getOriginalFilename());
		if(fileName.contains("..")) {
			System.out.println("not a valid file");
		}
		try {

			//student.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
			
			Subject subject = subjectRepo.getById(transientNotes.getSubjectId()).orElseThrow(()-> new RuntimeException("Subject Not Found"));
			Notes notes = new Notes();
			notes.setSubjectId(subject);
			notes.setUploadDate(transientNotes.getUploadDate());
			notes.setNotes(Base64.getEncoder().encodeToString(file.getBytes()));
			notesRepo.save(notes);
			 
		}
		catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "Notes Added Successfully";
	}
	@Override
	public Notes getNotesBySubjectIdAndUploadDate(Subject subjectId, LocalDate uploadDate) {
		return notesRepo.findByUploadDateAndSubjectId(uploadDate,subjectId).orElseThrow(()-> new RuntimeException(" Notes Not Found For "+uploadDate));
	}
}
