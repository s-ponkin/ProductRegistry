package com.example.product.registry.service;

import com.example.product.registry.model.Product;

import java.util.List;

public interface ProductService {

    /**
     * Создает новый продукт
     *
     * @param product - продукт для создания
     */
    void create(Product product);

    /**
     * Возвращает список всех имеющихся продуктов
     *
     * @return список продуктов
     */
    List<Product> readAll();

    /**
     * Возвращает продукт по его ID
     *
     * @param id - ID продукта
     * @return - объект продукта с заданным ID
     */
    Product read(int id);

    /**
     * Обновляет продукт с заданным ID,
     * в соответствии с переданным продуктом
     *
     * @param product - продукт в соответсвии с которым нужно обновить данные
     * @param id      - id продукта который нужно обновить
     * @return - true если данные были обновлены, иначе false
     */
    boolean update(Product product, int id);

    /**
     * Удаляет продукт с заданным ID
     *
     * @param id - id продукта, который нужно удалить
     * @return - true если клиент был удален, иначе false
     */
    boolean delete(int id);
}

