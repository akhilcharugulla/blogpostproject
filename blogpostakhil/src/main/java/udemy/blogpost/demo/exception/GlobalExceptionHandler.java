package udemy.blogpost.demo.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import udemy.blogpost.demo.dto.ErrorDetails;
import udemy.blogpost.demo.exception.BlogAPIException;
import udemy.blogpost.demo.exception.ResourceNotFoundException;

@ControllerAdvice
@ResponseStatus
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception,
			WebRequest webreq) {
		ErrorDetails errordetails = new ErrorDetails(new Date(), webreq.getDescription(false), exception.getMessage());
		return new ResponseEntity<>(errordetails, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BlogAPIException.class)
	public ResponseEntity<ErrorDetails> handleBlogAPIException(BlogAPIException exception, WebRequest webreq) {
		ErrorDetails errordetails = new ErrorDetails(new Date(), webreq.getDescription(false), exception.getMessage());
		return new ResponseEntity<>(errordetails, HttpStatus.BAD_REQUEST);
	}

// global exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception, WebRequest webRequest) {
		ErrorDetails errordetails = new ErrorDetails(new Date(), webRequest.getDescription(false),
				exception.getMessage());
		return new ResponseEntity<>(errordetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
