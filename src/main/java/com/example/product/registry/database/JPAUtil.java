package com.example.product.registry.database;

import com.example.product.registry.model.Product;
import com.example.product.registry.repository.ProductRepository;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JPAUtil {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		ProductRepository productRepository = context.getBean(ProductRepository.class);

		Product product = Product.builder()
			.name("Orange")
			.quantity(13)
			.cost(100)
			.build();

		productRepository.saveAndFlush(product);
	}
}
