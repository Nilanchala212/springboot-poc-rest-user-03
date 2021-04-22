package com.neosoft.user.response;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class UserResponse {
	private String message;
	private HttpStatus status;
	private Object body;

	public UserResponse(String message, HttpStatus status, Object body, Date timeStamp) {
		super();
		this.message = message;
		this.status = status;
		this.body = body;
	}

	public UserResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}



}
