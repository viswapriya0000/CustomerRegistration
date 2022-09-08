package com.example.demo.exceptionhandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomerException extends ResponseEntityExceptionHandler{
	
	
	@ExceptionHandler(KeyViolationException.class)
	public ResponseEntity<ExceptionResponse> KeyViolationException(KeyViolationException ex){
		ExceptionResponse res = new ExceptionResponse();
		res.setErrorCode("BAD_REQUEST");
		res.setErrorMessage(ex.getMessage());
		res.setTimestamp(LocalDateTime.now());
		
		return new ResponseEntity<ExceptionResponse>(res, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ProfileNotFoundException.class)
	public ResponseEntity<ExceptionResponse> ProfileNotFoundException(ProfileNotFoundException ex){
		ExceptionResponse res = new ExceptionResponse();
		res.setErrorCode("NOT_FOUND");
		res.setErrorMessage(ex.getMessage());
		res.setTimestamp(LocalDateTime.now());
		
		return new ResponseEntity<ExceptionResponse>(res, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(UsernameInvalidException.class)
	public ResponseEntity<ExceptionResponse> UsernameInvalidException(UsernameInvalidException ex){
		ExceptionResponse res = new ExceptionResponse();
		res.setErrorCode("BAD_REQUEST");
		res.setErrorMessage(ex.getMessage());
		res.setTimestamp(LocalDateTime.now());
		
		return new ResponseEntity<ExceptionResponse>(res, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PasswordInvalidException.class)
	public ResponseEntity<ExceptionResponse> PasswordInvalidException(PasswordInvalidException ex){
		ExceptionResponse res = new ExceptionResponse();
		res.setErrorCode("BAD_REQUEST");
		res.setErrorMessage(ex.getMessage());
		res.setTimestamp(LocalDateTime.now());
		
		return new ResponseEntity<ExceptionResponse>(res, HttpStatus.BAD_REQUEST);
	}
	
	
	 @Override
     protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
     		HttpHeaders header,HttpStatus status,WebRequest request){
     	Map<String,String> errors=new HashMap<>();
     	ex.getBindingResult().getAllErrors().forEach((error)->{
     	   String fieldName=((FieldError) error).getField();
     	   String messsage=error.getDefaultMessage();
     	   errors.put(fieldName, messsage);
     	});
     	return new ResponseEntity<Object>(errors,HttpStatus.BAD_REQUEST);
     	
     }
	 
	 

}
