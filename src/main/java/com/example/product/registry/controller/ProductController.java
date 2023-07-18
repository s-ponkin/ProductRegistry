package com.example.product.registry.controller;

import com.example.product.registry.model.Product;
import com.example.product.registry.model.ProductInfo;
import com.example.product.registry.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ProductController {

	private final ProductService productService;

	@PostMapping(value = "/product")
	public ResponseEntity<?> create(@RequestBody ProductInfo productInfo) {
		Product product = productService.create(productInfo);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@GetMapping(value = "/product/all")
	public ResponseEntity<List<Product>> read() {
		List<Product> products = productService.getAll();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@GetMapping(value = "/product")
	public ResponseEntity<Product> read(@RequestParam("id") int id) {
		Product product = productService.getById(id);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@PutMapping(value = "/product")
	public ResponseEntity<?> update(@RequestParam("id") int id, @RequestBody ProductInfo productInfo) {
		Product product = productService.update(productInfo, id);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@DeleteMapping(value = "/product")
	public ResponseEntity<?> delete(@RequestParam("id") int id) {
		productService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
