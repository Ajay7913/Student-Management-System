package com.app.service;

import com.app.dto.UserDto;
import com.app.pojos.Student;
import com.app.pojos.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.multipart.MultipartFile;

import java.lang.annotation.Native;

public interface IUserService {
	public User insertUserDetails(User transientUser);

	public User getUserByEmailAndPassword(String email, String password);

	public void updateUserDetails(UserDto transientUser, MultipartFile imageFile, int studentId);
}
