package com.example.product.registry.database;

import com.example.product.registry.exception.handler.ProductNotFoundException;
import com.example.product.registry.model.Product;
import com.example.product.registry.model.ProductInfo;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HibernateUtil {

	private static final Logger logger = Logger.getLogger(HibernateUtil.class.getName());
	private static HibernateUtil instance = null;
	private static SessionFactory SESSION_FACTORY;

	public static synchronized HibernateUtil getInstance() {
		if (instance == null) {
			instance = new HibernateUtil();
		}
		return instance;
	}

	private HibernateUtil() {
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
			.configure("hibernate.cfg.xml").build();
		Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
		SESSION_FACTORY = metadata.getSessionFactoryBuilder().build();
	}

	public List<Product> getAllProducts() {

		try (Session session = SESSION_FACTORY.openSession()) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Product> query = builder.createQuery(Product.class);
			Root<Product> rootEntry = query.from(Product.class);
			CriteriaQuery<Product> all = query.select(rootEntry);
			TypedQuery<Product> allQuery = session.createQuery(all);
			return allQuery.getResultList();
		} catch (Exception e) {
			logger.log(Level.WARNING, e.getMessage());
			return Collections.emptyList();
		}
	}

	public Product getProductById(int id) {
		Product product = null;
		try (Session session = SESSION_FACTORY.openSession()) {
			product = session.get(Product.class, id);
			if (product == null) {
				throw ProductNotFoundException.byId(id);
			}
		} catch (Exception e) {
			logger.log(Level.WARNING, e.getMessage());
		}
		return product;
	}

	public Product addProduct(ProductInfo productInfo) {
		Transaction transaction = null;
		Product product = null;
		try (Session session = SESSION_FACTORY.openSession()) {
			product = Product.builder()
				.name(productInfo.getName())
				.quantity(productInfo.getQuantity())
				.cost(productInfo.getCost())
				.build();

			transaction = session.beginTransaction();
			session.persist(product);
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.log(Level.WARNING, ex.getMessage());
		}
		return product;
	}

	public Product updateProduct(ProductInfo productInfo, int id) {
		getProductById(id);
		Transaction transaction = null;
		Product product = null;
		try (Session session = SESSION_FACTORY.openSession()) {
			transaction = session.beginTransaction();
			product = session.get(Product.class, id);
			product.setName(productInfo.getName());
			product.setQuantity(productInfo.getQuantity());
			product.setCost(productInfo.getCost());
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.log(Level.WARNING, ex.getMessage());
		}
		return product;
	}

	public void deleteProduct(int id) {
		Transaction transaction = null;
		try (Session session = SESSION_FACTORY.openSession()) {
			transaction = session.beginTransaction();
			Product product = session.get(Product.class, id);
			session.delete(product);
			transaction.commit();
		} catch (Exception ex) {
			if (transaction != null) {
				transaction.rollback();
			}
			logger.log(Level.WARNING, ex.getMessage());
		}
	}
}