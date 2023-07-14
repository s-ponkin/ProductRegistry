package com.example.product.registry.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductInfo {

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

    public ProductInfo(String name, int quantity, double cost) {
        this.name = name;
        this.quantity = quantity;
        this.cost = cost;
    }
}
