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
                int xProductId = rs.getInt("product_id");
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
               order by product_id
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
                xProductId = rs.getInt("product_id");
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
                xProductId = rs.getInt("product_id");
                xName = rs.getString("name");
                xPrice = rs.getInt("price");
                xDescription = rs.getString("description");
                xImage = rs.getString("image");
                xStatus = rs.getBoolean("status");
                xCreate_date = rs.getDate("create_date");

                int xQuantity = rs.getInt("quantity");
                Restaurant s = rt.getRestaurantById(rs.getInt("restaurant_id"));
                Category c = cd.getCategoryId(rs.getInt("category_id"));
                x = new Product(xProductId, xName, xPrice, xDescription, xImage, xStatus, xCreate_date,xQuantity, s, c);

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
                int xProductId = rs.getInt("product_id");
                String xName = rs.getString("name");
                int xPrice = rs.getInt("price");
                String xDescription = rs.getString("description");
                String xImage = rs.getString("image");
                boolean xStatus = rs.getBoolean("status");
                Date xCreate_date = rs.getDate("create_date");
                int xQuantity = rs.getInt("quantity");
                Restaurant s = rt.getRestaurantById(rs.getInt("restaurant_id"));
                Category c = cd.getCategoryId(rs.getInt("category_id"));
                Product x = new Product(xProductId, xName, xPrice, xDescription, xImage, xStatus, xCreate_date,xQuantity, s, c);

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
                int xProductId = rs.getInt("product_id");
                String xName = rs.getString("name");
                int xPrice = rs.getInt("price");
                String xDescription = rs.getString("description");
                String xImage = rs.getString("image");
                boolean xStatus = rs.getBoolean("status");
                Date xCreate_date = rs.getDate("create_date");
                int xQuantity = rs.getInt("quantity");
                Restaurant s = rt.getRestaurantById(rs.getInt("restaurant_id"));
                Category c = cd.getCategoryId(rs.getInt("category_id"));
                Product x = new Product(xProductId, xName, xPrice, xDescription, xImage, xStatus, xCreate_date,xQuantity, s, c);
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
        xSql = "SELECT * FROM Product WHERE product_id = ?";
        Product x = null;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, ProductId);
            rs = ps.executeQuery();
            if (rs.next()) {
                int xProductId = rs.getInt("product_id");
                String xName = rs.getString("name");
                int xPrice = rs.getInt("price");
                String xDescription = rs.getString("description");
                String xImage = rs.getString("image");
                boolean xStatus = rs.getBoolean("status");
                Date xCreate_date = rs.getDate("create_date");
                int xQuantity = rs.getInt("quantity");
                Restaurant s = rt.getRestaurantById(rs.getInt("restaurant_id"));
                Category c = cd.getCategoryId(rs.getInt("category_id"));
                x = new Product(xProductId, xName, xPrice, xDescription, xImage, xStatus, xCreate_date,xQuantity, s, c);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }

    public Product getProduct(String xxProductId) {
        xSql = "SELECT * FROM Product WHERE product_id = ?";
        Product x = null;
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, xxProductId);
            rs = ps.executeQuery();
            if (rs.next()) {
                int xProductId = rs.getInt("product_id");
                String xName = rs.getString("name");
                int xPrice = rs.getInt("price");
                String xDescription = rs.getString("description");
                String xImage = rs.getString("image");
                boolean xStatus = rs.getBoolean("status");
                Date xCreate_date = rs.getDate("create_date");
                int xQuantity = rs.getInt("quantity");
                Restaurant s = rt.getRestaurantById(rs.getInt("restaurant_id"));
                Category c = cd.getCategoryId(rs.getInt("category_id"));
                x = new Product(xProductId, xName, xPrice, xDescription, xImage, xStatus, xCreate_date,xQuantity, s, c);
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
            ps.setInt(7, x.getQuantity());
            ps.setInt(8, x.getRestaurant().getId());
            ps.setInt(9, x.getCategory().getId());

            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int xxProductId) {
        xSql = "delete from Product where product_id=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, xxProductId);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateProduct(int product_id, String xName, int xPrice, String xDescription, String xImage, boolean xStatus, Date xCreate_date, int category_product_id) {
        xSql = "UPDATE Product SET Title=?, AuthorID=?, Description=?, Price=?, Quantity=?, PublicationDate=?, ProductImage=?, CategoryID=?, Status=? WHERE ProductID=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, xName);
            ps.setInt(2, xPrice);
            ps.setString(3, xDescription);
            ps.setString(4, xImage);
            ps.setBoolean(5, xStatus);
            ps.setDate(6, new java.sql.Date(xCreate_date.getTime()));
            ps.setInt(7, category_product_id);
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
