/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Address;
import model.Restaurant;

/**
 *
 * @author manh0
 */
public class RestaurantDAO extends MyDAO {

    private AddressDAO ad = new AddressDAO();

    public Restaurant getRestaurantByProductId_Locct(int product_id) {
        xSql = "SELECT * FROM Restaurant WHERE product_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, product_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Restaurant r = new Restaurant(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6));
                return r;
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

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
        xSql = "select * from Restaurant ";
        int xRestaurantId;
        String xName;
        Address a;
        String xPhonenumber;
        int xRestaurantRating;
        int xAccountId;
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

//    public List<Restaurant> getRestaurantByPID(String product_id) {
//        List<Restaurant> t = new ArrayList<>();
//        xSql = "SELECT * FROM Restaurant WHERE product_id = ?";
//        try {
//            ps = con.prepareStatement(xSql);
//            ps.setString(1, product_id);
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                int xRestaurantId = rs.getInt("id");
//                String xName = rs.getString("name");
//                Address a = ad.getAddressById(rs.getInt("address_id"));
//                String xPhonenumber = rs.getString("phonenumber");
//                int xRestaurantRating = rs.getInt("restaurant_id");
//                int xAccountId = rs.getInt("account_id");
//
//                Restaurant x = new Restaurant(xRestaurantId, xName, xPhonenumber, a, xRestaurantRating, xAccountId);
//                t.add(x);
//            }
//            rs.close();
//            ps.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return t;
//    }
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
        xSql = "SELECT * FROM Restaurant WHERE ID = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, RestaurantId);
            rs = ps.executeQuery();
            if (rs.next()) {
                Restaurant r = new Restaurant();
                r.setId(rs.getInt("id"));
                r.setName(rs.getString("name"));
                Address a = ad.getAddressById(rs.getInt("address_id"));
                r.setPhonenumber(rs.getString("phonenumber"));
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
        xSql = "SELECT * FROM Restaurant WHERE ID = ?";
        Restaurant x = null;
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, xxRestaurantId);
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
        Restaurant r = rd.getRestaurantByProductId_Locct(29);
        System.out.println(r);

    }
}
