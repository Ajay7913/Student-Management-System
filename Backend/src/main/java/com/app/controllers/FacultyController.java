package com.app.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dto.NotesHandlingDto;
import com.app.service.INotesService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/faculty")
@CrossOrigin
public class FacultyController {
	
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private INotesService notesService;
	
    @PostMapping("/add_notes")
    // add REST API end point to create new emp
    //@RequestParam String productDetails, @RequestParam MultipartFile imageFile
    public ResponseEntity<?> addNewNotes(@RequestParam String tNotesData, @RequestParam MultipartFile notesFile) throws IOException{
        //UserDto transientUser = mapper.readValue(tUser, UserDto.class);
        NotesHandlingDto transientNotes = mapper.readValue(tNotesData, NotesHandlingDto.class);
        System.out.println("transientUser" + transientNotes);
        notesService.addNotes(notesFile, transientNotes);
        // create user object
        return new ResponseEntity<>("Notes added successfully",HttpStatus.OK);
    }
}
