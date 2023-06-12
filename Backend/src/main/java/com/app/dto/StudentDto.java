package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
	private String name;
	private int id;
	private String role;
	private int courseId;
	private String courseName;
	
	public StudentDto(String name, int id) {
		super();
		this.name = name;
		this.id = id;
	}

	public StudentDto(String name, int id, String role) {
		super();
		this.name = name;
		this.id = id;
		this.role = role;
	}

	public StudentDto(String name, int id, int courseId) {
		super();
		this.name = name;
		this.id = id;
		this.courseId = courseId;
	}



	
}
