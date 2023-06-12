package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "students")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student extends BaseEntity {
	
	@OneToOne
	@JoinColumn(name = "reg_id")
	private User registrationId;
	@Embedded
	private UserDetails userDetails;
	@ManyToOne
	@JoinColumn(name="course_id",nullable = false)
	private Course course;
//	@OneToOne
//	@JoinColumn(name = "attendance")
//	private StudentMarksAttendance attendance;
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private String image;



	@Override
	public String toString() {
		return "Admin [registrationId=" + registrationId + ", userDetails=" + userDetails + ", Image=" + image + "]";
	}

	public void setUser(User user) {
		this.registrationId = user;
	}
	public Student(UserDetails userDetails,int id) {
		super.setId(id);
		this.userDetails = userDetails;
	}
	public Student(UserDetails userDetails)
	{
		this.userDetails=userDetails;
	}

	public Student(int id) {
		super.setId(id);

	}




}