package com.app.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.app.dto.*;
import com.app.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.app.pojos.Admin;
import com.app.pojos.Course;
import com.app.pojos.Faculty;
import com.app.pojos.Role;
import com.app.pojos.Schedule;
import com.app.pojos.Student;
import com.app.pojos.StudentMarksAttendance;
import com.app.pojos.Subject;
import com.app.pojos.User;


@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private IUserService userService;
    @Autowired
    private ICourseService courseService;
    @Autowired
    private IStudentService studentService;
    @Autowired
    private IFacultyService facultyService;
    @Autowired
    private ISubjectService subjectService;
    @Autowired
    private IScheduleService scheduleService;
    @Autowired
    private IStudentMarksAttendanceService smaService;
    @Autowired
    private IAdminService adminService;
    @Autowired
    private INotesService notesService;

    @GetMapping("/get_all_courses")
    public List<?> getAllCourseList() {
        System.out.println("Inside get courses [ REQUEST CAME ]");
        courseService.getAllCourseNameList();
        return courseService.getAllCourseNameList();
    }

    @PostMapping("/add_student")
    // add REST API end point to create new emp
    //@RequestParam String productDetails, @RequestParam MultipartFile imageFile
        public ResponseEntity<?> addNewStudent(@RequestParam String tUser, @RequestParam MultipartFile imageFile) throws IOException{
        UserDto transientUser = mapper.readValue(tUser, UserDto.class);
        System.out.println("in add user " + transientUser.getUserDetails());
        System.out.println("transientUser" + transientUser);
        // create user object
        System.out.println("-----------------------------------------------------");
        System.out.println(transientUser.getRole());
        User user = new User(transientUser.getEmailId(), transientUser.getPassword(),
                Role.valueOf(transientUser.getRole()));
        userService.insertUserDetails(user);
            System.out.println("In student block");
            Student student = new Student(transientUser.getUserDetails());
            System.out.println(studentService.addStudent(imageFile, student, transientUser.getCourseName(), user));
            return new ResponseEntity<>("Student added successfully", HttpStatus.OK);
    }

    @PostMapping("/add_user")
    // add REST API end point to create new emp
    public String addNewUser(@RequestBody UserDto transientUser) {
        System.out.println("in add user " + transientUser.getUserDetails());
        System.out.println("transientUser" + transientUser);
        // create user object
        System.out.println("-----------------------------------------------------");
        System.out.println(transientUser.getRole());
        User user = new User(transientUser.getEmailId(), transientUser.getPassword(),
                Role.valueOf(transientUser.getRole()));
        userService.insertUserDetails(user);
        // check the role of user
        if (transientUser.getRole().equals("FACULTY")) {
            System.out.println("In faculty block");
            // add faculty
            Faculty faculty = new Faculty(transientUser.getUserDetails());
            System.out.println("sub name: " + transientUser.getSubjectName());
            System.out.println(facultyService.addFaculty(faculty, transientUser.getSubjectName(), user));
        } else {
            Admin admin = new Admin(transientUser.getUserDetails());
            System.out.println(adminService.addAdmin(admin, user));
        }
        return "OK";
    }






    @PostMapping("/add_course")
    public ResponseEntity<?> addNewCourse(@RequestBody CourseDto transientCourse) {
        Course c1 = new Course(transientCourse.getCourseName(), transientCourse.getCapacity());
        courseService.addNewCourse(c1);
        // get the list of subjects received from client
        System.out.println("subject list: ");
        transientCourse.getSubjects().forEach(System.out::println);
        // if the list of subjects is not empty then add the subjects in subject table
        if (transientCourse.getSubjects().size() != 0) {
            for (String subjectName : transientCourse.getSubjects()) {
                Subject s1 = new Subject(subjectName);
                courseService.addSubject(c1, s1);
            }
        }
        return new ResponseEntity<>("Successfully added " + transientCourse.getCourseName() + " course \n Subjects add under this course :"
                + transientCourse.getSubjects(),HttpStatus.OK);
    }

    @GetMapping("/update_course/getCourseList")
    public List<String> getAllCourseNameList() {
        // first get the list of courses
        System.out.println("Inside get courses [ REQUEST CAME ]");
        courseService.getAllCourseNameList();
        return courseService.getAllCourseNameList();
    }

    @GetMapping("/get_course_name_and_subject_name_list")
    public List<List<String>> getAllCourseNameListAndSubjectNameList() {
        // first get the list of courses
        List<List<String>> list = new ArrayList<>();
        list.add(courseService.getAllCourseNameList());
        list.add(subjectService.getAllSubjectNameList());
        return list;
    }

    // update course: add new subjects to course
    // duplicate subjects validation pending
    @PostMapping("/update_course")
    public ResponseEntity<?> updateCourse(@RequestBody CourseDto transientCourse) {
        // get the course by courseName
        // add the subject in the list of the course
        // add the subject in the subjects table
        Course course = courseService.getCourse(transientCourse.getCourseName());
        // if the list of subjects is not empty then add the subjects in subject table
        if (transientCourse.getSubjects().size() != 0) {
            for (String subjectName : transientCourse.getSubjects()) {
                Subject s1 = new Subject(subjectName);
                courseService.addSubject(course, s1);
            }
        }
        return new ResponseEntity<>("Subjects updated in course",HttpStatus.OK);
    }

    // add schedule
    @PostMapping("/add_schedule")
    public ResponseEntity<?> addSchedule(@RequestBody ScheduleDto transientSchedule) {
        // Only add unique subject schedule under a date
        List<String> subjectList = scheduleService.getSubjectNamesByDate(transientSchedule.getDate());
        if (subjectList.contains(transientSchedule.getSubject())) {
        	return new ResponseEntity<>( "Already added schedule for this subject",HttpStatus.OK);        
        }
        // get the faculty
        Subject subject = subjectService.getSubject(transientSchedule.getSubject());
        Faculty faculty = facultyService.getFacultyBySubjectName(subject);
        System.out.println("HIIIIIIII" + faculty.toString());
        Schedule schedule = new Schedule(transientSchedule.getDate(), transientSchedule.getTime(),
                transientSchedule.getSubject(), transientSchedule.getLink(), transientSchedule.getPassword());
        scheduleService.addSchedule(schedule, faculty, subject);
        return new ResponseEntity<>( "Added schedule for this subject",HttpStatus.OK);
    }

    @GetMapping("/get_schedule")
    public List<String> getSubjectsByDate(@RequestParam String date) {
        // get schedules
        return scheduleService.getSubjectNamesByDate(LocalDate.parse(date));
    }

    @PostMapping("/update_schedule")
    public ResponseEntity<?> updateSchedule(@RequestBody ScheduleDto transientSchedule) {
        scheduleService.updateSchedule(transientSchedule);
        return new ResponseEntity<>( "Schedule updated successfully",HttpStatus.OK);  
    }


    @GetMapping("/get_students_and_subject_list")
    public List<List<?>> getAllStudentsByCourseName(@RequestParam String courseName) {
        List<List<?>> studentsAndSubjectName = new ArrayList<>();
        List<StudentDto> studentNameAndIdList = new ArrayList<StudentDto>();
        System.out.println("//////" + courseService.getCourse(courseName).getId());
        List<Subject> subjectNameList = courseService.getCourse(courseName).getSubjects();

        List<SubjectDto> subjectNameAndIdList = new ArrayList<SubjectDto>();

        for (int i = 0; i < subjectNameList.size(); i++) {
            subjectNameAndIdList.add(new SubjectDto(subjectNameList.get(i).getId(), subjectNameList.get(i).getSubjectName(), courseService.getCourse(courseName).getId()));
        }

        List<Student> studentIdList = studentService.getStudentsByCourseId(courseService.getCourse(courseName));
        for (int i = 0; i < studentIdList.size(); i++) {
            StudentDto student = new StudentDto(studentIdList.get(i).getUserDetails().getName(),
                    studentIdList.get(i).getId(),courseService.getCourse(courseName).getId());
            studentNameAndIdList.add(student);
        }
        studentsAndSubjectName.add(subjectNameAndIdList);
        studentsAndSubjectName.add(studentNameAndIdList);

        return studentsAndSubjectName;
    }

    @PostMapping("/add_or_update_attendance")
    public ResponseEntity<?> addOrUpdateStudentAttendanceAndMarks(@RequestBody StudentMarksAttendanceDto smaDto) {
        System.out.println("smaDto/////////////////"+smaDto.toString());
        Student student = studentService.getStudentById(smaDto.getStudentId());
        Course course = courseService.getCourseById(smaDto.getCourseId());
        Subject subject = subjectService.getSubjectId(smaDto.getSubjectId());
        try {
            StudentMarksAttendance sma = smaService.getStudentMarksAttendanceBySubjectIdCourseIdStudentId(subject, course, student);
            smaService.updateAttendanceAndMarks(smaDto, subject, course, student);
            return new ResponseEntity<>( "SMA Record updated",HttpStatus.OK);  
        } catch (Exception e) {
            StudentMarksAttendance transientSMA = new StudentMarksAttendance(subject, course, student, smaDto.getMarks(), smaDto.getAttendance());
            System.out.println("transientSMA///////////////"+transientSMA.toString());
            smaService.insertNewAttendanceAndMarks(transientSMA);
            return new ResponseEntity<>( "SMA Record inserted",HttpStatus.OK);
        }
    }
  @PostMapping("/add_attendance_marks")
  public ResponseEntity<?>addStudentAttendanceAndMarks(@RequestBody StudentMarksAttendanceDto smaDto)
  {
      System.out.println("smaDto/////////////////"+smaDto.toString());
      Student student = studentService.getStudentById(smaDto.getStudentId());
      Course course = courseService.getCourseById(smaDto.getCourseId());
      Subject subject = subjectService.getSubjectId(smaDto.getSubjectId());
       try {
           StudentMarksAttendance sma = smaService.getStudentMarksAttendanceBySubjectIdCourseIdStudentId(subject, course, student);
           return new ResponseEntity<>("SMA Found cant insert try update", HttpStatus.OK);
       }
       catch (Exception e) {
           StudentMarksAttendance transientSMA = new StudentMarksAttendance(subject, course, student, smaDto.getMarks(), smaDto.getAttendance());
           System.out.println("transientSMA///////////////" + transientSMA.toString());
           smaService.insertNewAttendanceAndMarks(transientSMA);
           return new ResponseEntity<>("SMA Record inserted", HttpStatus.OK);
       }
  }

    //Controller to fetch the existing marks and attendance
    @GetMapping("/get_marks_and_attendance_by_student_id")
    public ResponseEntity<?> getMarksAndAttendanceByStudentId(@RequestParam int studentId) {
        List<StudentMarksAttendance> studentMarksList = smaService.getMarksByStudentId(studentService.getStudentById(studentId));
        StudentMarksAttendanceDto smaDto = new StudentMarksAttendanceDto();
        if (studentMarksList.size() == 0) {
            smaDto.setMessage("No MARKS RECORDS AVAILABLE FOR THIS STUDENT");
            return new ResponseEntity<>(smaDto, HttpStatus.OK);
        }
        List<StudentMarksAttendanceDto> smaDtoList = new ArrayList<>();
        for (StudentMarksAttendance smaRecord : studentMarksList) {
            StudentMarksAttendanceDto smaDto1 = new StudentMarksAttendanceDto(smaRecord.getCourseId().getId(), smaRecord.getMarks(), smaRecord.getAttendance(), smaRecord.getSubjectId().getSubjectName(), smaRecord.getSubjectId().getId(), smaRecord.getStudentId().getId());
            System.out.println("SUB AND STU ID" + smaRecord.getSubjectId().getId() + smaRecord.getStudentId().getId());
            smaDtoList.add(smaDto1);
        }
        return new ResponseEntity<>(smaDtoList, HttpStatus.OK);
    }

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
