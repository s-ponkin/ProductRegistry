package com.example.product.registry.exception.handler;

public class ProductNotFoundException extends RuntimeException {

	public ProductNotFoundException(String message) {
		super(message);
	}

	public static ProductNotFoundException byId(int id) {
		return new ProductNotFoundException(String.format("Not found by id: %s", id));
	}
}
