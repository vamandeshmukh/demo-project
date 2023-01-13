package com.lti.springboot.demo.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lti.springboot.demo.model.Employee;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<Employee> handleEmployeeNotFoundException(RuntimeException e) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("message", e.getMessage());
		HttpStatus status = HttpStatus.NOT_FOUND;
		ResponseEntity<Employee> response = new ResponseEntity<>(null, headers, status);
		return response;
	}

}
