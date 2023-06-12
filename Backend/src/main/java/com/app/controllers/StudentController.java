package com.app.controllers;


import com.app.dto.*;
import com.app.pojos.*;
import com.app.service.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.app.pojos.Notes;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin
public class StudentController {
    @Autowired
    private IScheduleService scheduleService;
    @Autowired
    private IStudentMarksAttendanceService studentMarksAttendanceService;
    @Autowired
    private IStudentService studentService;
    @Autowired
    private IStudentMarksAttendanceService smaService;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private IUserService userService;
    @Autowired
    private INotesService notesService;
    @Autowired
    private ISubjectService subjectService;

    @GetMapping("/student_details/{studentId}")
    public StudentDto2 getStudentDetailsById(@PathVariable int studentId) {
        Student student = studentService.getStudentById(studentId);
        StudentDto2 stdto = new StudentDto2(student.getImage(),student.getUserDetails(),student.getCourse().getCourseName());
        return stdto;
    }

    //GET STUDENT DETAILS BY STUDENT ID
    @GetMapping("/get_student_details_by_studentId")
    public ResponseEntity<?> getStudentDetailsByStudentId(@RequestParam int studentId) {
        Student student = studentService.getStudentById(studentId);
        StudentDto2 studentDto2 = new StudentDto2(student.getImage(),student.getUserDetails(),student.getCourse().getCourseName(), student.getRegistrationId().getEmailId(), student.getRegistrationId().getPassword());
        return new ResponseEntity<>(studentDto2, HttpStatus.OK);
    }

    @GetMapping("/view_schedule")
    public List<?> getSessionLinks() throws Exception{

        List<Schedule> l1 = scheduleService.findByDate(LocalDate.now());
        if(l1.size()==0){
            throw new Exception("SCHEDULE RECORD NOT FOUND FOR THIS DATE");
        }
        List<MyScheduleDto> sessionList = new ArrayList<>();

        for(int i=0; i<l1.size(); i++){
            MyScheduleDto myScheduleDto = new MyScheduleDto(
                    l1.get(i).getDate(), l1.get(i).getPassword(),l1.get(i).getSessionLink(),
                    l1.get(i).getSubjectName(),l1.get(i).getTime(),l1.get(i).getFacultyId().getId(),
                    l1.get(i).getSubjectId().getId(), l1.get(i).getRecordedSessionLink(),
                    l1.get(i).getRecordedSessionPassword()
            );
            sessionList.add(myScheduleDto);
        }
        return sessionList;
    }

    @GetMapping("/get_subject_marks")
    public ResponseEntity<?> getSubjectMarks (@RequestParam int studentId)throws Exception{
        System.out.println("studentId " + studentId);
        List<StudentMarksAttendance> studentMarksList = studentMarksAttendanceService.getMarksByStudentId(studentService.getStudentById(studentId));

        StudentMarksAttendanceDto smaDto = new StudentMarksAttendanceDto();
        if(studentMarksList.size() ==0){
            smaDto.setMessage("No MARKS RECORDS AVAILABLE FOR THIS STUDENT");
            throw new Exception("No MARKS RECORDS AVAILABLE FOR THIS STUDENT");
        }
        List<StudentMarksAttendanceDto> smaDtoList = new ArrayList<>();
        for(StudentMarksAttendance smaRecord : studentMarksList){
            StudentMarksAttendanceDto smaDto1 = new StudentMarksAttendanceDto();
            smaDto1.setMarks(smaRecord.getMarks());
            smaDto1.setSubjectName(smaRecord.getSubjectId().getSubjectName());
            smaDto1.setCourseName(smaRecord.getCourseId().getCourseName());
            smaDtoList.add(smaDto1);
        }
        return new ResponseEntity<>(smaDtoList, HttpStatus.OK);
    }

    @GetMapping("/get_subject_attendance")
    public ResponseEntity<?> getSubjectWiseAttendanceByRegId(@RequestParam int studentId) throws Exception
    {
        System.out.println("Hii");
        //get the whole sma object
        List<StudentMarksAttendance> studentMarksAttendanceList=smaService.getStudentMarksAttendanceListByStudentId(studentService.getStudentById(studentId));
        if(studentMarksAttendanceList.size() == 0)
        {
            throw new Exception("NO ATTENDANCE RECORDS AVAILABLE FOR THIS STUDENT");
        }
        System.out.println(studentMarksAttendanceList.toString());
        List<StudentMarksAttendanceDto> smaDtoList=new ArrayList<StudentMarksAttendanceDto>();
        for(StudentMarksAttendance smaDtoRecord: studentMarksAttendanceList  )
        {
            StudentMarksAttendanceDto smaDto2=new StudentMarksAttendanceDto();
            smaDto2.setAttendance(smaDtoRecord.getAttendance());
            smaDto2.setSubjectName(smaDtoRecord.getSubjectId().getSubjectName());
            smaDto2.setCourseName(smaDtoRecord.getCourseId().getCourseName());
            smaDtoList.add(smaDto2);
        }
        return new ResponseEntity<>(smaDtoList,HttpStatus.OK);

    }

    @PostMapping("/update_student")
    // add REST API end point to create new emp
    //@RequestParam String productDetails, @RequestParam MultipartFile imageFile
    public ResponseEntity<?> updateStudent(@RequestParam String tUser, @RequestParam MultipartFile imageFile) throws IOException {
        UserDto transientUser = mapper.readValue(tUser, UserDto.class);
        System.out.println("in update user " + transientUser.getUserDetails());
        System.out.println("transientUser" + transientUser);
        userService.updateUserDetails(transientUser, imageFile, transientUser.getStudentId());
        return new ResponseEntity<>("Student added successfully", HttpStatus.OK);
    }

    @PostMapping("/view_notes")
    public NotesHandlingDto2 getNotesBySubjectIdAndDate(@RequestBody NotesHandlingDto transientNotes) {
    	
    	Notes notes = notesService.getNotesBySubjectIdAndUploadDate(subjectService.getSubjectId(transientNotes.getSubjectId()), transientNotes.getUploadDate());
        
        NotesHandlingDto2 notesDto2 = new NotesHandlingDto2(notes.getSubjectId().getId(),notes.getUploadDate(),notes.getNotes());
        return notesDto2;
    }

}
