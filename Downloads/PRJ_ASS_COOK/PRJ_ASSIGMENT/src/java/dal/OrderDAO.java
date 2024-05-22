/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Cart;
import model.User;
import model.Item;
import model.Order;
import model.OrderDateDetail;
//import model.Product;

/**
 *
 * @author dell
 */
public class OrderDAO extends DBContext {

    public void addOrder(User u, Cart cart) {
        LocalDate curDate = java.time.LocalDate.now();
        String date = curDate.toString();
        try {
            String sql = "insert into [Order] values (?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, date);
            ps.setInt(2, u.getId());
            ps.setDouble(3, cart.getTotalMoney());
            ps.executeUpdate();

            String sql1 = "select top 1 id from [Order] order by id desc";
            PreparedStatement ps1 = connection.prepareStatement(sql1);
            ResultSet rs = ps1.executeQuery();
            if (rs.next()) {
                int oid = rs.getInt(1);
                for (Item i : cart.getItems()) {
                    String sql2 = "insert into [OrderLine] values (?,?,?,?)";
                    PreparedStatement ps2 = connection.prepareStatement(sql2);
                    ps2.setInt(1, oid);
                    ps2.setString(2, i.getProduct().getId());
                    ps2.setInt(3, i.getQuantity());
                    ps2.setDouble(4, i.getPrice());
                    ps2.executeUpdate();

                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public int getNumOfOrder() {
        ArrayList<Order> ls = new ArrayList<>();
        String sql = "SELECT  *\n"
                + "  FROM [PRJ301].[dbo].[Order]\n"
                + "  where totalmoney!=0";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Order c = new Order();
                c.setId(rs.getInt("ID"));
                ls.add(c);

            }
        } catch (Exception e) {
        }
        int count = ls.size();
        return count;
    }

    public double getTotalRenevue() {
        ArrayList<Order> ls = new ArrayList<>();
        String sql = "SELECT  [id]\n"
                + "      ,[date]\n"
                + "      ,[cid]\n"
                + "      ,[totalmoney]\n"
                + "  FROM [PRJ301].[dbo].[Order]\n"
                + " where totalmoney!=0";

        double sum = 0;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Order c = new Order();
                c.setTotalmoney(rs.getDouble("totalmoney"));
                ls.add(c);
            }
        } catch (SQLException e) {
        }
        for (int i = 0; i < ls.size(); i++) {
            sum += ls.get(i).getTotalmoney();
        }

        return sum;
    }

//    public double getMostBuy() {
//        ArrayList<Order> ls = new ArrayList<>();
//        String sql = "SELECT  [id]\n"
//                + "      ,[date]\n"
//                + "      ,[cid]\n"
//                + "      ,[totalmoney]\n"
//                + "  FROM [PRJ301].[dbo].[Order]\n"
//                + " where totalmoney!=0";
//
//        double sum = 0;
//        try {
//            PreparedStatement st = connection.prepareStatement(sql);
//            ResultSet rs = st.executeQuery();
//
//            while (rs.next()) {
//                Order c = new Order();
//                c.setTotalmoney(rs.getDouble("totalmoney"));
//                ls.add(c);
//            }
//        } catch (SQLException e) {
//        }
//        for (int i = 0; i < ls.size(); i++) {
//            sum += ls.get(i).getTotalmoney();
//        }
//
//        return sum;
//    }
    public List<Order> getAll() {
        List<Order> ls = new ArrayList<>();
        String sql = "SELECT  [id]\n"
                + "      ,[date]\n"
                + "      ,[cid]\n"
                + "      ,[totalmoney]\n"
                + "  FROM [PRJ301].[dbo].[Order]\n"
                + "  where totalmoney!=0\n"
                + "  order by [date]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Order c = new Order();
                c.setId(rs.getInt("id"));
                c.setDate(rs.getString("date"));
                c.setCusid(rs.getInt("cid"));
                c.setTotalmoney(rs.getInt("totalmoney"));
                ls.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return ls;
    }

