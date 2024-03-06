package com.example.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	
	@NotNull(message =  "username  can't be null")
	private String userName;
	
	@NotNull(message =  "email can't be null")
	@Email
	private String userEmail;
	
	@NotNull(message =  "password can't be null")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Minimum eight characters\r\n"
			+ "At least one uppercase letter\r\n"
			+ "At least one lowercase letter\r\n"
			+ "At least one number\r\n"
			+ "At least one special character (@, $, !, %, *, ?, &)")
	private String password;
	@NotNull(message =  "mobilenumber can't be null")
	@Column(unique = true)
	@Pattern(regexp = "[6789]{1}[0-9]{9}", message = "Enter valid 10 digit mobile number")
	private String mobileNumber;
	
	@NotNull(message =  "Address  can't be null")
	private String address;
	
	
	private String picture;
	public User() {
	}
	
	public User(Integer userId, String userName, String userEmail, String password, String mobileNumber, String address,
			String picture, Cart cart, List<Orders> orders) {
		super();
		//this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.picture = picture;
		this.cart = cart;
		this.orders=orders;
	}

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	
	


	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userEmail=" + userEmail + ", password="
				+ password + ", mobileNumber=" + mobileNumber + ", address=" + address + ", picture=" + picture
				+ ", cart=" + cart + ", orders=" + orders + "]";
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}





	@OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
	private Cart cart;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Orders> orders=new ArrayList<>();
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	
	
	
	
}
