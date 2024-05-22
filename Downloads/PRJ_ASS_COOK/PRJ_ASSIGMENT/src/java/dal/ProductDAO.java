/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.util.List;
import model.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Category;

public class ProductDAO extends DBContext {

    public List<Product> getAll() {
        List<Product> ls = new ArrayList<>();
        String sql = "SELECT [ID]\n"
                + "      ,[name]\n"
                + "      ,[price]\n"
                + "      ,[describe]\n"
                + "      ,[image]\n"
                + "      ,[cid]\n"
                + "  FROM [dbo].[Product]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product c = new Product();
                c.setId(rs.getString("ID"));
                c.setName(rs.getString("name"));
                c.setPrice(rs.getInt("price"));
                c.setDescribe(rs.getString("describe"));
                c.setImage(rs.getString("image"));
                c.setCid(rs.getInt("cid"));
                ls.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return ls;
    }

    public Product getLast() {
        String sql = "select top 1 * from Product\n"
                + "order by id desc";
        Product c = new Product();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                c.setId(rs.getString("ID"));
                c.setName(rs.getString("name"));
                c.setPrice(rs.getInt("price"));
                c.setCid(rs.getInt("cid"));
                c.setDescribe(rs.getString("describe"));
                c.setImage(rs.getString("image"));
            }
        } catch (Exception e) {
        }
        return c;
    }

    public Product getMinPrice() {
        String sql = "SELECT top (1)[ID]\n"
                + "      ,[name]\n"
                + "      ,[price]\n"
                + "      ,[cid]\n"
                + "      ,[image]\n"
                + "      ,[describe]\n"
                + "  FROM [dbo].[Product]\n"
                + "  order by price";
        Product c = new Product();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                c.setId(rs.getString("ID"));
                c.setName(rs.getString("name"));
                c.setPrice(rs.getInt("price"));
                c.setCid(rs.getInt("cid"));
                c.setDescribe(rs.getString("describe"));
                c.setImage(rs.getString("image"));
            }
        } catch (Exception e) {
        }
        return c;
    }

    public Product getMaxPrice() {
        String sql = "SELECT top (1)[ID]\n"
                + "      ,[name]\n"
                + "      ,[price]\n"
                + "      ,[cid]\n"
                + "      ,[image]\n"
                + "      ,[describe]\n"
                + "  FROM [dbo].[Product]\n"
                + "  order by price desc";
        Product c = new Product();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {

                c.setId(rs.getString("ID"));
                c.setName(rs.getString("name"));
                c.setPrice(rs.getInt("price"));
                c.setCid(rs.getInt("cid"));
                c.setDescribe(rs.getString("describe"));
                c.setImage(rs.getString("image"));
            }
        } catch (Exception e) {
        }
        return c;
    }

    public Product getProductById(String id) {
        String sql = "SELECT [ID]\n"
                + "      ,[name]\n"
                + "      ,[price]\n"
                + "      ,[describe]\n"
                + "      ,[image]\n"
                + "      ,[cid]\n"
                + "  FROM [dbo].[Product] where ID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Product c = new Product();
                c.setId(rs.getString("ID"));
                c.setName(rs.getString("name"));
                c.setPrice(rs.getInt("price"));
                c.setDescribe(rs.getString("describe"));
                c.setImage(rs.getString("image"));
                c.setCid(rs.getInt("cid"));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    //xoa
    public void delete(String id) {
        String sql = "Delete from [dbo].[Product]\n"
                + "     where ID =? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

//    public void insert(Product c) {
//        String sql = "insert into Categories values(?,?,?)";
//        try {
//            PreparedStatement st = connection.prepareStatement(sql);
//            st.setInt(1, c.getId());
//            st.setString(2, c.getName());
//            st.setString(3, c.getDescribe());
//            st.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//    }
    // check id da ton tai chua
    public void insert(Product c) {
        String sql = "INSERT INTO [dbo].[Product]\n"
                + "           ([name]\n"
                + "           ,[price]\n"
                + "           ,[cid]\n"
                + "           ,[describe]\n"
                + "           ,[image])\n"
                + "     VALUES\n"
                + "           (?,?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, c.getName());
            st.setInt(2, c.getPrice());
            st.setInt(3, c.getCid());
            st.setString(4, c.getDescribe());
            st.setString(5, c.getImage());

            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // update 
    public void update(Product c) {
        String sql = "UPDATE [dbo].[Product]\n"
                + "   SET [name] = ?\n"
                + "      ,[price] = ?\n"
                + "      ,[describe] = ?\n"
                + "      ,[image] = ?\n"
                + " WHERE ID =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, c.getName());
            st.setInt(2, c.getPrice());
            st.setString(3, c.getDescribe());
            st.setString(4, c.getImage());
            st.setString(5, c.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Product> searchProductByname(String name) throws SQLException {
        ArrayList<Product> ls = new ArrayList<>();
        try {
            String sql = "SELECT [ID]\n"
                    + "      ,[name]\n"
                    + "      ,[price]\n"
                    + "      ,[cid]\n"
                    + "      ,[describe]\n"
                    + "      ,[image]\n"
                    + "  FROM [dbo].[Product] where [name] like ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + name + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product c = new Product();
                c.setId(rs.getString("ID"));
                c.setName(rs.getString("name"));
                c.setPrice(rs.getInt("price"));
                c.setCid(rs.getInt("cid"));
                c.setDescribe(rs.getString("describe"));
                c.setImage(rs.getString("image"));
                ls.add(c);

            }

        } catch (SQLException e) {
        }
        return ls;
    }

    public List<Product> sortProductByPriceUp(String cid) throws SQLException {
        ArrayList<Product> ls = new ArrayList<>();
        try {
            String sql = "SELECT [ID]\n"
                    + "      ,[name]\n"
                    + "      ,[price]\n"
                    + "      ,[cid]\n"
                    + "      ,[image]\n"
                    + "      ,[describe]\n"
                    + "  FROM [dbo].[Product] \n"
                    + "  where cid=?\n"
                    + "  order by price";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, cid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product c = new Product();
                c.setId(rs.getString("ID"));
                c.setName(rs.getString("name"));
                c.setPrice(rs.getInt("price"));
                c.setCid(rs.getInt("cid"));
                c.setDescribe(rs.getString("describe"));
                c.setImage(rs.getString("image"));
                ls.add(c);

            }

        } catch (SQLException e) {
        }
        return ls;
    }

    public List<Product> sortProductByPriceDown(String cid) throws SQLException {
        ArrayList<Product> ls = new ArrayList<>();
        try {
            String sql = "SELECT [ID]\n"
                    + "      ,[name]\n"
                    + "      ,[price]\n"
                    + "      ,[cid]\n"
                    + "      ,[image]\n"
                    + "      ,[describe]\n"
                    + "  FROM [dbo].[Product] \n"
                    + "  where cid=?\n"
                    + "  order by price desc";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, cid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product c = new Product();
                c.setId(rs.getString("ID"));
                c.setName(rs.getString("name"));
                c.setPrice(rs.getInt("price"));
                c.setCid(rs.getInt("cid"));
                c.setDescribe(rs.getString("describe"));
                c.setImage(rs.getString("image"));
                ls.add(c);

            }

        } catch (SQLException e) {
        }
        return ls;
    }

    public List<Product> sortProductByNameUp(String cid) throws SQLException {
        ArrayList<Product> ls = new ArrayList<>();
        try {
            String sql = "SELECT [ID]\n"
                    + "      ,[name]\n"
                    + "      ,[price]\n"
                    + "      ,[cid]\n"
                    + "      ,[image]\n"
                    + "      ,[describe]\n"
                    + "  FROM [dbo].[Product] \n"
                    + "  where cid=?\n"
                    + "  order by name";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, cid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product c = new Product();
                c.setId(rs.getString("ID"));
                c.setName(rs.getString("name"));
                c.setPrice(rs.getInt("price"));
                c.setCid(rs.getInt("cid"));
                c.setDescribe(rs.getString("describe"));
                c.setImage(rs.getString("image"));
                ls.add(c);

            }

        } catch (SQLException e) {
        }
        return ls;
    }

    public List<Product> sortProductByNameDown(String cid) throws SQLException {
        ArrayList<Product> ls = new ArrayList<>();
        try {
            String sql = "SELECT [ID]\n"
                    + "      ,[name]\n"
                    + "      ,[price]\n"
                    + "      ,[cid]\n"
                    + "      ,[image]\n"
                    + "      ,[describe]\n"
                    + "  FROM [dbo].[Product] \n"
                    + "  where cid=?\n"
                    + "  order by name desc";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, cid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product c = new Product();
                c.setId(rs.getString("ID"));
                c.setName(rs.getString("name"));
                c.setPrice(rs.getInt("price"));
                c.setCid(rs.getInt("cid"));
                c.setDescribe(rs.getString("describe"));
                c.setImage(rs.getString("image"));
                ls.add(c);

            }

        } catch (SQLException e) {
        }
        return ls;
    }

    public List<Product> searchProductByPrice(String pricehead, String priceend) throws SQLException {
        ArrayList<Product> ls = new ArrayList<>();
        try {
            String sql = "SELECT [ID]\n"
                    + "      ,[name]\n"
                    + "      ,[price]\n"
                    + "      ,[cid]\n"
                    + "      ,[describe]\n"
                    + "      ,[image]\n"
                    + "  FROM [dbo].[Product] where price>=? and price <?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, pricehead);
            st.setString(2, priceend);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product c = new Product();
                c.setId(rs.getString("ID"));
                c.setName(rs.getString("name"));
                c.setPrice(rs.getInt("price"));
                c.setCid(rs.getInt("cid"));
                c.setDescribe(rs.getString("describe"));
                c.setImage(rs.getString("image"));
                ls.add(c);
            }

        } catch (SQLException e) {
        }
        return ls;
    }

    public ArrayList<Product> getProductByCidPrice(String cid, String head, String end) throws SQLException {

        ArrayList<Product> list = new ArrayList<>();
        if (head == null && end == null && cid != null) {
            String sql = "SELECT [ID]\n"
                    + "      ,[name]\n"
                    + "      ,[price]\n"
                    + "      ,[cid]\n"
                    + "      ,[image]\n"
                    + "      ,[describe]\n"
                    + "  FROM [dbo].[Product] where cid = ?";
            try {
                PreparedStatement st = connection.prepareStatement(sql);
                st.setString(1, cid);

                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    Product c = new Product();
                    c.setId(rs.getString("ID"));
                    c.setName(rs.getString("name"));
                    c.setPrice(rs.getInt("price"));
                    c.setCid(rs.getInt("cid"));
                    c.setDescribe(rs.getString("describe"));
                    c.setImage(rs.getString("image"));
                    list.add(c);
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        if (cid == null && head != null && end != null) {
            String sql = "SELECT [ID]\n"
                    + "      ,[name]\n"
                    + "      ,[price]\n"
                    + "      ,[cid]\n"
                    + "      ,[describe]\n"
                    + "      ,[image]\n"
                    + "  FROM [dbo].[Product] where price>=? and price <?";
            try {
                PreparedStatement st = connection.prepareStatement(sql);
                st.setString(1, head);
                st.setString(2, end);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    Product c = new Product();
                    c.setId(rs.getString("ID"));
                    c.setName(rs.getString("name"));
                    c.setPrice(rs.getInt("price"));
                    c.setCid(rs.getInt("cid"));
                    c.setDescribe(rs.getString("describe"));
                    c.setImage(rs.getString("image"));
                    list.add(c);
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        } else if (cid != null && head != null && end != null) {
            String sql = "SELECT [ID]\n"
                    + "      ,[name]\n"
                    + "      ,[price]\n"
                    + "      ,[cid]\n"
                    + "      ,[describe]\n"
                    + "      ,[image]\n"
                    + "  FROM [dbo].[Product] where cid = ? and price>=? and price <?";
            try {
                PreparedStatement st = connection.prepareStatement(sql);
                st.setString(1, cid);
                st.setString(2, head);
                st.setString(3, end);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    Product c = new Product();
                    c.setId(rs.getString("ID"));
                    c.setName(rs.getString("name"));
                    c.setPrice(rs.getInt("price"));
                    c.setCid(rs.getInt("cid"));
                    c.setDescribe(rs.getString("describe"));
                    c.setImage(rs.getString("image"));
                    list.add(c);
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return list;
    }

    public List<Product> randomRelative(String id, String cid) throws SQLException {
        ArrayList<Product> ls = new ArrayList<>();
        try {
            String sql = "SELECT top (4) [ID]\n"
                    + "      ,[name]\n"
                    + "      ,[price]\n"
                    + "      ,[cid]\n"
                    + "      ,[image]\n"
                    + "      ,[describe]\n"
                    + "  FROM [PRJ301].[dbo].[Product]\n"
                    + "  where id!= ? and cid = ? \n"
                    + "  order by NEWID()";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            st.setString(2, cid);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Product c = new Product();
                c.setId(rs.getString("ID"));
                c.setName(rs.getString("name"));
                c.setPrice(rs.getInt("price"));
                c.setCid(rs.getInt("cid"));
                c.setDescribe(rs.getString("describe"));
                c.setImage(rs.getString("image"));
                ls.add(c);

            }

        } catch (SQLException e) {
        }
        return ls;
    }
//san pham moi dc add vao db

    public List<Product> topNew() throws SQLException {
        ArrayList<Product> ls = new ArrayList<>();
        try {
            String sql = "SELECT TOP (4) [ID]\n"
                    + "      ,[name]\n"
                    + "      ,[price]\n"
                    + "      ,[cid]\n"
                    + "      ,[image]\n"
                    + "      ,[describe]\n"
                    + "  FROM [PRJ301].[dbo].[Product]\n"
                    + "  order by id desc";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Product c = new Product();
                c.setId(rs.getString("ID"));
                c.setName(rs.getString("name"));
                c.setPrice(rs.getInt("price"));
                c.setCid(rs.getInt("cid"));
                c.setDescribe(rs.getString("describe"));
                c.setImage(rs.getString("image"));
                ls.add(c);
            }

        } catch (SQLException e) {
        }
        return ls;
    }
//so luong ban duoc nhieu nhat

    public List<Product> mostSold() throws SQLException {
        ArrayList<Product> ls = new ArrayList<>();
        try {
            String sql = "select top(4) [pid]\n"
                    + "	  ,[name]\n"
                    + "      ,p.[price]\n"
                    + "      ,[cid]\n"
                    + "      ,[image]\n"
                    + "      ,[describe]\n"
                    + "  FROM OrderLine ol join product p\n"
                    + "  on ol.pid = p.ID\n"
                    + "  order by quantity desc";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Product c = new Product();
                c.setId(rs.getString("pid"));
                c.setName(rs.getString("name"));
                c.setPrice(rs.getInt("price"));
                c.setCid(rs.getInt("cid"));
                c.setDescribe(rs.getString("describe"));
                c.setImage(rs.getString("image"));
                ls.add(c);
            }

        } catch (SQLException e) {
        }
        return ls;
    }
//tong doanh thu cao nhat

    public List<Product> topFeature() throws SQLException {
        ArrayList<Product> ls = new ArrayList<>();
        try {
            String sql = "select top(4) [pid]\n"
                    + "	  ,[name]\n"
                    + "      ,p.[price]\n"
                    + "      ,p.[cid]\n"
                    + "      ,[image]\n"
                    + "      ,[describe]\n"
                    + "  FROM OrderLine ol join product p\n"
                    + "  on ol.pid = p.ID\n"
                    + "  join [Order] o \n"
                    + "  on o.id = ol.oid\n"
                    + "  order by o.totalmoney desc";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Product c = new Product();
                c.setId(rs.getString("pid"));
                c.setName(rs.getString("name"));
                c.setPrice(rs.getInt("price"));
                c.setCid(rs.getInt("cid"));
                c.setDescribe(rs.getString("describe"));
                c.setImage(rs.getString("image"));
                ls.add(c);
            }

        } catch (SQLException e) {
        }
        return ls;
    }

    public Product getHighestEarningProduct() {
        String sql = "select top(1) [pid]\n"
                + "	  ,[name]\n"
                + "      ,p.[price]\n"
                + "      ,p.[cid]\n"
                + "      ,[image]\n"
                + "      ,[describe]\n"
                + "	  ,o.totalmoney\n"
                + "  FROM OrderLine ol join product p\n"
                + "  on ol.pid = p.ID\n"
                + "  join [Order] o \n"
                + "  on o.id = ol.oid\n"
                + "  order by o.totalmoney desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Product c = new Product();
                c.setId(rs.getString("pid"));
                c.setName(rs.getString("name"));
                c.setPrice(rs.getInt("price"));
                c.setDescribe(rs.getString("describe"));
                c.setImage(rs.getString("image"));
                c.setCid(rs.getInt("cid"));
                c.setTotal(rs.getInt("totalmoney"));

                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Product getLowestEarningProduct() {
        String sql = "select top(1) [pid]\n"
                + "	  ,[name]\n"
                + "      ,p.[price]\n"
                + "      ,p.[cid]\n"
                + "      ,[image]\n"
                + "      ,[describe]\n"
                + "	  ,o.totalmoney\n"
                + "  FROM OrderLine ol join product p\n"
                + "  on ol.pid = p.ID\n"
                + "  join [Order] o \n"
                + "  on o.id = ol.oid\n"
                + "  order by o.totalmoney  ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Product c = new Product();
                c.setId(rs.getString("pid"));
                c.setName(rs.getString("name"));
                c.setPrice(rs.getInt("price"));
                c.setDescribe(rs.getString("describe"));
                c.setImage(rs.getString("image"));
                c.setCid(rs.getInt("cid"));
                c.setTotal(rs.getInt("totalmoney"));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    //so luong nhieu nhat ngay hnay
    // ngay hnay last =0, 3 ngay qua: last=2, 7 ngay qua: last =6
    public List<Product> mostSoldInXDay(int last) throws SQLException {
        ArrayList<Product> ls = new ArrayList<>();
        try {
            String sql = "SELECT TOP (4)[date]\n"
                    + "	  ,p.cid\n"
                    + "	  ,p.ID\n"
                    + "	  ,p.name\n"
                    + "	  ,p.price\n"
                    + "	  ,p.image\n"
                    + "	  ,p.describe\n"
                    + "      ,ol.quantity\n"
                    + "      ,[totalmoney]\n"
                    + "  FROM [dbo].[Order] o join OrderLine ol\n"
                    + "  on o.id = ol.oid\n"
                    + "  join Product p\n"
                    + "  on p.ID = ol.pid\n"
                    + "  where day(date) between day(GETDATE()-?) and  day(GETDATE()) and totalmoney!=0\n"
                    + "  order by ol.quantity desc";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, last);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Product c = new Product();
                c.setId(rs.getString("ID"));
                c.setName(rs.getString("name"));
                c.setPrice(rs.getInt("price"));
                c.setCid(rs.getInt("cid"));
                c.setDescribe(rs.getString("describe"));
                c.setImage(rs.getString("image"));
                c.setDate(rs.getString("date"));
                c.setQuantity(rs.getInt("quantity"));
                c.setTotal(rs.getInt("totalmoney"));
                ls.add(c);
            }

        } catch (SQLException e) {
        }
        return ls;
    }

    public List<Product> mostRevenueInXDay(int last) throws SQLException {
        ArrayList<Product> ls = new ArrayList<>();
        try {
            String sql = "SELECT TOP (4)[date]\n"
                    + "		  ,p.cid\n"
                    + "		  ,p.ID\n"
                    + "		  ,p.name\n"
                    + "		  ,p.price\n"
                    + "		  ,p.image\n"
                    + "		  ,p.describe\n"
                    + "		  ,ol.quantity\n"
                    + "		  ,[totalmoney]\n"
                    + "	  FROM [dbo].[Order] o join OrderLine ol\n"
                    + "	  on o.id = ol.oid\n"
                    + "	  join Product p\n"
                    + "	  on p.ID = ol.pid\n"
                    + "	  where day(date) between day(GETDATE()-?) and  day(GETDATE()) and totalmoney!=0\n"
                    + "	  order by totalmoney desc";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, last);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Product c = new Product();
                c.setId(rs.getString("ID"));
                c.setName(rs.getString("name"));
                c.setPrice(rs.getInt("price"));
                c.setCid(rs.getInt("cid"));
                c.setDescribe(rs.getString("describe"));
                c.setImage(rs.getString("image"));
                c.setDate(rs.getString("date"));
                c.setQuantity(rs.getInt("quantity"));
                c.setTotal(rs.getInt("totalmoney"));
                ls.add(c);
            }

        } catch (SQLException e) {
        }
        return ls;
    }

    public static void main(String[] args) throws SQLException {
        ProductDAO d = new ProductDAO();
        List<Product> ls = d.getAll();
        System.out.println(d.mostRevenueInXDay(0));
    }

}
