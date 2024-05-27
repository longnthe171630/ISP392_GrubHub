/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author manh0
 */
public class ProductDAO extends MyDAO {

    public List<Product> getProducts() {
        List<Product> t = new ArrayList<>();
        xSql = "select * from Product ";
        int xProductId;
        String xName;
        int xPrice;
        String xDescription;
        String xImage;
        boolean xStatus;
        Date xCreate_date;
        int xCategory_id;
        Product x;
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                xProductId = rs.getInt("id");
                xName = rs.getString("name");
                xPrice = rs.getInt("price");
                xDescription = rs.getString("description");
                xImage = rs.getString("image");
                xStatus = rs.getBoolean("status");
                xCreate_date = rs.getDate("create_date");
                xCategory_id = rs.getInt("categoryID");
                x = new Product(xProductId, xName, xPrice, xDescription, xImage, xStatus, xCreate_date, xCategory_id);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public List<Product> getProducts(String xxName) {
        List<Product> t = new ArrayList<>();
        xSql = "select * from Product where name like ?";
        int xProductId;
        String xName;
        int xPrice;
        String xDescription;
        String xImage;
        boolean xStatus;
        Date xCreate_date;
        int xCategory_id;
        Product x;
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, "%" + xxName + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                xProductId = rs.getInt("id");
                xName = rs.getString("name");
                xPrice = rs.getInt("price");
                xDescription = rs.getString("description");
                xImage = rs.getString("image");
                xStatus = rs.getBoolean("status");
                xCreate_date = rs.getDate("create_date");
                xCategory_id = rs.getInt("categoryID");
                x = new Product(xProductId, xName, xPrice, xDescription, xImage, xStatus, xCreate_date, xCategory_id);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public List<Product> getProductByCID(String cid) {
        List<Product> t = new ArrayList<>();
        xSql = "SELECT * FROM Product WHERE category_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, cid);
            rs = ps.executeQuery();
            while (rs.next()) {
                int xProductId = rs.getInt("id");
                String xName = rs.getString("name");
                int xPrice = rs.getInt("price");
                String xDescription = rs.getString("description");
                String xImage = rs.getString("image");
                boolean xStatus = rs.getBoolean("status");
                Date xCreate_date = rs.getDate("create_date");
                int xCategory_id = rs.getInt("categoryID");

                Product x = new Product(xProductId, xName, xPrice, xDescription, xImage, xStatus, xCreate_date, xCategory_id);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public Product getProduct(int ProductId) {
        xSql = "SELECT * FROM Product WHERE ID = ?";
        Product x = null;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, ProductId);
            rs = ps.executeQuery();
            if (rs.next()) {
                int xProductId = rs.getInt("id");
                String xName = rs.getString("name");
                int xPrice = rs.getInt("price");
                String xDescription = rs.getString("description");
                String xImage = rs.getString("image");
                boolean xStatus = rs.getBoolean("status");
                Date xCreate_date = rs.getDate("create_date");
                int xCategory_id = rs.getInt("categoryID");

                x = new Product(xProductId, xName, xPrice, xDescription, xImage, xStatus, xCreate_date, xCategory_id);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }

    public Product getProduct(String xxProductId) {
        xSql = "SELECT * FROM Product WHERE ID = ?";
        Product x = null;
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, xxProductId);
            rs = ps.executeQuery();
            if (rs.next()) {
                 int xProductId = rs.getInt("id");
                String xName = rs.getString("name");
                int xPrice = rs.getInt("price");
                String xDescription = rs.getString("description");
                String xImage = rs.getString("image");
                boolean xStatus = rs.getBoolean("status");
                Date xCreate_date = rs.getDate("create_date");
                int xCategory_id = rs.getInt("categoryID");

                x = new Product(xProductId, xName, xPrice, xDescription, xImage, xStatus, xCreate_date, xCategory_id);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }

    public void insert(Product x) {
        xSql = "INSERT INTO Product (Title, AuthorID, Description, Price, Quantity, PublicationDate, ProductImage, CategoryID, Status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, x.getName());
            ps.setInt(2, x.getPrice());
            ps.setString(3, x.getDescription());
            ps.setString(4, x.getImage());
            ps.setBoolean(5, x.isStatus());
            ps.setDate(6, new java.sql.Date(x.getCreate_date().getTime()));
            ps.setInt(7, x.getCategory_id());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int xxProductId) {
        xSql = "delete from Product where id=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, xxProductId);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateProduct(int id, String xName, int xPrice, String xDescription, String xImage, boolean xStatus, Date xCreate_date, int category_id) {
        xSql = "UPDATE Product SET Title=?, AuthorID=?, Description=?, Price=?, Quantity=?, PublicationDate=?, ProductImage=?, CategoryID=?, Status=? WHERE ProductID=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, xName);
            ps.setInt(2, xPrice);
            ps.setString(3, xDescription);
            ps.setString(4, xImage);
            ps.setBoolean(5, xStatus);
            ps.setDate(6, new java.sql.Date(xCreate_date.getTime()));
            ps.setInt(7, category_id);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            ProductDAO dao = new ProductDAO();
            List<Product> list = dao.getProducts();

            for (Product b : list) {
                System.out.println(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
