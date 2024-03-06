package com.example.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserSession {
	
	
	@GeneratedValue(strategy =GenerationType.AUTO)
	@Id
	private Integer userSessionId;
	private Integer userId;
	private String sessionId;

	private LocalDateTime sessiontime;
	private String role;
	public UserSession() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserSession(Integer userid, String sessionId, LocalDateTime sessiontime, String role) {
		super();
		this.userId = userid;
		this.sessionId = sessionId;
		this.sessiontime = sessiontime;
		this.role = role;
	}
	
	
	public Integer getUserSessionId() {
		return userSessionId;
	}
	public void setUserSessionId(Integer userSessionId) {
		this.userSessionId = userSessionId;
	}
	public Integer getUserid() {
		return userId;
	}
	public void setUserid(Integer userid) {
		this.userId = userid;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public LocalDateTime getSessiontime() {
		return sessiontime;
	}
	public void setSessiontime(LocalDateTime sessiontime) {
		this.sessiontime = sessiontime;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "UserSession [userSessionId=" + userSessionId + ", userid=" + userId + ", sessionId=" + sessionId
				+ ", sessiontime=" + sessiontime + ", role=" + role + "]";
	}
	
	
	
	
	

}
