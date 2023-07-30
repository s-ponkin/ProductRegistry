package com.example.product.registry.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "Product")
public class Product {

	/**
	 * ID продукта в базе данных
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	public Product() {
	}
}
