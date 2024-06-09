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

public class CartDAO extends MyDAO{


    public List<CartItem> getProductsInCart(int customerId) {
        List<CartItem> cartItems = new ArrayList<>();
        xSql = """
           SELECT p.product_id, p.name, p.price, p.description, p.image, pc.quantity
           FROM product_cart pc 
           JOIN product p ON pc.product_id = p.product_id
           JOIN Cart c ON pc.cart_id = c.cart_id 
           WHERE c.customer_id = ?""";

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, customerId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("product_id"));
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
            // Check if cart exists for the customer
            xSql = "SELECT cart_id FROM Cart WHERE customer_id = ?";
            ps = con.prepareStatement(xSql);
            ps.setInt(1, customerId);
            rs = ps.executeQuery();
            int cartId;
            if (rs.next()) {
                cartId = rs.getInt("cart_id");
            } else {
                // Create a new cart if it doesn't exist
                xSql = "INSERT INTO Cart (customer_id, quantity) VALUES (?, 0)";
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
            xSql = """
                UPDATE product_cart pc
                SET pc.quantity = ?
                FROM product_cart pc
                JOIN Cart c ON pc.cart_id = c.cart_id
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
                JOIN Cart c ON pc.cart_id = c.cart_id
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
        try {
            CartDAO dao = new CartDAO();
            List<CartItem> list = dao.getProductsInCart(2);
            for (CartItem b : list) {
                System.out.println(b);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
