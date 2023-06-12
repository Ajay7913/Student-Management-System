package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

	@Column(name = "email_id",length = 30,unique = true)
	private String emailId;
	@Column(length = 30)
	private String password;
	@Column()
	@Enumerated(EnumType.STRING)
	private Role role;
	@Column(name = "reg_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long registrationId;

	@Override
	public String toString() {
		return "User [emailId=" + emailId + ", password=" + password + ", role=" + role + ", registrationId="
				+ registrationId + "]";
	}

	//cons
	public User(String emailId, String password, Role role) {
		this.emailId = emailId;
		this.password = password;
		this.role = role;
	}

	public
	User(long registrationId){
		this.registrationId = registrationId;
	}
}
