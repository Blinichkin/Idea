package ru.urfu.idea.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler(value = {AppException.class})
	public ResponseEntity<ErrorDto> handleException(AppException e) {
		return ResponseEntity
				.status(e.getStatus())
				.body(ErrorDto.builder().message(e.getMessage()).build());
	}
	
}
