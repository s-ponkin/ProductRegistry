package com.example.product.registry.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Product {

	/**
	 * ID продукта в базе данных
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
