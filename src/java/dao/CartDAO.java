package dao;

import dao.MyDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.CartItem;
import model.Product;

public class CartDAO extends MyDAO {

    public List<CartItem> getProductsInCart(int customerId) {
        List<CartItem> cartItems = new ArrayList<>();
        xSql = """
           SELECT p.id, p.name, p.price, p.description, p.image, pc.quantity
           FROM product_cart pc 
           JOIN product p ON pc.product_id = p.id
           JOIN Cart c ON pc.cart_id = c.id 
           WHERE c.customer_id = ?""";

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, customerId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getInt("price"));
                product.setDescription(rs.getString("description"));
                product.setImage(rs.getString("image"));
                int quantity = rs.getInt("quantity");

                CartItem cartItem = new CartItem(product, quantity);
                cartItems.add(cartItem);
            }

            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cartItems;
    }

    public void addProductToCart(int customerId, int productId, int quantity) {
        try {
            // Get product details including restaurant_id
            xSql = "SELECT p.restaurant_id FROM product p WHERE p.id = ?";
            ps = con.prepareStatement(xSql);
            ps.setInt(1, productId);
            rs = ps.executeQuery();
            int restaurantId;
            if (rs.next()) {
                restaurantId = rs.getInt("restaurant_id");
            } else {
                throw new SQLException("Product not found with ID: " + productId);
            }

            // Check if cart exists for the customer
            xSql = "SELECT id FROM Cart WHERE customer_id = ?";
            ps = con.prepareStatement(xSql);
            ps.setInt(1, customerId);
            rs = ps.executeQuery();
            int cartId;
            if (rs.next()) {
                cartId = rs.getInt("id");
            } else {
                // Create a new cart if it doesn't exist
                xSql = "INSERT INTO Cart (customer_id) VALUES (?)";
                ps = con.prepareStatement(xSql, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setInt(1, customerId);
                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
                rs.next();
                cartId = rs.getInt(1);
            }

            // Check if product is already in cart
            xSql = "SELECT quantity FROM product_cart WHERE cart_id = ? AND product_id = ?";
            ps = con.prepareStatement(xSql);
            ps.setInt(1, cartId);
            ps.setInt(2, productId);
            rs = ps.executeQuery();
            if (rs.next()) {
                // Update quantity if product already in cart
                int existingQuantity = rs.getInt("quantity");
                xSql = "UPDATE product_cart SET quantity = ? WHERE cart_id = ? AND product_id = ?";
                ps = con.prepareStatement(xSql);
                ps.setInt(1, existingQuantity + quantity);
                ps.setInt(2, cartId);
                ps.setInt(3, productId);
                ps.executeUpdate();
            } else {
                // Add product to cart
                xSql = "INSERT INTO product_cart (cart_id, product_id, quantity) VALUES (?, ?, ?)";
                ps = con.prepareStatement(xSql);
                ps.setInt(1, cartId);
                ps.setInt(2, productId);
                ps.setInt(3, quantity);
                ps.executeUpdate();
            }

            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCartQuantity(int customerId, int productId, int quantity) {
        try {
            xSql = "UPDATE Product_cart SET quantity = ? WHERE cart_id = (SELECT id FROM Cart WHERE customer_id = ?) AND product_id = ?";
            ps = con.prepareStatement(xSql);
            ps.setInt(1, quantity);
            ps.setInt(2, customerId);
            ps.setInt(3, productId);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void clearCart(int customerId) {
        String deleteFromProductCartQuery = "DELETE FROM Product_Cart WHERE cart_id IN (SELECT id FROM Cart WHERE customer_id = ?)";
        String deleteFromCartQuery = "DELETE FROM Cart WHERE customer_id = ?";

        try {
            con.setAutoCommit(false);

            // Delete from Product_Cart table
            try (PreparedStatement ps = con.prepareStatement(deleteFromProductCartQuery)) {
                ps.setInt(1, customerId);
                ps.executeUpdate();
            }

            // Delete from Cart table
            try (PreparedStatement ps = con.prepareStatement(deleteFromCartQuery)) {
                ps.setInt(1, customerId);
                ps.executeUpdate();
            }

            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProductFromCart(int customerId, int productId) {
        try {
            xSql = """
                DELETE pc
                FROM product_cart pc
                JOIN Cart c ON pc.cart_id = c.id
                WHERE c.customer_id = ? AND pc.product_id = ?""";
            ps = con.prepareStatement(xSql);
            ps.setInt(1, customerId);
            ps.setInt(2, productId);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CartDAO cartDAO = new CartDAO();

        // Thiết lập các giá trị đầu vào để kiểm tra
        int customerId = 1; // ID của khách hàng
        int productId = 1;  // ID của sản phẩm
        int quantity = 2;   // Số lượng sản phẩm mới

        // Cập nhật số lượng sản phẩm trong giỏ hàng
        cartDAO.updateCartQuantity(customerId, productId, quantity);

        // In ra thông báo để xác nhận việc cập nhật
        System.out.println("Cart quantity updated successfully.");
    }
}
