package com.example.product.registry.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "PRODUCT")
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
	@Column(name = "NAME")
	String name;

	/**
	 * Количество продукта
	 */
	@Column(name = "QUANTITY")
	int quantity;

	/**
	 * Стоимость продукта
	 */
	@Column(name = "COST")
	double cost;

	public Product() {
	}
}
