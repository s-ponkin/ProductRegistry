package com.example.product.registry.exception.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ProductNotFoundException.class)
	protected ErrorBody handleProductNotFoundException(ProductNotFoundException exception) {
		return new ErrorBody(exception.getMessage());
	}
}
