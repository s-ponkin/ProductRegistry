package com.example.product.registry.converter;

import com.example.product.registry.entity.ProductEntity;
import com.example.product.registry.model.ProductInfoOutput;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

@Service
public class ProductEntityToProductInfoOutputConverter implements Converter<ProductEntity, ProductInfoOutput> {

	@Override
	public ProductInfoOutput convert(ProductEntity source) {
		return ProductInfoOutput.builder()
			.name(source.getName())
			.quantity(source.getQuantity())
			.cost(source.getCost())
			.build();
	}
}
