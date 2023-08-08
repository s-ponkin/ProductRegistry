package com.example.product.registry.service.product.impl;

import com.example.product.registry.exception.handler.ProductNotFoundException;
import com.example.product.registry.model.Product;
import com.example.product.registry.model.ProductInfo;
import com.example.product.registry.repository.ProductRepository;
import com.example.product.registry.service.product.ProductPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductPersistenceImpl implements ProductPersistence {

	private final ProductRepository productRepository;

	@Override
	public Product create(ProductInfo productInfo) {
		Product product = Product.builder()
			.name(productInfo.getName())
			.quantity(productInfo.getQuantity())
			.cost(productInfo.getCost())
			.build();
		return productRepository.save(product);
	}

	@Override
	public List<Product> getAll() {
		return productRepository.findAll();
	}

	@Override
	public Product getById(int id) {
		return productRepository.findById(id)
			.orElseThrow(() -> ProductNotFoundException.byId(id));
	}

	@Override
	public Product update(ProductInfo productInfo, int id) {
		Product product = productRepository.findById(id)
			.orElseThrow(() -> ProductNotFoundException.byId(id));
		product.setName(product.getName());
		product.setCost(productInfo.getCost());
		product.setQuantity(productInfo.getQuantity());
		return productRepository.save(product);
	}

	@Override
	public void delete(int id) {
		if (productRepository.existsById(id)) {
			productRepository.deleteById(id);
		}
	}
}