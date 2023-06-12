package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.*;

@Embeddable
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {
	@Column(length = 30)
	private String name;
	@Column(length = 30, name = "phone_number",unique = true)
	private long phoneNumber;
	@Column(length = 30)
	private String city;
	@Column(length = 30)
	private String state;
	@Column(length = 30)
	private String country;
	@Column(length = 30, name = "zip_code")
	private String zipCode;


	/*
	* name
	* phoneNumber
	* city
	* state
	* country
	* zipCode
	* */

}
