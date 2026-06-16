<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.productapp.model.Product, java.util.List"%>
<%
    Product product = (Product) request.getAttribute("product");
    List<Product> products = (List<Product>) request.getAttribute("products");

    boolean isEdit      = (product != null);
    String  productId   = isEdit ? String.valueOf(product.getProductId())   : "";
    String  productName = isEdit ? product.getProductName()                  : "";
    String  price       = isEdit ? String.valueOf(product.getPrice())        : "";
    String  formTitle   = isEdit ? "Edit Product"   : "Add Product";
    String  submitLabel = isEdit ? "Update Product" : "Save Product";
%>
<!DOCTYPE html>
<html>
<head>
<title>Product App</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>

<body class="bg-light">
	<div class="container mt-5">

		<!-- Product Form -->
		<div class="card shadow mb-4">
			<div class="card-header bg-primary text-white">
				<h3 class="mb-0"><%=formTitle%></h3>
			</div>

			<div class="card-body">
				<form action="product" method="post">

					<input type="hidden" name="productId" value="<%=productId%>">

					<div class="mb-3">
					<label class="form-label">Product Name</label>
					<input type="text" class="form-control" name="productName"
						value="<%=productName%>" required>
					</div>

					<div class="mb-3">
					<label class="form-label">Price</label>
					<input type="number" step="0.01" class="form-control" name="price"
						value="<%=price%>" required>
					</div>

					<button type="submit" class="btn btn-success">
						<%=submitLabel%>
					</button>

					<a href="product" class="btn btn-secondary">Clear</a>

				</form>
			</div>
		</div>

		<!-- Product Table -->
		<div class="card shadow">
			<div class="card-header bg-dark text-white">
				<h3 class="mb-0">Product List</h3>
			</div>

			<div class="card-body">
				<table class="table table-bordered table-hover table-striped">
					<thead class="table-primary">
						<tr>
							<th>ID</th>
							<th>Product Name</th>
							<th>Price</th>
							<th>Actions</th>
						</tr>
					</thead>

					<tbody>
						<%
						if (products != null) {
							for (Product p : products) {
						%>
						<tr>
							<td><%=p.getProductId()%></td>
							<td><%=p.getProductName()%></td>
							<td>&#8377; <%=p.getPrice()%></td>
							<td><a href="product?action=edit&id=<%=p.getProductId()%>"
								class="btn btn-warning btn-sm">Edit</a> <a
								href="product?action=delete&id=<%=p.getProductId()%>"
								class="btn btn-danger btn-sm"
								onclick="return confirm('Delete this product?')">Delete</a></td>
						</tr>
						<%
						}
						}
						%>
					</tbody>
				</table>
			</div>
		</div>

	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>