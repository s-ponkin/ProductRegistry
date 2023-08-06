package com.example.product.registry.service.product.impl;

import com.example.product.registry.database.HibernateUtil;
import com.example.product.registry.model.Product;
import com.example.product.registry.model.ProductInfo;
import com.example.product.registry.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger logger = Logger.getLogger(ProductServiceImpl.class.getName());
	private final HibernateUtil HIBERNATE;


	@Override
	public Product create(ProductInfo productInfo) {
		return HIBERNATE.addProduct(productInfo);
	}

	@Override
	public List<Product> getAll() {
		return HIBERNATE.getAllProducts();
	}

	@Override
	public Product getById(int id) {
		return HIBERNATE.getProductById(id);
	}

	@Override
	public Product update(ProductInfo productInfo, int id) {

		return HIBERNATE.updateProduct(productInfo, id);
	}

	@Override
	public void delete(int id) {
		HIBERNATE.deleteProduct(id);
	}
}
