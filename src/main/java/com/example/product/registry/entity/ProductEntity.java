package com.example.product.registry.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "PRODUCT")
public class ProductEntity {

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
}
