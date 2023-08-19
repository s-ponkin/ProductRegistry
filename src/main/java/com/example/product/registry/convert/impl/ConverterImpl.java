package com.example.product.registry.convert.impl;

import com.example.product.registry.convert.Converter;
import com.example.product.registry.entity.ProductEntity;
import com.example.product.registry.model.ProductInfoOutput;

public class ConverterImpl implements Converter<ProductEntity, ProductInfoOutput> {

	@Override
	public ProductInfoOutput convert(ProductEntity source) {
		return ProductInfoOutput.builder()
			.name(source.getName())
			.quantity(source.getQuantity())
			.cost(source.getCost())
			.build();
	}
}
