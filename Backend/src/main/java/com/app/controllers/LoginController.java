package com.app.controllers;


import com.app.dto.AdminDto;
import com.app.dto.FacultyDto;
import com.app.dto.LoginDto;
import com.app.dto.StudentDto;
import com.app.pojos.Admin;
import com.app.pojos.Faculty;
import com.app.pojos.Student;
import com.app.pojos.User;
import com.app.service.IAdminService;
import com.app.service.IFacultyService;
import com.app.service.IStudentService;
import com.app.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IStudentService studentService;

    @Autowired
    private IFacultyService facultyService;

    @Autowired
    private IAdminService adminService;



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto ){

        System.out.println(loginDto);
        User user =userService.getUserByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
        System.out.println(user);
        if(user.getRole().toString().equals("STUDENT")){
            System.out.println("in student");
            User u = new User(user.getRegistrationId());
            Student s =  studentService.findByRegistrationId(u);
            return new ResponseEntity<>(new StudentDto(s.getUserDetails().getName(), s.getId(), "STUDENT", s.getCourse().getId(), s.getCourse().getCourseName()), HttpStatus.OK);
        }
        else if(user.getRole().toString().equals("FACULTY")){
            User u = new User(user.getRegistrationId());
            Faculty f = facultyService.findByRegistrationId(u);
            return new ResponseEntity<>(new FacultyDto(f.getUserDetails().getName(), f.getId(), "FACULTY"), HttpStatus.OK);
        }
        else {
            User u = new User(user.getRegistrationId());
            System.out.println("u" + u);
            System.out.println("reg_id: "+ user.getRegistrationId());
            Admin a = adminService.findByRegistrationId(u);
            System.out.println(a);
            return new ResponseEntity<>(new AdminDto(a.getUserDetails().getName(), a.getId(), "ADMIN"), HttpStatus.OK);
        }

    }

}
