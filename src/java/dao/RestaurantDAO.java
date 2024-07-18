/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Account;
import model.Address;
import model.Restaurant;

/**
 *
 * @author manh0
 */
public class RestaurantDAO extends MyDAO {

    private AddressDAO ad = new AddressDAO();

    public int getRestaurantIDbyAccID(int accID) {
        xSql = "select id from [dbo].[Restaurant] where account_id = ?";
        int id = -1; // Use a sentinel value to indicate no result found

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, accID);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }

    public List<Restaurant> getListRestaurant() {
        List<Restaurant> list = new ArrayList<>();
        try {
            String sql = "select r.id, r.name, ac.phonenumber, a.details, a.state, a.street \n"
                    + "from Restaurant r \n"
                    + "join Account ac on ac.id= r.account_id  \n"
                    + "join Address a on a.id = ac.address_id";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int xId = rs.getInt("id");
                String xName = rs.getString("name");
                String xPhonenumber = rs.getString("phonenumber");
                String xDetail = rs.getString("details");
                String xState = rs.getString("state");
                String xStreet = rs.getString("street");
                Restaurant a = new Restaurant(xId, xName, xPhonenumber, xDetail, xState, xStreet);
                list.add(a);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    public void insertRestaurant(Restaurant r) {
        xSql = "INSERT INTO [dbo].[Restaurant]\n"
                + "           ([name]\n"
                + "           ,[restaurant_rating]\n"
                + "           ,[account_id])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, r.getName());
            ps.setFloat(2, r.getRestaurant_rating());
            ps.setInt(3, r.getAccount_id());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void updateRestaurantById(int id){
//        xSql = "UPDATE Restaurant SET name = ?,"
//                + "rest"
//    }
    public void updateRestaurant(Restaurant r) {
        xSql = "UPDATE Restaurant SET name = ?, restaurant_rating = ? WHERE id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, r.getName());
            ps.setInt(2, r.getRestaurant_rating());
            ps.setInt(3, r.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Restaurant getRestaurantByAccID_loc(int AccID) {
        String xSql = "Select [id],[name],[restaurant_rating],[account_id] from [dbo].[Restaurant] where account_id =?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, AccID);
            rs = ps.executeQuery();

            if (rs.next()) {
                int resID = rs.getInt("id");
                String name = rs.getString("name");
                int resRating = rs.getInt("restaurant_rating");
                int accID = rs.getInt("account_id");
                Restaurant r = new Restaurant(resID, name, resRating, accID);
                return r;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Restaurant> getRestaurants() {
        List<Restaurant> t = new ArrayList<>();
        xSql = """
                SELECT 
                               r.id, 
                               r.name, 
                               a.phonenumber, 
                               r.restaurant_rating, 
                               r.account_id, 
                               a.address_id 
                           FROM 
                               Restaurant AS r
                           JOIN 
                               Account AS a ON r.account_id = a.id""";
        int xRestaurantId;
        String xName;
        Address a;
        String xPhonenumber;
        int xRestaurantRating;
        int xAccountId;
        Account acc = new Account();
        Restaurant x;
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                xRestaurantId = rs.getInt("id");
                xName = rs.getString("name");
                a = ad.getAddressById(rs.getInt("address_id"));
                xPhonenumber = rs.getString("phonenumber");
                xRestaurantRating = rs.getInt("restaurant_rating");
                xAccountId = rs.getInt("account_id");
                x = new Restaurant(xRestaurantId, xName, xPhonenumber, a, xRestaurantRating, xAccountId);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public List<Restaurant> getRestaurants(String xxName) {
        List<Restaurant> t = new ArrayList<>();
        xSql = "select * from Restaurant where name like ?";
        int xRestaurantId;
        String xName;
        int xAddressId;
        String xPhonenumber;
        int xRestaurantRating;
        int xAccountId;
        Restaurant x;
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, "%" + xxName + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                xRestaurantId = rs.getInt("product_id");
                xName = rs.getString("name");
                Address a = ad.getAddressById(rs.getInt("address_id"));
                xPhonenumber = rs.getString("description");
                xRestaurantRating = rs.getInt("category_id");
                xAccountId = rs.getInt("category_id");
                x = new Restaurant(xRestaurantId, xName, xPhonenumber, a, xRestaurantRating, xAccountId);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public List<Restaurant> getRestaurantByPID(String product_id) {
        List<Restaurant> t = new ArrayList<>();
        xSql = """
               SELECT r.id, r.name, acc.phonenumber, r.restaurant_rating, r.account_id, a.street AS address_street, a.details AS address_details, a.state AS address_state
               FROM Restaurant r
               JOIN Account acc ON r.account_id = acc.id
               JOIN Address a ON acc.address_id = a.id
               WHERE r.id IN (SELECT restaurant_id FROM Product WHERE id = ?)""";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, product_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int xRestaurantId = rs.getInt("id");
                String xName = rs.getString("name");
                Address a = ad.getAddressById(rs.getInt("address_id"));
                String xPhonenumber = rs.getString("phonenumber");
                int xRestaurantRating = rs.getInt("restaurant_id");
                int xAccountId = rs.getInt("account_id");

                Restaurant x = new Restaurant(xRestaurantId, xName, xPhonenumber, a, xRestaurantRating, xAccountId);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    public Restaurant getRestaurantByID_Loc(int resID) {
        xSql = "select * from Restaurant where id = ?";
        Restaurant r = null;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, resID);
            rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int resRating = rs.getInt("restaurant_rating");
                int accID = rs.getInt("account_id");
                r = new Restaurant(id, name, resRating, accID);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }

    public Restaurant getRestaurant(int RestaurantId) {
        xSql = "SELECT * FROM Restaurant WHERE ID = ?";
        Restaurant x = null;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, RestaurantId);
            rs = ps.executeQuery();
            if (rs.next()) {
                int xRestaurantId = rs.getInt("id");
                String xName = rs.getString("name");
                Address a = ad.getAddressById(rs.getInt("address_id"));
                String xPhonenumber = rs.getString("phonenumber");
                int xRestaurantRating = rs.getInt("restaurant_rating");
                int xAccountId = rs.getInt("account_id");

                x = new Restaurant(xRestaurantId, xName, xPhonenumber, a, xRestaurantRating, xAccountId);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return x;
    }

    public Restaurant getRestaurantById(int RestaurantId) {
        xSql = "SELECT * FROM Restaurant WHERE id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, RestaurantId);
            rs = ps.executeQuery();
            if (rs.next()) {
                Restaurant r = new Restaurant();
                r.setId(rs.getInt("id"));
                r.setName(rs.getString("name"));
                r.setRestaurant_rating(rs.getInt("restaurant_rating"));
                return r;
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Restaurant getRestaurant(String xxRestaurantId) {
        String xSql = """
                   SELECT 
                       r.id, 
                       r.name, 
                       a.phonenumber, 
                       r.restaurant_rating, 
                       r.account_id, 
                       a.address_id 
                   FROM 
                       Restaurant AS r
                   JOIN 
                       Account AS a ON r.account_id = a.id 
                   WHERE 
                       r.id = ?""";
        Restaurant restaurant = null;

        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, xxRestaurantId);
            rs = ps.executeQuery();

            if (rs.next()) {
                int xRestaurantId = rs.getInt("id");
                String xName = rs.getString("name");
                String xPhonenumber = rs.getString("phonenumber"); // Corrected here
                Address a = ad.getAddressById(rs.getInt("address_id")); // Corrected to use address_id
                int xRestaurantRating = rs.getInt("restaurant_rating");
                int xAccountId = rs.getInt("account_id");

                restaurant = new Restaurant(xRestaurantId, xName, xPhonenumber, a, xRestaurantRating, xAccountId);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return restaurant;
    }

    public void insert(Restaurant x) {
        xSql = "INSERT INTO Restaurant (Title, AuthorID, Description, Price, Quantity, PublicationDate, RestaurantImage, CategoryID, Status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, x.getName());
            ps.setInt(2, x.getAddress().getId());
            ps.setString(3, x.getPhonenumber());
            ps.setInt(4, x.getRestaurant_rating());
            ps.setInt(5, x.getAccount_id());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int xxRestaurantId) {
        xSql = "delete from Restaurant where product_id=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, xxRestaurantId);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateRestaurant(int product_id, String xName, int xPrice, String xPhonenumber, String xImage, boolean xStatus, Date xCreate_date, int category_product_id) {
        xSql = "UPDATE Restaurant SET Title=?, AuthorID=?, Description=?, Price=?, Quantity=?, PublicationDate=?, RestaurantImage=?, CategoryID=?, Status=? WHERE RestaurantID=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, xName);
            ps.setInt(2, xPrice);
            ps.setString(3, xPhonenumber);
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

    //----------------------New function-------------
    public Restaurant getResByID_VuPL(int id) {
        xSql = "select * from restaurant where id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                int rating = rs.getInt("restaurant_rating");
                int accountId = rs.getInt("account_id");
                return new Restaurant(id, name, rating, accountId);
            }

        } catch (Exception e) {
        }
        return null;
    }
     public void deleteRestaurantByAccountId(int accountId) {
        xSql = "DELETE FROM [dbo].[Restaurant]\n"
                + "      WHERE account_id =?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, accountId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        RestaurantDAO rd = new RestaurantDAO();
       List<Restaurant> rl = rd.getListRestaurant();
        for( Restaurant res: rl){
            System.out.println(res);
    }
}
}
