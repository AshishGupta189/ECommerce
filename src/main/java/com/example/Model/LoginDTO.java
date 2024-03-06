package com.example.Model;

public class LoginDTO {

	private String phonenumber;
	private String password;
	public LoginDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoginDTO(String phonenumber, String password) {
		super();
		this.phonenumber = phonenumber;
		this.password = password;
	}
	@Override
	public String toString() {
		return "LoginDTO [phonenumber=" + phonenumber + ", password=" + password + "]";
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
