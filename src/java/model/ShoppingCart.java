
import java.sql.*;
import java.util.Scanner;

public class ShoppingCart {
    // JDBC URL, username, and password của database
    static final String JDBC_URL = "jdbc:sqlserver://localhost:1433;databaseName=OrderFood";
    static final String USERNAME = "sa";
    static final String PASSWORD = "sa";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)) {
            Scanner scanner = new Scanner(System.in);
            int customerId = 1; // Assume customer ID, you might get this from user input or elsewhere

            // Display danh sách món ăn đã chọn trong giỏ hàng
            displayCartItems(connection, customerId);

            // Chỉnh sửa giỏ hàng
            System.out.println("Bạn có muốn cập nhật giỏ hàng không? (yes/no)");
            String choice = scanner.nextLine().toLowerCase();
            if (choice.equals("yes")) {
                updateCart(connection, scanner, customerId);
            }

            // Tính tổng tiền của giỏ hàng
            double totalAmount = calculateTotal(connection, customerId);
            System.out.println("Tổng tiền: " + totalAmount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Hiển thị danh sách các món ăn đã chọn trong giỏ hàng
    private static void displayCartItems(Connection connection, int customerId) throws SQLException {
        String query = "SELECT p.name, p.price, c.quantity FROM Product p " +
                       "INNER JOIN product_cart pc ON p.product_id = pc.product_id " +
                       "INNER JOIN Cart c ON pc.cart_id = c.cart_id " +
                       "WHERE c.customer_id = ?";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            System.out.println("Danh sách món ăn đã chọn trong giỏ hàng:");
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                System.out.println(name + " - " + price + " - Số lượng: " + quantity);
            }
        }
    }

    // Chỉnh sửa giỏ hàng
    private static void updateCart(Connection connection, Scanner scanner, int customerId) throws SQLException {
        System.out.println("Nhập ID của sản phẩm bạn muốn cập nhật số lượng:");
        int productId = scanner.nextInt();
        System.out.println("Nhập số lượng mới:");
        int newQuantity = scanner.nextInt();

        // Update số lượng của sản phẩm trong giỏ hàng
        String updateQuery = "UPDATE Cart SET quantity = ? WHERE customer_id = ? AND product_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setInt(1, newQuantity);
            preparedStatement.setInt(2, customerId);
            preparedStatement.setInt(3, productId);
            preparedStatement.executeUpdate();
        }
        System.out.println("Giỏ hàng đã được cập nhật!");
    }

    // Tính tổng tiền của giỏ hàng
    private static double calculateTotal(Connection connection, int customerId) throws SQLException {
        String query = "SELECT SUM(p.price * c.quantity) AS total " +
                       "FROM Product p " +
                       "INNER JOIN product_cart pc ON p.product_id = pc.product_id " +
                       "INNER JOIN Cart c ON pc.cart_id = c.cart_id " +
                       "WHERE c.customer_id = ?";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                return resultSet.getDouble("total");
            }
        }
        return 0; // Nếu không có sản phẩm nào trong giỏ hàng, trả về tổng tiền là 0
    }
}
