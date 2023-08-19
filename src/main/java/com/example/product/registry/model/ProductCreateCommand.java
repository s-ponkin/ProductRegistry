package com.example.product.registry.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProductCreateCommand {

	/**
	 * Имя продукта
	 */
	String name;

	/**
	 * Количество продукта
	 */
	int quantity;

	/**
	 * Стоимость продукта
	 */
	double cost;
}
