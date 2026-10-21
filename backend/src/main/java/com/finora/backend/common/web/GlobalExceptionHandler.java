package com.finora.backend.common.web;

import com.finora.backend.common.exception.ConflictException;
import com.finora.backend.common.exception.ForbiddenException;
import com.finora.backend.common.exception.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(NotFoundException.class)
	public ProblemDetail handleNotFound(NotFoundException ex, HttpServletRequest request) {
		return problemDetail(HttpStatus.NOT_FOUND, ex.getMessage(), request);
	}

	@ExceptionHandler(ConflictException.class)
	public ProblemDetail handleConflict(ConflictException ex, HttpServletRequest request) {
		return problemDetail(HttpStatus.CONFLICT, ex.getMessage(), request);
	}

	@ExceptionHandler(ForbiddenException.class)
	public ProblemDetail handleForbidden(ForbiddenException ex, HttpServletRequest request) {
		return problemDetail(HttpStatus.FORBIDDEN, ex.getMessage(), request);
	}

	@ExceptionHandler(Exception.class)
	public ProblemDetail handleUnexpected(Exception ex, HttpServletRequest request) {
		// The real cause is logged but never sent to the caller.
		log.error("Unhandled exception on {}", request.getRequestURI(), ex);
		return problemDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong. Please try again.", request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex,
			HttpHeaders headers,
			HttpStatusCode status,
			WebRequest request) {

		List<ApiFieldError> errors = ex.getBindingResult().getFieldErrors().stream()
				.map(fieldError -> new ApiFieldError(fieldError.getField(), fieldError.getDefaultMessage()))
				.toList();

		ProblemDetail problem = problemDetail(HttpStatus.BAD_REQUEST, "Validation failed", servletRequest(request));
		problem.setProperty("errors", errors);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problem);
	}

	private ProblemDetail problemDetail(HttpStatus status, String detail, HttpServletRequest request) {
		ProblemDetail problem = ProblemDetail.forStatusAndDetail(status, detail);
		problem.setInstance(URI.create(request.getRequestURI()));
		return problem;
	}

	private HttpServletRequest servletRequest(WebRequest request) {
		return ((ServletWebRequest) request).getRequest();
	}
}
