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
           SELECT p.id, p.name, p.price, p.description, p.image, pc.quantity, p.rating,p.rating,p.restaurant_id,p.category_id
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
                product.setRating(rs.getFloat("rating"));
                product.setRestaurantId(rs.getInt("restaurant_id"));
                product.setCategoryId(rs.getInt("category_id"));

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
            // Check if cart exists for the customer
            xSql = "SELECT id FROM Cart WHERE customer_id = ?";
            ps = con.prepareStatement(xSql);
            ps.setInt(1, customerId);
            rs = ps.executeQuery();
            int cartId;
            if (rs.next()) {
                cartId = rs.getInt("id");
                System.out.println("Cart exists. Cart ID: " + cartId);
            } else {
                // Create a new cart if it doesn't exist
                xSql = "INSERT INTO Cart (customer_id) VALUES (?)";
                ps = con.prepareStatement(xSql, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setInt(1, customerId);
                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
                rs.next();
                cartId = rs.getInt(1);
                System.out.println("New cart created. Cart ID: " + cartId);
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
                System.out.println("Updated product in cart. Product ID: " + productId + ", New Quantity: " + (existingQuantity + quantity));
            } else {
                // Add product to cart
                xSql = "INSERT INTO product_cart (cart_id, product_id, quantity) VALUES (?, ?, ?)";
                ps = con.prepareStatement(xSql);
                ps.setInt(1, cartId);
                ps.setInt(2, productId);
                ps.setInt(3, quantity);
                ps.executeUpdate();
                System.out.println("Added product to cart. Product ID: " + productId + ", Quantity: " + quantity);
            }

            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCartQuantity(int customerId, int productId, int quantity) {
    try {
        String xSql = """
            UPDATE pc
            SET pc.quantity = ?
            FROM product_cart pc
            JOIN Cart c ON pc.cart_id = c.id
            WHERE c.customer_id = ? AND pc.product_id = ?""";
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
     public void clearCart(int customerId) {
        try {
            // Lấy cart_id của khách hàng
            xSql = "SELECT id FROM Cart WHERE customer_id = ?";
            ps = con.prepareStatement(xSql);
            ps.setInt(1, customerId);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                int cartId = rs.getInt("id");

                // Xóa tất cả các sản phẩm trong giỏ hàng của khách hàng
                xSql = "DELETE FROM product_cart WHERE cart_id = ?";
                ps = con.prepareStatement(xSql);
                ps.setInt(1, cartId);
                ps.executeUpdate();
            }
            
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            CartDAO dao = new CartDAO();
            // Thêm sản phẩm vào giỏ hàng
            int customerId = 2; // Thay đổi ID khách hàng cho phù hợp
            int productId = 6;
            int quantity = 1;
            List<CartItem> list = dao.getProductsInCart(customerId);

            for (CartItem b : list) {
                System.out.println(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
