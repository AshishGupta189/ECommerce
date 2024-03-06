package com.example.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Admin{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer adminId;
	
	@NotNull(message =  "username  can't be null")
	private String username;
	
	@NotNull(message =  "email  can't be null")
	private String email;
	
	@NotNull(message =  " mobile can't be null")
	@Column(unique = true)
	@Pattern(regexp = "[6789]{1}[0-9]{9}", message = "Enter valid 10 digit mobile number")
	private String mobileNumber;
	
	@NotNull(message =  "password  can't be null")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Minimum eight characters\r\n"
			+ "At least one uppercase letter\r\n"
			+ "At least one lowercase letter\r\n"
			+ "At least one number\r\n"
			+ "At least one special character (@, $, !, %, *, ?, &)")
	private String password;
	
	private String picture;

	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(Integer adminId, String username, String email, String mobileNumber, String password, String picture) {
		super();
		this.adminId = adminId;
		this.username = username;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.password = password;
		this.picture = picture;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", username=" + username + ", email=" + email + ", mobileNumber="
				+ mobileNumber + ", password=" + password + ", picture=" + picture + "]";
	}
	
	
	

}
