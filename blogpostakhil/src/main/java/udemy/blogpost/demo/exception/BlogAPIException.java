package udemy.blogpost.demo.exception;

import org.springframework.http.HttpStatus;
import lombok.Data;

@Data
public class BlogAPIException extends RuntimeException {

	private HttpStatus httpstatus;
	private String message;

	public BlogAPIException(HttpStatus httpstatus, String message) {
		super();
		this.httpstatus = httpstatus;
		this.message = message;
	}
}
