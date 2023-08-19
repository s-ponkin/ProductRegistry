package com.example.product.registry.controller;

import com.example.product.registry.convert.impl.ConverterImpl;
import com.example.product.registry.entity.ProductEntity;
import com.example.product.registry.model.ProductCreateCommand;
import com.example.product.registry.model.ProductInfoOutput;
import com.example.product.registry.model.ProductUpdateCommand;
import com.example.product.registry.service.product.ProductPersistence;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductController {

	private final ProductPersistence productPersistence;
	private final ConverterImpl converter = new ConverterImpl();

	@PostMapping(value = "/product")
	public ResponseEntity<?> create(@RequestBody ProductCreateCommand productCreateCommand) {
		ProductEntity productEntity = productPersistence.create(productCreateCommand);
		return new ResponseEntity<>(converter.convert(productEntity), HttpStatus.OK);
	}

	@GetMapping(value = "/product/all")
	public ResponseEntity<List<ProductInfoOutput>> read() {
		List<ProductEntity> productEntities = productPersistence.getAll();
		List<ProductInfoOutput> productList = new ArrayList<>();
		for (ProductEntity product : productEntities) {
			productList.add(converter.convert(product));
		}
		return new ResponseEntity<>(productList, HttpStatus.OK);
	}

	@GetMapping(value = "/product")
	public ResponseEntity<ProductInfoOutput> read(@RequestParam("id") int id) {
		ProductEntity productEntity = productPersistence.getById(id);
		return new ResponseEntity<>(converter.convert(productEntity), HttpStatus.OK);
	}

	@PutMapping(value = "/product")
	public ResponseEntity<?> update(@RequestParam("id") int id, @RequestBody ProductUpdateCommand productUpdateCommand) {
		productUpdateCommand.setId(id);
		ProductEntity productEntity = productPersistence.update(productUpdateCommand);
		return new ResponseEntity<>(converter.convert(productEntity), HttpStatus.OK);
	}

	@DeleteMapping(value = "/product")
	public ResponseEntity<?> delete(@RequestParam("id") int id) {
		productPersistence.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
