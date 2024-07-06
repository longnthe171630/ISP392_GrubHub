package dao;

import dao.MyDAO;
import model.Product;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Category;
import model.Restaurant;

/**
 *
 * @author manh0
 */
public class ProductDAO extends MyDAO {

    private CategoryDAO cd = new CategoryDAO();
    private RestaurantDAO rt = new RestaurantDAO();

    public List<Product> getProducts() {
        List<Product> t = new ArrayList<>();
        xSql = "select * from Product ";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int xProductId = rs.getInt("id");
                String xName = rs.getString("name");
                int xPrice = rs.getInt("price");
                int xQuantity = rs.getInt("quantity");
                String xDescription = rs.getString("description");
                String xImage = rs.getString("image");
                boolean xStatus = rs.getBoolean("status");
                float xRating = rs.getFloat("rating");
                Date xCreate_date = rs.getDate("create_date");
                Restaurant s = rt.getRestaurantById(rs.getInt("restaurant_id"));
                Category c = cd.getCategoryId(rs.getInt("category_id"));

                Product x = new Product(xProductId, xName, xPrice, xQuantity, xDescription, xImage, xStatus, xRating, xCreate_date, s, c);

                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public String getNameProductById(int product_id) {
        xSql = "SELECT name FROM Product WHERE ID = ?";
        Product x = null;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, product_id);
            rs = ps.executeQuery();
            if (rs.next()) {
//                int xProduct_id = rs.getInt("id");
                String xName = rs.getString("name");
//                int xPrice = rs.getInt("price");
//                String xDescription = rs.getString("description");
//                String xImage = rs.getString("image");
//                boolean xStatus = rs.getBoolean("status");
//                Date xCreate_date = rs.getDate("create_date");
//                int xCategory_id = rs.getInt("category_id");
//                int xRestaurant_id = rs.getInt("restaurant_id");
//
//                x = new Product(xProduct_id, xName, xPrice, xDescription, xImage, xStatus, xCreate_date, xCategory_id, xRestaurant_id);
                return xName;
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Product> getProducts2() {
        List<Product> t = new ArrayList<>();
        xSql = "SELECT p.id, p.name, p.price, p.description, p.image, p.status, p.create_date, "
                + "p.category_id, p.restaurant_id, r.name AS restaurant_name "
                + "FROM Product p "
                + "JOIN Restaurant r ON p.restaurant_id = r.id "
                + "WHERE p.id IN (1, 2, 3, 4)";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int xProductId = rs.getInt("id");
                String xName = rs.getString("name");
                int xPrice = rs.getInt("price");
                String xDescription = rs.getString("description");
                String xImage = rs.getString("image");
                boolean xStatus = rs.getBoolean("status");
                Date xCreate_date = rs.getDate("create_date");
                Restaurant s = rt.getRestaurantById(rs.getInt("restaurant_id"));
                Category c = cd.getCategoryId(rs.getInt("category_id"));
                Product x = new Product(xProductId, xName, xPrice, xDescription, xImage, xStatus, xCreate_date, xPrice, s, c);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public int getTotalProduct() {
        xSql = "select count(*) from Product ";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public int getTotalProductByCategory(int cate) {
        xSql = "SELECT COUNT(*) FROM Product WHERE category_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, cate);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Product> getProducTop9(int index) {
        List<Product> t = new ArrayList<>();
        xSql = """
               select * from Product 
               order by id
               offset ? rows fetch next 9 rows only""";
        int xProductId;
        String xName;
        int xPrice;
        String xDescription;
        String xImage;
        boolean xStatus;
        Date xCreate_date;
        int xCategory_id;
        int xRestaurant_id;
        Product x;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, (index - 1) * 9);
            rs = ps.executeQuery();

            while (rs.next()) {
                xProductId = rs.getInt("id");
                xName = rs.getString("name");
                xPrice = rs.getInt("price");
                xDescription = rs.getString("description");
                xImage = rs.getString("image");
                xStatus = rs.getBoolean("status");
                xCreate_date = rs.getDate("create_date");
                int xQuantity = rs.getInt("quantity");
                Restaurant s = rt.getRestaurantById(rs.getInt("restaurant_id"));
                Category c = cd.getCategoryId(rs.getInt("category_id"));
                x = new Product(xProductId, xName, xPrice, xDescription, xImage, xStatus, xCreate_date, xQuantity, s, c);
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
        int xRestaurant_id;
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

                int xQuantity = rs.getInt("quantity");
                Restaurant s = rt.getRestaurantById(rs.getInt("restaurant_id"));
                Category c = cd.getCategoryId(rs.getInt("category_id"));
                x = new Product(xProductId, xName, xPrice, xDescription, xImage, xStatus, xCreate_date, xQuantity, s, c);

                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public List<Product> getProductByCID(String cproduct_id, int index) {
        List<Product> t = new ArrayList<>();
        xSql = "SELECT * FROM Product WHERE category_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, cproduct_id);
            ps.setInt(2, (index - 1) * 9);
            rs = ps.executeQuery();
            while (rs.next()) {
                int xProductId = rs.getInt("id");
                String xName = rs.getString("name");
                int xPrice = rs.getInt("price");
                String xDescription = rs.getString("description");
                String xImage = rs.getString("image");
                boolean xStatus = rs.getBoolean("status");
                Date xCreate_date = rs.getDate("create_date");
                int xQuantity = rs.getInt("quantity");
                Restaurant s = rt.getRestaurantById(rs.getInt("restaurant_id"));
                Category c = cd.getCategoryId(rs.getInt("category_id"));
                Product x = new Product(xProductId, xName, xPrice, xDescription, xImage, xStatus, xCreate_date, xQuantity, s, c);

                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public List<Product> getProductByRID(String cproduct_id) {
        List<Product> t = new ArrayList<>();
        xSql = "SELECT * FROM Product WHERE restaurant_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, cproduct_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int xProductId = rs.getInt("id");
                String xName = rs.getString("name");
                int xPrice = rs.getInt("price");
                String xDescription = rs.getString("description");
                String xImage = rs.getString("image");
                boolean xStatus = rs.getBoolean("status");
                Date xCreate_date = rs.getDate("create_date");
                int xQuantity = rs.getInt("quantity");
                Restaurant s = rt.getRestaurantById(rs.getInt("restaurant_id"));
                Category c = cd.getCategoryId(rs.getInt("category_id"));
                Product x = new Product(xProductId, xName, xPrice, xDescription, xImage, xStatus, xCreate_date, xQuantity, s, c);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public List<Product> listTop5() {
        List<Product> t = new ArrayList<>();
        xSql = """
               SELECT TOP 5 p.id, p.name, p.price,p.quantity, p.rating,p.description,p.image,p.create_date,p.restaurant_id,p.category_id,p.status  ,SUM(od.quantity) AS total_quantity
               FROM Orderdetails od
               JOIN product p ON od.product_id = p.id
               GROUP BY p.id, p.name, p.price,p.quantity, p.rating,p.description,p.image,p.create_date,p.restaurant_id,p.category_id,p.status
               ORDER BY total_quantity DESC;""";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int xProductId = rs.getInt("id");
                String xName = rs.getString("name");
                int xPrice = rs.getInt("price");
                String xDescription = rs.getString("description");
                String xImage = rs.getString("image");
                boolean xStatus = rs.getBoolean("status");
                Date xCreate_date = rs.getDate("create_date");
                float xRating = rs.getFloat("rating");
                int xQuantity = rs.getInt("quantity");
                Restaurant s = rt.getRestaurantById(rs.getInt("restaurant_id"));
                Category c = cd.getCategoryId(rs.getInt("category_id"));
                Product x = new Product(xProductId, xName, xPrice, xQuantity, xDescription, xImage, xStatus, xRating, xCreate_date, s, c);
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
        xSql = "SELECT * FROM Product WHERE id = ?";
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
                int xQuantity = rs.getInt("quantity");
                float xRating = rs.getInt("rating");
                int xRestaurant = rs.getInt("restaurant_id");
                int xCategory = rs.getInt("category_id");
                x = new Product(xProductId, xName, xPrice, xQuantity, xDescription, xImage, xStatus, xRating, xCreate_date, xCategory, xRestaurant);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }

    public Product getProduct(String xxProductId) {
        xSql = "SELECT * FROM Product WHERE id = ?";
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
                int xQuantity = rs.getInt("quantity");
                int xRestaurant = rs.getInt("restaurant_id");
                int xCategory = rs.getInt("category_id");
                x = new Product(xProductId, xName, xPrice, xQuantity, xDescription, xImage, xStatus, xPrice, xCreate_date, xCategory, xRestaurant);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }

    public void insert(Product x) {
        String xSql = "INSERT INTO [dbo].[Product] "
                + "([name], [price], [quantity], [description], [image], [status],[rating], [create_date], [category_id], [restaurant_id]) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?,?, ?)";

        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, x.getName());
            ps.setDouble(2, x.getPrice());
            ps.setInt(3, x.getQuantity());
            ps.setString(4, x.getDescription());
            ps.setString(5, x.getImage());
            ps.setBoolean(6, x.isStatus());
            ps.setInt(7, 0);
            ps.setDate(8, new java.sql.Date(new Date().getTime())); // Lấy ngày hiện tại
            ps.setInt(9, x.getCategoryId());
            ps.setInt(10, x.getRestaurantId());

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

    public void updateProduct(Product p) {
        xSql = "UPDATE Product SET name=?, price=?, quantity=?,description=?,image=?, "
                + "status=?, rating=?, category_id=?, restaurant_id=? WHERE id=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, p.getName());
            ps.setInt(2, p.getPrice());
            ps.setInt(3, p.getQuantity());
            ps.setString(4, p.getDescription());
            ps.setString(5, p.getImage());
            ps.setBoolean(6, true);
            ps.setFloat(7, p.getRating());
            ps.setInt(8, p.getCategoryId());
            ps.setInt(9, p.getRestaurantId());
            ps.setInt(10, p.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Product> getProductsRestaurant() {
        List<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM Product"; // Query to select all columns from Product table

        try {
            // Prepare statement and execute query
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            // Process each row in the result set
            while (rs.next()) {
                // Retrieve data from the result set
                int productId = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                String description = rs.getString("description");
                String image = rs.getString("image");
                boolean status = rs.getBoolean("status");
                float rating = rs.getFloat("rating");
                Date createDate = rs.getDate("create_date");
                int categoryId = rs.getInt("category_id");
                int restaurantId = rs.getInt("restaurant_id");

                // Create a new Product object using the retrieved data
                Product p = new Product(productId, name, price, quantity, description, image, status, rating, createDate, categoryId, restaurantId);

                // Add the product to the list
                productList.add(p);
            }

            // Close result set and statement
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Return the list of products
        return productList;
    }

    public Product getProductVu(int ProductId) {
        xSql = "SELECT * FROM Product WHERE id = ?";
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
                int xQuantity = rs.getInt("quantity");
                int xCategoryId = rs.getInt("category_id");
                int xRestaurantId = rs.getInt("restaurant_id");
                float xRating = rs.getFloat("rating");
                x = new Product(xProductId, xName, xPrice, xQuantity, xDescription, xImage, xStatus, xRating, xCreate_date, xCategoryId, xRestaurantId);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }

    public static void main(String[] args) {
        ProductDAO pd = new ProductDAO();
        Product p = pd.getProduct(4);
        System.out.println(p);
    }
}
