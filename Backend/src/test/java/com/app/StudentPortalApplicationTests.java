package com.app;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.app.dao.StudentAttendanceMarksRepository;
import com.app.pojos.Admin;
import com.app.pojos.Faculty;
import com.app.pojos.Role;
import com.app.pojos.Schedule;
import com.app.pojos.Student;
import com.app.pojos.StudentMarksAttendance;
import com.app.pojos.Subject;
import com.app.pojos.User;
import com.app.pojos.UserDetails;
import com.app.service.IAdminService;
import com.app.service.ICourseService;
import com.app.service.IScheduleService;
import com.app.service.IStudentMarksAttendanceService;
import com.app.service.ISubjectService;
import com.app.service.IUserService;
import com.app.service.StudentServiceImpl;

@SpringBootTest
class StudentPortalApplicationTests {

	@Autowired
	IAdminService adminService;
	
	@Autowired
	IUserService userService;
	
	@Autowired
	ICourseService courseService;
	
	@Autowired
	ISubjectService subjectService;
	
	@Autowired
	StudentAttendanceMarksRepository samRepo;
	
	
	@Autowired
	StudentServiceImpl studentService;
	
	@Autowired
	IStudentMarksAttendanceService studMarkAttService;
	
	@Autowired
	IScheduleService scheduleService;
	
	//Admin service
	
	@Test//done testing... add new record evertime before running this test case
	void AddAdminTestCase(){
		User user = new User("bharat@gmail.com","bharat",Role.valueOf("ADMIN"));
		userService.insertUserDetails(user);
		UserDetails userDetails = new UserDetails("bharat",87964285,"pune","MH","IN","411028");
		Admin admin = new Admin(user,userDetails);
		String result = adminService.addAdmin(admin, user);
		assertEquals("Admin added successfully", result);
		
	}
	
	@Test//done testing
	void findByRegistrationId() {
		User u =  new User("a@gmail.com","k",Role.valueOf("ADMIN"),3);
		Admin admin = adminService.findByRegistrationId(u);
		assertEquals("kunal", admin.getUserDetails().getName());
	}
	
	//Faculty Service
	
	@Test//done... add new record evertime before running this test case
	void addFaculty() {
		User user = new User("ram@gmail.com","ram",Role.valueOf("FACULTY"));
		userService.insertUserDetails(user);
		UserDetails userDetails = new UserDetails("ram",87964289,"pune","MH","IN","411028");
		Subject subject = subjectService.getSubject("OS");
		Faculty faculty = new Faculty(user,userDetails,subject);
		assertEquals("ram", faculty.getUserDetails().getName());
	}

	@Test //done
	void getMarksByStudentId() {
		Student s1= studentService.getStudentById(1);
		List<StudentMarksAttendance> l1 = studMarkAttService.getMarksByStudentId(s1);
		assertEquals(39, l1.get(0).getMarks());
		
	}
	
	@Test
	void viewSchedule() {
		List<Schedule> l1= scheduleService.findByDate(LocalDate.now());
		assertEquals	("JAVA", l1.get(0).getSubjectName());
	}
	
}

