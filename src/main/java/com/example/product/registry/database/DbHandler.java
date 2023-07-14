package com.example.product.registry.database;

import com.example.product.registry.model.Product;
import com.example.product.registry.model.ProductInfo;
import org.sqlite.JDBC;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DbHandler {

    private static final String path = "./data/products.db";
    private static final String CONNECTION_ADDRESS = "jdbc:sqlite:" + path;

    private static DbHandler instance = null;

    public static synchronized DbHandler getInstance() throws SQLException {
        if (instance == null) {
            instance = new DbHandler();
        }
        return instance;
    }

    private final Connection connection;

    private DbHandler() throws SQLException {
        DriverManager.registerDriver(new JDBC());
        this.connection = DriverManager.getConnection(CONNECTION_ADDRESS);
        createDatabase();
    }

    private void createDatabase() {
        if(new File(path).exists()){
            return;
        }

        String query = "CREATE TABLE products ('id' INTEGER PRIMARY KEY AUTOINCREMENT, 'name' VARCHAR(50) NOT NULL, " +
                "'quantity' INTEGER, 'cost' FLOAT) ";
        try {
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Product> getAllProducts() {

        try (Statement statement = this.connection.createStatement()) {
            List<Product> products = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery("SELECT id, name, quantity, cost FROM products");
            while (resultSet.next()) {
                products.add(new Product(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("quantity"),
                        resultSet.getDouble("cost")));
            }
            return products;

        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public Product getProductById(int id) {
        Product product = null;
        try (PreparedStatement statement = this.connection.prepareStatement(
                "SELECT * FROM products WHERE id = ?")) {
            statement.setObject(1, id);
            ResultSet resultSet = statement.executeQuery();
            product = new Product(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("quantity"),
                    resultSet.getDouble("cost"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public Product addProduct(ProductInfo productInfo) {
        Product product = null;
        try (PreparedStatement statement = this.connection.prepareStatement(
                "INSERT INTO products(name, quantity, cost) " +
                        "VALUES(?, ?, ?)")) {
            statement.setObject(1, productInfo.getName());
            statement.setObject(2, productInfo.getQuantity());
            statement.setObject(3, productInfo.getCost());
            statement.execute();
            String query = "SELECT * FROM products ORDER BY id DESC LIMIT 1";
            Statement statement1 = connection.createStatement();
            ResultSet resultSet = statement1.executeQuery(query);
            product = new Product(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("quantity"),
                    resultSet.getDouble("cost"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public Product updateProduct(ProductInfo productInfo, int id) {
        Product product = null;
        try (PreparedStatement statement = this.connection.prepareStatement(
                "UPDATE products SET name = ?, quantity = ?, cost = ? WHERE id = ?")) {
            statement.setObject(1, productInfo.getName());
            statement.setObject(2, productInfo.getQuantity());
            statement.setObject(3, productInfo.getCost());
            statement.setObject(4, id);
            boolean exit = statement.execute();
            if (!exit) {
                return null;
            }
            ResultSet resultSet = statement.executeQuery();
            product = new Product(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getInt("quantity"),
                    resultSet.getDouble("cost"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public void deleteProduct(int id) {
        try (PreparedStatement statement = this.connection.prepareStatement(
                "DELETE FROM products WHERE id = ?")) {
            statement.setObject(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