    public List<Order> numProductPerDay() {
        List<Order> ls = new ArrayList<>();
        String sql = "SELECT  count([date]) as'Num'\n"
                + "			,date	\n"
                + "         FROM [PRJ301].[dbo].[Order] \n"
                + "	where totalmoney!=0 \n"
                + "	group by [date]";

//        double sum = 0;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Order c = new Order();
                c.setDate(rs.getString("date"));
                c.setNum(rs.getInt("Num"));
                ls.add(c);
            }
        } catch (SQLException e) {

        }

        return ls;
    }

    public List<Order> Date() {
        List<Order> ls = new ArrayList<>();
        String sql = "SELECT  [date]\n"
                + "  FROM [PRJ301].[dbo].[Order]\n"
                + "  where totalmoney!=0";

//        double sum = 0;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Order c = new Order();
                c.setDate(rs.getString("date"));

                ls.add(c);
            }
        } catch (SQLException e) {

        }

        return ls;
    }

    public List<OrderDateDetail> OrderDateDetail(String date) {
        ArrayList<OrderDateDetail> ls = new ArrayList<>();

//        double sum = 0;
        try {
            String sql = "SELECT date\n"
                    + "		,p.[name]\n"
                    + "      ,[quantity]\n"
                    + "      ,p.[price]\n"
                    + "	  , totalmoney\n"
                    + "	FROM [PRJ301].[dbo].[Order] o join OrderLine ol\n"
                    + "	on o.id = ol.oid\n"
                    + "	join Product p\n"
                    + "	on ol.pid= p.ID\n"
                    + "\n"
                    + "	where totalmoney!=0 and [date]=?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, date);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                OrderDateDetail c = new OrderDateDetail();
                c.setDate(rs.getString("date"));
                c.setpName(rs.getString("name"));
                c.setQuantity(rs.getInt("quantity"));
                c.setPrice(rs.getInt("price"));
                c.setTotal(rs.getInt("totalmoney"));
                ls.add(c);
            }
        } catch (SQLException e) {

        }

        return ls;
    }

    public List<Order> mostOrderPerDay() {
        ArrayList<Order> ls = new ArrayList<>();
        String sql = "SELECT top(1)  count([date]) as'Num',date\n"
                + "  FROM [PRJ301].[dbo].[Order] \n"
                + "  where totalmoney!=0 \n"
                + "  group by [date] \n"
                + "  order by 'Num' desc";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Order c = new Order();
                c.setNum(rs.getInt("Num"));
                c.setDate(rs.getString("date"));
                ls.add(c);

            }
        } catch (SQLException e) {
        }
//        int count = ls.get(0).getNum();
        return ls;
    }

    public List<Order> leastOrderPerDay() {
        ArrayList<Order> ls = new ArrayList<>();
        String sql = "SELECT top(1)  count([date]) as'Num',date\n"
                + "  FROM [PRJ301].[dbo].[Order] \n"
                + "  where totalmoney!=0 \n"
                + "  group by [date] \n"
                + "  order by 'Num' ";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Order c = new Order();
                c.setNum(rs.getInt("Num"));
                c.setDate(rs.getString("date"));
                ls.add(c);

            }
        } catch (SQLException e) {
        }
//        int count = ls.get(0).getNum();
        return ls;
    }

    public List<Order> AllDate() {
        ArrayList<Order> ls = new ArrayList<>();
        String sql = "SELECT  [date]\n"
                + "  FROM [dbo].[Order]\n"
                + "  group by date";

        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Order c = new Order();
                c.setDate(rs.getString("date"));
                ls.add(c);

            }
        } catch (SQLException e) {
        }
//        int count = ls.get(0).getNum();
        return ls;
    }

    public String currentDate() {

        String sql = "SELECT top(1) [date]\n"
                + "  FROM [dbo].[Order]\n"
                + "  group by date\n"
                + "  order by date desc";
        String date = null;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Order c = new Order();
                c.setDate(rs.getString("date"));
                date = c.getDate();

            }
        } catch (SQLException e) {
        }
