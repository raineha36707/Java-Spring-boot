package com.infytel.exceptions;


import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.infytel.dto.ErrorMessage;
import com.infytel.util.InfyTelConstants;
 
@RestControllerAdvice

public class ExceptionControllerAdvice {
	@Autowired
	private Environment environment;
	
	//Handler for exceptions other than NoSuchCustomerException and validation exceptions
 	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> exceptionHandler2(Exception ex) 
 	{
		 ErrorMessage error = new ErrorMessage();
	     error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
	     error.setMessage(environment.getProperty(InfyTelConstants.GENERAL_EXCEPTION_MESSAGE.toString()));
	     return new ResponseEntity<>(error, HttpStatus.OK);
	}
	
	//Handler for NoSuchCustomerException
	@ExceptionHandler(NoSuchCustomerException.class)
	public ResponseEntity<ErrorMessage> exceptionHandler2(NoSuchCustomerException ex) 
	{
		 ErrorMessage error = new ErrorMessage();
	     error.setErrorCode(HttpStatus.BAD_REQUEST.value());
	     error.setMessage(ex.getMessage());
	     return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	//Handler that handles the exception raised because of invalid data that is received as method argument (DTO)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorMessage> handleValidationExceptions(MethodArgumentNotValidException ex) 
	{
		 ErrorMessage error = new ErrorMessage();
	     error.setErrorCode(HttpStatus.BAD_REQUEST.value());
	     error.setMessage(ex.getBindingResult().getAllErrors()
	    		 		  	.stream().map(ObjectError::getDefaultMessage)//lambda equivalent -> x->x.getDefaultMessage()
	    		 		  	.collect(Collectors.joining(", ")));
	        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
		
	//Handler that handles the exception raised because of invalid data that is received as 
	//URI parameter (path variables, request parameters)
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorMessage> handleConstraintValidationExceptions(ConstraintViolationException ex) 
	{
		 ErrorMessage error = new ErrorMessage();
	     error.setErrorCode(HttpStatus.BAD_REQUEST.value());
	     error.setMessage(ex.getConstraintViolations()
	    		 			.stream().map(ConstraintViolation::getMessage)//lambda equivalent -> x->x.getMessage()
	    		 			.collect(Collectors.joining(", ")));
	     return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
} 