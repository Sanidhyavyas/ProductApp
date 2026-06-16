package com.productapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.productapp.model.Product;
import com.productapp.util.DBConnection;

public class ProductDAO {

    // INSERT
    public boolean addProduct(Product product) {

        boolean status = false;

        try {

            Connection con = DBConnection.getConnection();

            String sql =
                    "INSERT INTO product(product_name,price) VALUES(?,?)";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setString(1, product.getProductName());
            ps.setDouble(2, product.getPrice());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                status = true;
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    // SELECT ALL
    public List<Product> getAllProducts() {

        List<Product> list = new ArrayList<>();

        try {

            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM product";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                Product p = new Product();

                p.setProductId(
                        rs.getInt("product_id"));

                p.setProductName(
                        rs.getString("product_name"));

                p.setPrice(
                        rs.getDouble("price"));

                list.add(p);
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // SELECT BY ID
    public Product getProductById(int id) {

        Product p = null;

        try {

            Connection con = DBConnection.getConnection();

            String sql =
                    "SELECT * FROM product WHERE product_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                p = new Product();

                p.setProductId(
                        rs.getInt("product_id"));

                p.setProductName(
                        rs.getString("product_name"));

                p.setPrice(
                        rs.getDouble("price"));
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return p;
    }

    // UPDATE
    public boolean updateProduct(Product product) {

        boolean status = false;

        try {

            Connection con = DBConnection.getConnection();

            String sql =
                    "UPDATE product SET product_name=?, price=? WHERE product_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);
            
            ps.setString(1,
                    product.getProductName());

            ps.setDouble(2,
                    product.getPrice());

            ps.setInt(3,
                    product.getProductId());

            int rows = ps.executeUpdate();

            if (rows > 0) {
                status = true;
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }

    // DELETE
    public boolean deleteProduct(int id) {

        boolean status = false;

        try {

            Connection con = DBConnection.getConnection();

            String sql =
                    "DELETE FROM product WHERE product_id=?";

            PreparedStatement ps =
                    con.prepareStatement(sql);

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                status = true;
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }
}