package com.example.Exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	/*											 user exception 														*/
	
		@ExceptionHandler(UserException.class)
		public ResponseEntity<MyErrorDetails> userExceptionHandler(UserException ce, WebRequest req) {
			
			MyErrorDetails err = new MyErrorDetails();
			err.setTimestamp(LocalDateTime.now());
			err.setMessage(ce.getMessage());
			err.setDetails(req.getDescription(false));
			
			return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
		}
		
		
		@ExceptionHandler(ProductException.class)
		public ResponseEntity<MyErrorDetails> productExceptionHandler(ProductException pe, WebRequest req) {
			
			MyErrorDetails err = new MyErrorDetails();
			err.setTimestamp(LocalDateTime.now());
			err.setMessage(pe.getMessage());
			err.setDetails(req.getDescription(false));
			
			return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
		}
		
		@ExceptionHandler(CategoryException.class)
		public ResponseEntity<MyErrorDetails> categoryExceptionHandler(CategoryException ce, WebRequest req) {
			
			MyErrorDetails err = new MyErrorDetails();
			err.setTimestamp(LocalDateTime.now());
			err.setMessage(ce.getMessage());
			err.setDetails(req.getDescription(false));
			
			return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
		}
		
		@ExceptionHandler(OrderException.class)
		public ResponseEntity<MyErrorDetails> orderExceptionHandler(OrderException ce, WebRequest req) {
			
			MyErrorDetails err = new MyErrorDetails();
			err.setTimestamp(LocalDateTime.now());
			err.setMessage(ce.getMessage());
			err.setDetails(req.getDescription(false));
			
			return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
		}
		
		@ExceptionHandler(CartException.class)
		public ResponseEntity<MyErrorDetails> cartExceptionHandler(CartException ce, WebRequest req) {
			
			MyErrorDetails err = new MyErrorDetails();
			err.setTimestamp(LocalDateTime.now());
			err.setMessage(ce.getMessage());
			err.setDetails(req.getDescription(false));
			
			return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
		}
			
		
		@ExceptionHandler(Exception.class)
		public ResponseEntity<MyErrorDetails> anyExceptionHandler(Exception ie, WebRequest req) {
			
			MyErrorDetails err = new MyErrorDetails();
			err.setTimestamp(LocalDateTime.now());
			err.setMessage(ie.getMessage());
			err.setDetails(req.getDescription(false));
			
			return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
		}
		
		@ExceptionHandler(NoHandlerFoundException.class)
		public ResponseEntity<MyErrorDetails> noHandlerFoundExceptionHandler(NoHandlerFoundException ie, WebRequest req) {
			
			MyErrorDetails err = new MyErrorDetails();
			err.setTimestamp(LocalDateTime.now());
			err.setMessage(ie.getMessage());
			err.setDetails(req.getDescription(false));
			
			return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
		}
		
		@ExceptionHandler(MethodArgumentNotValidException.class)
		public ResponseEntity<MyErrorDetails> myInvalidDataExpHandler(MethodArgumentNotValidException me) {
			
			MyErrorDetails err = new MyErrorDetails();
			
			err.setTimestamp(LocalDateTime.now());
			err.setMessage("Validation Error");
			err.setDetails(me.getBindingResult().getFieldError().getDefaultMessage());
			
			
			return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_REQUEST);
			
			
		}
	
	
}