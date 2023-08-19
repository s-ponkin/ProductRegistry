package com.example.product.registry.service.product.impl;

import com.example.product.registry.entity.ProductEntity;
import com.example.product.registry.exception.handler.ProductNotFoundException;
import com.example.product.registry.model.ProductCreateCommand;
import com.example.product.registry.model.ProductUpdateCommand;
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
	public ProductEntity create(ProductCreateCommand productCreateCommand) {
		ProductEntity productEntity = com.example.product.registry.entity.ProductEntity.builder()
			.name(productCreateCommand.getName())
			.quantity(productCreateCommand.getQuantity())
			.cost(productCreateCommand.getCost())
			.build();
		return productRepository.save(productEntity);
	}

	@Override
	public List<ProductEntity> getAll() {
		return productRepository.findAll();
	}

	@Override
	public ProductEntity getById(int id) {
		return productRepository.findById(id)
			.orElseThrow(() -> ProductNotFoundException.byId(id));
	}

	@Override
	public ProductEntity update(ProductUpdateCommand productUpdateCommand) {
		ProductEntity productEntity = productRepository.findById(productUpdateCommand.getId())
			.orElseThrow(() -> ProductNotFoundException.byId(productUpdateCommand.getId()));
		productEntity.setName(productUpdateCommand.getName());
		productEntity.setCost(productUpdateCommand.getCost());
		productEntity.setQuantity(productUpdateCommand.getQuantity());
		return productRepository.save(productEntity);
	}

	@Override
	public void delete(int id) {
		if (productRepository.existsById(id)) {
			productRepository.deleteById(id);
		}
	}
}