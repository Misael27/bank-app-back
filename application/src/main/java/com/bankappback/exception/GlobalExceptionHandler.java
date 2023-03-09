package com.bankappback.exception;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.http.HttpStatus;

/**
 * GlobalExceptionHandler
 * 
 * @author mjpol
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * resourceNotFoundHandling
	 * 
	 * @param exception
	 * @param request
	 * @return ResponseEntity<?>
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundHandling(ResourceNotFoundException exception, WebRequest request) {
		final ErrorDetails errorDetails = new ErrorDetails(
				new Date(), 
				exception.getMessage(),
				404,
				HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	/**
	 * globalExceptionHandling
	 * 
	 * @param exception
	 * @param request
	 * @return ResponseEntity<?>
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalExceptionHandling(Exception exception, WebRequest request) {
		logger.error(exception.getMessage(), exception);
		final ErrorDetails errorDetails = new ErrorDetails(
				new Date(), 
				exception.getMessage(), 
				500,
				 HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
