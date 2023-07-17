package com.example.product.registry.model;

import lombok.Builder;
import lombok.Data;

@Data
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
	private int quantity;
	/**
	 * Стоимость продукта
	 */
	double cost;
}
