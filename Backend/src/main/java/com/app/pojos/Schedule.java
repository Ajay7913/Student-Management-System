package com.app.pojos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "schedule")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Schedule extends BaseEntity{
	@OneToOne
	@JoinColumn(name="subject_id",nullable = false)
    private Subject subjectId;
	@OneToOne
	@JoinColumn(name="faculty_id",nullable = false)
    private Faculty facultyId;
    private LocalDate date;
    private String time;
    @Column(name="subject_name",length=30)
    private String subjectName;
    @Column(name="session_link",length=100)
    private String sessionLink;
    @Column(length=30)
    private String password;
    @Column(name="recorded_session_link",length=100)
    private String recordedSessionLink;
    @Column(name="recorded_session_password",length=30)
    private String recordedSessionPassword;
    
    public void setFaculty(Faculty faculty)
    {
    	this.facultyId=faculty;
    }
    public void setSubject(Subject subject)
    {
    	this.subjectId=subject;
    }
	public Schedule(LocalDate date, String time, String subjectName, String sessionLink, String password) {
		super();
		this.date = date;
		this.time = time;
		this.subjectName = subjectName;
		this.sessionLink = sessionLink;
		this.password = password;
	}
 
}
