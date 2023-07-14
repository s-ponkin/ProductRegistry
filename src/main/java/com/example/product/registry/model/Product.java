package com.example.product.registry.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

    /**
     * ID продукта в базе данных
     */
    private int id;
    /**
     * Имя продукта
     */
    private String name;
    /**
     * Количество продукта
     */
    private int quantity;
    /**
     * Стоимость продукта
     */
    private double cost;

    public Product(int id, String name, int quantity, double cost) {

        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", cost=" + cost +
                '}';
    }
}
