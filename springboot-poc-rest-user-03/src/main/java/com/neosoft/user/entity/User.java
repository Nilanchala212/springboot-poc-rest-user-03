package com.neosoft.user.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER")
public class User {

	public User(User user) {
		this.age = user.getAge();
		this.city = user.getCity();
		this.country = user.getCountry();
		this.dob = user.getDob();
		this.doj = user.getDoj();
		this.email = user.getEmail();
		this.firstName = user.getFirstName();
		this.desg = user.getDesg();
		this.password = user.getPassword();
		this.pincode = user.getPincode();
		this.role = user.getRole();
		this.state = user.getState();
		this.surName = user.getSurName();
		this.userId = user.getUserId();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;

	@NotNull
	@Size(min = 3, message = "First name should be atleast 3 character")
	private String firstName;

	// @NotNull
	@Size(min = 3, message = "Sur name should be atleast 3 character")
	private String surName;
	// @NotNull
	private int age;
//	@Email
	private String email;
	private String desg;
	// @NotNull
	private Long pincode;
	private String city;
	private String state;
	private String country;

	private boolean isDeleted;
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dob;
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")

	private Date doj;

	private String role;
	private String password;
	private String username;

	public User(int userId,
			@NotNull @Size(min = 3, message = "First name should be atleast 3 character") String firstName,
			@Size(min = 3, message = "Sur name should be atleast 3 character") String surName, int age) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.surName = surName;
		this.age = age;
	}

}
