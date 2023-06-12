package com.app.dto;

import com.app.pojos.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto2 {
	private String image;
	private UserDetails userDetails;
	private String course;
	private String email;
	private String password;

	public StudentDto2(String image, UserDetails userDetails, String course) {
		this.image = image;
		this.userDetails = userDetails;
		this.course = course;
	}
}
