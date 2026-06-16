# ProductApp

A simple Java Web Application for managing products using **Servlet + JSP + JDBC + MySQL**, built with Eclipse and deployed on Apache Tomcat.

## Features

- Add a new product
- View all products in a table
- Edit an existing product
- Delete a product
- Responsive UI with Bootstrap 5

## Tech Stack

| Layer | Technology |
|---|---|
| Frontend | JSP, Bootstrap 5 |
| Backend | Java Servlet (Jakarta EE) |
| Database | MySQL |
| Driver | MySQL Connector/J 9.x |
| Server | Apache Tomcat 9 |
| IDE | Eclipse |

## Project Structure

```
productapp/
├── src/main/java/com/productapp/
│   ├── controller/
│   │   └── ProductServlet.java      # Handles GET (list/edit/delete) and POST (add/update)
│   ├── dao/
│   │   └── ProductDAO.java          # CRUD SQL operations via JDBC
│   ├── model/
│   │   └── Product.java             # Product entity (productId, productName, price)
│   ├── service/
│   │   └── ProductService.java      # Service layer delegating to DAO
│   └── util/
│       └── DBConnection.java        # MySQL connection utility
└── src/main/webapp/
    ├── index.jsp                    # JSP view (scriptlet-based, no JSTL)
    └── WEB-INF/
        └── web.xml                  # Deployment descriptor
```

## Database Setup

Run the following SQL in MySQL Workbench or CLI:

```sql
CREATE DATABASE product;

USE product;

CREATE TABLE product (
    product_id    INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    product_name  VARCHAR(100) NOT NULL,
    price         DECIMAL(10,2) NOT NULL,
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

## Configuration

Update the DB credentials in `DBConnection.java`:

```java
private static final String URL  = "jdbc:mysql://localhost:3306/product";
private static final String USER = "root";
private static final String PASSWORD = "your_password";
```

## How to Run

1. Clone the repository
   ```
   git clone https://github.com/Sanidhyavyas/ProductApp.git
   ```
2. Import into Eclipse as an **Existing Project**
3. Add `mysql-connector-j-x.x.x.jar` to the build path
4. Configure a Tomcat 9 server in Eclipse
5. Right-click project → **Run As → Run on Server**
6. Open `http://localhost:8080/productapp/product`

## How Edit & Delete Work

- **Edit** — clicking Edit sends `GET /product?action=edit&id={id}`, the servlet fetches the product from DB, pre-fills the form, and the hidden `productId` field carries the ID on submit to trigger an UPDATE instead of INSERT.
- **Delete** — clicking Delete sends `GET /product?action=delete&id={id}`, the servlet runs the DELETE query and redirects back to the list.
