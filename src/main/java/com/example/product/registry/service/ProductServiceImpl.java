package com.example.product.registry.service;

import com.example.product.registry.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ProductServiceImpl implements ProductService {

    private static final Map<Integer, Product> PRODUCT_REPOSITORY_MAP = new HashMap<>();
    private static final AtomicInteger PRODUCT_ID_HOLDER = new AtomicInteger();

    @Override
    public void create(Product product) {
        final int productId = PRODUCT_ID_HOLDER.incrementAndGet();
        product.setId(productId);
        PRODUCT_REPOSITORY_MAP.put(productId, product);
    }

    @Override
    public List<Product> readAll() {
        return new ArrayList<>(PRODUCT_REPOSITORY_MAP.values());
    }

    @Override
    public Product read(int id) {
        return PRODUCT_REPOSITORY_MAP.get(id);
    }

    @Override
    public boolean update(Product product, int id) {
        if (PRODUCT_REPOSITORY_MAP.containsKey(id)) {
            product.setId(id);
            PRODUCT_REPOSITORY_MAP.put(id, product);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        return PRODUCT_REPOSITORY_MAP.remove(id) != null;
    }
}
