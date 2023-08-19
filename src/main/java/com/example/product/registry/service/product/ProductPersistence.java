package com.example.product.registry.service.product;

import com.example.product.registry.entity.ProductEntity;
import com.example.product.registry.model.ProductCreateCommand;
import com.example.product.registry.model.ProductUpdateCommand;

import java.util.List;

public interface ProductPersistence {

	/**
	 * Создает новый продукт
	 *
	 * @param productCreateCommand продукт для создания
	 *
	 * @return объект класса Product
	 */
	ProductEntity create(ProductCreateCommand productCreateCommand);

	/**
	 * Возвращает список всех имеющихся продуктов
	 *
	 * @return список продуктов
	 */
	List<ProductEntity> getAll();

	/**
	 * Возвращает продукт по его ID
	 *
	 * @param id ID продукта
	 *
	 * @return объект продукта с заданным ID
	 */
	ProductEntity getById(int id);

	/**
	 * Обновляет продукт с заданным ID,
	 * в соответствии с переданным продуктом
	 *
	 * @param productUpdateCommand информация в соответсвии с которой нужно обновить данные
	 * @return - true если данные были обновлены, иначе false
	 */
	ProductEntity update(ProductUpdateCommand productUpdateCommand);

	/**
	 * Удаляет продукт с заданным ID
	 *
	 * @param id id продукта, который нужно удалить
	 */
	void delete(int id);
}

