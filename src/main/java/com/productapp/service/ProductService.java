package com.productapp.service;

import java.util.List;

import com.productapp.dao.ProductDAO;
import com.productapp.model.Product;

public class ProductService {

	ProductDAO dao = new ProductDAO();

	public boolean addProduct(Product product) {

		return dao.addProduct(product);
	}

	public List<Product> getAllProducts() {

		return dao.getAllProducts();
	}

	public Product getProductById(int id) {

		return dao.getProductById(id);
	}

	public boolean updateProduct(Product product) {

		return dao.updateProduct(product);
	}

	public boolean deleteProduct(int id) {

		return dao.deleteProduct(id);
	}
}