package com.app.dto;

import com.app.pojos.UserDetails;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
public class UserDto {
	    private int studentId;
		private String emailId;
		private String password;
		private String role;
		private String courseName;
		private UserDetails userDetails;
		private String subjectName;
}