//        int count = ls.get(0).getNum();
        return date;
    }

    public List<OrderDateDetail> OrderDateDetailInXDays(int day) {
        ArrayList<OrderDateDetail> ls = new ArrayList<>();

//        double sum = 0;
        try {
            String sql = "SELECT date\n"
                    + ",p.[name]\n"
                    + ",[quantity]\n"
                    + ",p.[price]\n"
                    + ", totalmoney\n"
                    + "FROM [PRJ301].[dbo].[Order] o join OrderLine ol\n"
                    + "on o.id = ol.oid\n"
                    + "join Product p\n"
                    + "on ol.pid= p.ID\n"
                    + "where totalmoney!=0 and day([date])<=day(getdate()) and day(date) >=day(getdate()-?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, day);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                OrderDateDetail c = new OrderDateDetail();
                c.setDate(rs.getString("date"));
                c.setpName(rs.getString("name"));
                c.setQuantity(rs.getInt("quantity"));
                c.setPrice(rs.getInt("price"));
                c.setTotal(rs.getInt("totalmoney"));
                ls.add(c);
            }
        } catch (SQLException e) {

        }

        return ls;
    }

    public List<OrderDateDetail> OrderDateDetailInAllDays() {
        ArrayList<OrderDateDetail> ls = new ArrayList<>();

//        double sum = 0;
        try {
            String sql = "SELECT date\n"
                    + ",p.[name]\n"
                    + ",[quantity]\n"
                    + ",p.[price]\n"
                    + ", totalmoney\n"
                    + "FROM [PRJ301].[dbo].[Order] o join OrderLine ol\n"
                    + "on o.id = ol.oid\n"
                    + "join Product p\n"
                    + "on ol.pid= p.ID\n"
                    + "where totalmoney!=0";
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                OrderDateDetail c = new OrderDateDetail();
                c.setDate(rs.getString("date"));
                c.setpName(rs.getString("name"));
                c.setQuantity(rs.getInt("quantity"));
                c.setPrice(rs.getInt("price"));
                c.setTotal(rs.getInt("totalmoney"));
                ls.add(c);
            }
        } catch (SQLException e) {

        }

        return ls;
    }

    public List<OrderDateDetail> RevenueUp() {
        ArrayList<OrderDateDetail> ls = new ArrayList<>();

//        double sum = 0;
        try {
            String sql = "SELECT date\n"
                    + ",p.[name]\n"
                    + ",[quantity]\n"
                    + ",p.[price]\n"
                    + ", totalmoney\n"
                    + "FROM [PRJ301].[dbo].[Order] o join OrderLine ol\n"
                    + "on o.id = ol.oid\n"
                    + "join Product p\n"
                    + "on ol.pid= p.ID\n"
                    + "where totalmoney!=0 \n"
                    + "order by totalmoney";
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                OrderDateDetail c = new OrderDateDetail();
                c.setDate(rs.getString("date"));
                c.setpName(rs.getString("name"));
                c.setQuantity(rs.getInt("quantity"));
                c.setPrice(rs.getInt("price"));
                c.setTotal(rs.getInt("totalmoney"));
                ls.add(c);
            }
        } catch (SQLException e) {

        }

        return ls;
    }

    public List<OrderDateDetail> RevenueDown() {
        ArrayList<OrderDateDetail> ls = new ArrayList<>();

//        double sum = 0;
        try {
            String sql = "SELECT date\n"
                    + ",p.[name]\n"
                    + ",[quantity]\n"
                    + ",p.[price]\n"
                    + ", totalmoney\n"
                    + "FROM [PRJ301].[dbo].[Order] o join OrderLine ol\n"
                    + "on o.id = ol.oid\n"
                    + "join Product p\n"
                    + "on ol.pid= p.ID\n"
                    + "where totalmoney!=0 \n"
                    + "order by totalmoney desc";
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                OrderDateDetail c = new OrderDateDetail();
                c.setDate(rs.getString("date"));
                c.setpName(rs.getString("name"));
                c.setQuantity(rs.getInt("quantity"));
                c.setPrice(rs.getInt("price"));
                c.setTotal(rs.getInt("totalmoney"));
                ls.add(c);
            }
        } catch (SQLException e) {

        }

        return ls;
    }

    public List<Order> OrderPerDayUp() {
        List<Order> ls = new ArrayList<>();
        String sql = "SELECT  count([date]) as'Num'\n"
                + ",date\n"
                + "FROM [PRJ301].[dbo].[Order]\n"
                + "where totalmoney!=0\n"
                + "group by [date]\n"
                + "order by 'Num'";

//        double sum = 0;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Order c = new Order();
                c.setDate(rs.getString("date"));
                c.setNum(rs.getInt("Num"));
                ls.add(c);
            }
        } catch (SQLException e) {

        }

        return ls;
    }

    public List<Order> OrderPerDayDown() {
        List<Order> ls = new ArrayList<>();
        String sql = "SELECT  count([date]) as'Num'\n"
                + ",date\n"
                + "FROM [PRJ301].[dbo].[Order]\n"
                + "where totalmoney!=0\n"
                + "group by [date]\n"
                + "order by 'Num' desc";

//        double sum = 0;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Order c = new Order();
                c.setDate(rs.getString("date"));
                c.setNum(rs.getInt("Num"));
                ls.add(c);
            }
        } catch (SQLException e) {

        }

        return ls;
    }

    public List<OrderDateDetail> leastRevenuePerDay() {
        ArrayList<OrderDateDetail> ls = new ArrayList<>();

//        double sum = 0;
        try {
            String sql = "	SELECT top(1) date\n"
                    + "	,p.[name]\n"
                    + "	,[quantity]\n"
                    + "	,p.[price]\n"
                    + "	, totalmoney\n"
                    + "	FROM [PRJ301].[dbo].[Order] o join OrderLine ol\n"
                    + "	on o.id = ol.oid\n"
                    + "	join Product p\n"
                    + "	on ol.pid= p.ID\n"
                    + "	where totalmoney!=0 \n"
                    + "	order by totalmoney ";
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                OrderDateDetail c = new OrderDateDetail();
                c.setDate(rs.getString("date"));
                c.setpName(rs.getString("name"));
                c.setQuantity(rs.getInt("quantity"));
                c.setPrice(rs.getInt("price"));
                c.setTotal(rs.getInt("totalmoney"));
                ls.add(c);
            }
        } catch (SQLException e) {

        }

        return ls;
    }
    
    public List<OrderDateDetail> mostRevenuePerDay() {
        ArrayList<OrderDateDetail> ls = new ArrayList<>();

//        double sum = 0;
        try {
            String sql = "	SELECT top(1) date\n"
                    + "	,p.[name]\n"
                    + "	,[quantity]\n"
                    + "	,p.[price]\n"
                    + "	, totalmoney\n"
                    + "	FROM [PRJ301].[dbo].[Order] o join OrderLine ol\n"
                    + "	on o.id = ol.oid\n"
                    + "	join Product p\n"
                    + "	on ol.pid= p.ID\n"
                    + "	where totalmoney!=0 \n"
                    + "	order by totalmoney desc";
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                OrderDateDetail c = new OrderDateDetail();
                c.setDate(rs.getString("date"));
                c.setpName(rs.getString("name"));
                c.setQuantity(rs.getInt("quantity"));
                c.setPrice(rs.getInt("price"));
                c.setTotal(rs.getInt("totalmoney"));
                ls.add(c);
            }
        } catch (SQLException e) {

        }

        return ls;
    }

    public static void main(String[] args) throws SQLException {
        OrderDAO d = new OrderDAO();

        System.out.println(d.getNumOfOrder());
    }
}
