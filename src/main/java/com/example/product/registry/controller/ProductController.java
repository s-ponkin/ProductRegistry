package com.example.product.registry.controller;

import com.example.product.registry.model.Product;
import com.example.product.registry.model.ProductInfo;
import com.example.product.registry.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(value = "/product")
    public ResponseEntity<?> create(@RequestBody ProductInfo productInfo) {
        Product product = productService.create(productInfo);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/product/all")
    public ResponseEntity<List<Product>> read() {
        final List<Product> products = productService.getAll();

        return products != null && products.isEmpty()
                ? new ResponseEntity<>(products, HttpStatus.OK)
                : new ResponseEntity<>(products, HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/product")
    public ResponseEntity<Product> read(@RequestParam("id") int id) {
        final Product product = productService.getById(id);

        return product != null
                ? new ResponseEntity<>(product, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/product")
    public ResponseEntity<?> update(@RequestParam("id") int id, @RequestBody ProductInfo productInfo) {
        final Product product = productService.update(productInfo, id);

        return product != null
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/product")
    public ResponseEntity<?> delete(@RequestParam("id") int id) {
        productService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
