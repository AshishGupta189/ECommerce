package com.example.Exceptions;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class MyErrorDetails {

	public MyErrorDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	private LocalDateTime timestamp;
	private String message;
	private String details;
	
	public MyErrorDetails(LocalDateTime timestamp,String message,String details) {
		this.timestamp=timestamp;
		this.details=details;
		this.message=message;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "MyErrorDetails [timestamp=" + timestamp + ", message=" + message + ", details=" + details + "]";
	}
	
	
}