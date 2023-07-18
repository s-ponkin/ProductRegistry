package com.example.product.registry.consts;

public interface QueryConsts {

    String SELECT_ALL = "SELECT * FROM products";

    String SELECT_BY_ID = "SELECT * FROM products WHERE id = ?";

    String DELETE_BY_ID = "DELETE FROM products WHERE id = ?";

    String UPDATE_BY_ID = "UPDATE products SET name = ?, quantity = ?, cost = ? WHERE id = ?";

    String INSERT = "INSERT INTO products(name, quantity, cost) VALUES(?, ?, ?)";

    String SELECT_LAST_ELEMENT = "SELECT * FROM products ORDER BY id DESC LIMIT 1";
}
