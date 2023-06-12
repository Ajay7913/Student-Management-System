package com.app.pojos;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "faculties")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Faculty extends BaseEntity {
	@OneToOne
	@JoinColumn(name = "reg_id")
	private User registrationId;
	@Embedded
	private UserDetails userDetails;
	@OneToOne
	@JoinColumn(name="subject_id")
    private Subject subject;
	
	@Override
	public String toString() {
		return "Faculty [registrationId=" + registrationId + ", userDetails=" + userDetails + ", subject=" + subject
				+ "]";
	}
	
	public Faculty(UserDetails userDetails) {
		super();
		this.userDetails = userDetails;
	}
	
	public void setSubject(Subject subject) {
		this.subject = subject;
		subject.setFacultyId(this);
	}
	
	public void setUser(User user) {
		this.registrationId = user;
	}
}
