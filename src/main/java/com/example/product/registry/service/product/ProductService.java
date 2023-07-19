package com.example.product.registry.service.product;

import com.example.product.registry.model.Product;
import com.example.product.registry.model.ProductInfo;

import java.util.List;

public interface ProductService {

	/**
	 * Создает новый продукт
	 *
	 * @param productInfo продукт для создания
	 *
	 * @return объект класса Product
	 */
	Product create(ProductInfo productInfo);

	/**
	 * Возвращает список всех имеющихся продуктов
	 *
	 * @return список продуктов
	 */
	List<Product> getAll();

	/**
	 * Возвращает продукт по его ID
	 *
	 * @param id ID продукта
	 *
	 * @return объект продукта с заданным ID
	 */
	Product getById(int id);

	/**
	 * Обновляет продукт с заданным ID,
	 * в соответствии с переданным продуктом
	 *
	 * @param productInfo информация в соответсвии с которой нужно обновить данные
	 * @param id          id продукта который нужно обновить
	 * @return - true если данные были обновлены, иначе false
	 */
	Product update(ProductInfo productInfo, int id);

	/**
	 * Удаляет продукт с заданным ID
	 *
	 * @param id id продукта, который нужно удалить
	 */
	void delete(int id);
}

