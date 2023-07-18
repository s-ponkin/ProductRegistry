package com.example.product.registry.database;

import com.example.product.registry.exception.handler.ProductNotFoundException;
import com.example.product.registry.model.Product;
import com.example.product.registry.model.ProductInfo;
import org.springframework.stereotype.Service;
import org.sqlite.JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.example.product.registry.consts.QueryConsts.*;

@Service
public class DbHandler {

	private static final Logger logger = Logger.getLogger(DbHandler.class.getName());
	private static final String path = "src/main/java/com/example/product/registry/data/products.db";
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
	}

	public List<Product> getAllProducts() {

		try (Statement statement = this.connection.createStatement()) {
			List<Product> products = new ArrayList<>();
			ResultSet resultSet = statement.executeQuery(SELECT_ALL);
			while (resultSet.next()) {
				products.add(Product.builder()
					.id(resultSet.getInt("id"))
					.name(resultSet.getString("name"))
					.quantity(resultSet.getInt("quantity"))
					.cost(resultSet.getDouble("cost"))
					.build());
			}
			return products;
		} catch (SQLException e) {
			logger.log(Level.WARNING, e.getMessage());
			return Collections.emptyList();
		}
	}

	public Product getProductById(int id) {
		Product product = null;
		try (PreparedStatement statement = this.connection.prepareStatement(
				SELECT_BY_ID)) {
			statement.setObject(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.getInt("id") == 0) {
				throw ProductNotFoundException.byId(id);
			}
			product = Product.builder()
					.id(resultSet.getInt("id"))
					.name(resultSet.getString("name"))
					.quantity(resultSet.getInt("quantity"))
					.cost(resultSet.getDouble("cost"))
				.build();
		} catch (SQLException e) {
			logger.log(Level.WARNING, e.getMessage());
		}
		return product;
	}

	public Product addProduct(ProductInfo productInfo) {
		Product product = null;
		try (PreparedStatement statement = this.connection.prepareStatement(INSERT)) {
			statement.setObject(1, productInfo.getName());
			statement.setObject(2, productInfo.getQuantity());
			statement.setObject(3, productInfo.getCost());
			statement.execute();
			Statement statement1 = connection.createStatement();
			ResultSet resultSet = statement1.executeQuery(SELECT_LAST_ELEMENT);
			product = Product.builder()
					.id(resultSet.getInt("id"))
					.name(resultSet.getString("name"))
					.quantity(resultSet.getInt("quantity"))
				.cost(resultSet.getDouble("cost"))
				.build();
		} catch (SQLException e) {
			logger.log(Level.WARNING, e.getMessage());
		}
		return product;
	}

	public Product updateProduct(ProductInfo productInfo, int id) {
		getProductById(id);
		Product product = null;
		try (PreparedStatement statement = this.connection.prepareStatement(UPDATE_BY_ID)) {
			statement.setObject(1, productInfo.getName());
			statement.setObject(2, productInfo.getQuantity());
			statement.setObject(3, productInfo.getCost());
			statement.setObject(4, id);
			statement.executeUpdate();
			product = Product.builder()
					.id(id)
					.name(productInfo.getName())
					.quantity(productInfo.getQuantity())
					.cost(productInfo.getCost())
				.build();
		} catch (SQLException e) {
			logger.log(Level.WARNING, e.getMessage());
		}
		return product;
	}

	public void deleteProduct(int id) {
		try (PreparedStatement statement = this.connection.prepareStatement(DELETE_BY_ID)) {
			statement.setObject(1, id);
			statement.execute();
		} catch (SQLException e) {
			logger.log(Level.WARNING, e.getMessage());
		}
	}
}
