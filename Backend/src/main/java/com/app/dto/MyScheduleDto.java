package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class MyScheduleDto {

    private LocalDate date;
    private String password;
    private String link;
    private String subjectName;
    private String time;
    private int facultyId;
    private int subjectId;
    private String recordedSessionLink;
    private String recordedPassword;

}