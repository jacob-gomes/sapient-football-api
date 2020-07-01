package com.sapient.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.sapient.exception.BadRequestException;

@ControllerAdvice
public class APIExceptionHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(APIExceptionHandler.class);
	
	
	@ExceptionHandler(value=BadRequestException.class)
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handleBadRequestException(BadRequestException e){
		logger.error(e.getMessage());
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
