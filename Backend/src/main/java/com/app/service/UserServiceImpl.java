package com.app.service;

import javax.transaction.Transactional;

import com.app.dto.UserDto;
import com.app.pojos.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.UserRepository;
import com.app.pojos.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private IStudentService studentService;

    @Override
    public User insertUserDetails(User transientUser) {
        return userRepo.save(transientUser);
    }

    @Override
    public User getUserByEmailAndPassword(String email, String password) {
        return userRepo.findByEmailIdAndPassword(email, password).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void updateUserDetails(UserDto transientUser, MultipartFile imageFile, int studentId) {
        Student student = studentService.getStudentById(studentId);
        //Update user
        User user = student.getRegistrationId();
        user.setEmailId(transientUser.getEmailId());
        user.setPassword(transientUser.getEmailId());
        //Update student
        student.setUserDetails(transientUser.getUserDetails());
        try {
            student.setImage(Base64.getEncoder().encodeToString(imageFile.getBytes()));
        } catch (Exception e) {
            throw new RuntimeException("Unable to set Image");
        }
    }
}
