package com.neosoft.user.globalexception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.neosoft.user.exception.InvalidId;
import com.neosoft.user.exception.InvalidName;
import com.neosoft.user.exception.InvalidUser;
import com.neosoft.user.response.UserResponse;
@ControllerAdvice
public class GlobalExceptionhandler {
	@ExceptionHandler(InvalidUser.class)
	public ResponseEntity<?> invalidUserHandling(InvalidUser exception, WebRequest webRequest) {
		UserResponse userResponse = new UserResponse(exception.getMessage(), HttpStatus.BAD_REQUEST,
				webRequest.getDescription(false), new Date());
		return new ResponseEntity<>(userResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> customValidationnErrorhandling(MethodArgumentNotValidException argumentNotValidException) {

		UserResponse userResponse = new UserResponse("validation error", null,
				argumentNotValidException.getBindingResult().getFieldError().getDefaultMessage(), new Date());
		return new ResponseEntity<>(userResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidName.class)
	public ResponseEntity<?> invalidnameHandling(InvalidName invalidName, WebRequest webRequest) {
		UserResponse userResponse = new UserResponse(invalidName.getMessage(), HttpStatus.BAD_REQUEST,
				webRequest.getDescription(false), new Date());
		return new ResponseEntity<>(userResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidId.class)
	public ResponseEntity<?> invalididHandling(InvalidId invalidId, WebRequest request) {
		UserResponse userResponse = new UserResponse(invalidId.getMessage(), HttpStatus.BAD_REQUEST,
				request.getDescription(false), new Date());
		return new ResponseEntity<>(userResponse, HttpStatus.BAD_REQUEST);
	}

}
