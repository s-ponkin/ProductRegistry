package com.example.product.registry.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductUpdateCommand {

	/**
	 * ID продукта
	 */
	int id;

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
