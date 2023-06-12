package com.app.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
@Getter
public class ScheduleDto {
	private String courseName;
	private LocalDate date;
	private String time;
	private String subject;
	private String link;
	private String password;
}
