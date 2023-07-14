package com.example.product.registry.service.product.impl;

import com.example.product.registry.database.DbHandler;
import com.example.product.registry.model.Product;
import com.example.product.registry.model.ProductInfo;
import com.example.product.registry.service.product.ProductService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Map<Integer, Product> PRODUCT_REPOSITORY_MAP = new HashMap<>();
    private static final AtomicInteger PRODUCT_ID_HOLDER = new AtomicInteger();
    private static DbHandler DATABASE_HANDLER;

    public ProductServiceImpl() {
        try {
            Class.forName("org.sqlite.JDBC");
            DATABASE_HANDLER = DbHandler.getInstance();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Product create(ProductInfo productInfo) {
        return DATABASE_HANDLER.addProduct(productInfo);
    }

    @Override
    public List<Product> getAll() {
        return DATABASE_HANDLER.getAllProducts();
    }

    @Override
    public Product getById(int id) {
        return DATABASE_HANDLER.getProductById(id);
    }

    @Override
    public Product update(ProductInfo productInfo, int id) {

        return DATABASE_HANDLER.updateProduct(productInfo, id);
    }

    @Override
    public void delete(int id) {
        DATABASE_HANDLER.deleteProduct(id);
    }
}
