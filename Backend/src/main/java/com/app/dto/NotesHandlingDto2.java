package com.app.dto;

import java.time.LocalDate;

import com.app.pojos.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
public class NotesHandlingDto2 {
	
	private int subjectId;
	private LocalDate uploadDate;
	private String notesFile;
	//private int size;

}
