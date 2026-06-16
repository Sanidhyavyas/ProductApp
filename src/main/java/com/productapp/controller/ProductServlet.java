package com.productapp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.productapp.model.Product;
import com.productapp.service.ProductService;

@WebServlet("/product")
public class ProductServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	ProductService service = new ProductService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");

		if ("delete".equals(action)) {

			int id = Integer.parseInt(request.getParameter("id"));

			service.deleteProduct(id);

			response.sendRedirect("product");

			return;
		}

		if ("edit".equals(action)) {

			int id = Integer.parseInt(request.getParameter("id"));

			Product product = service.getProductById(id);

			request.setAttribute("product"	, product);
		}

		request.setAttribute("products", service.getAllProducts());

		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("productId");

		String name = request.getParameter("productName");

		double price = Double.parseDouble(request.getParameter("price"));

		if (id != null && !id.isEmpty()) {

			Product p = new Product();

			p.setProductId(Integer.parseInt(id));
			
			p.setProductName(name);

			p.setPrice(price);

			service.updateProduct(p);

		} else {

			Product p = new Product();

			p.setProductName(name);

			p.setPrice(price);

			service.addProduct(p);
		}

		response.sendRedirect("product");
	}
}