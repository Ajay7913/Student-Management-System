package com.app.pojos;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "admins")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Admin extends BaseEntity {
	@OneToOne
	@JoinColumn(name = "reg_id")
	private User registrationId;
	@Embedded
	private UserDetails userDetails;
	@Override
	public String toString() {
		return "Admin [registrationId=" + registrationId + ", userDetails=" + userDetails + "]";
	}

	public Admin(UserDetails userDetails) {
		super();
		this.userDetails = userDetails;
	}

	public void setUser(User user) {
		this.registrationId = user;
	}
    
}
