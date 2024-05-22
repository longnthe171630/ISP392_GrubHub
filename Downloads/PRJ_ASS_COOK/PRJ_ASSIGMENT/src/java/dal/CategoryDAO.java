/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.util.List;
import model.Category;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Quang Anh
 */
public class CategoryDAO extends DBContext {

    public List<Category> getAll() {
        List<Category> ls = new ArrayList<>();
        String sql = "SELECT [id]\n"
                + "      ,[name]\n"
                + "  FROM [dbo].[Category]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category c = new Category();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));

                ls.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return ls;
    }

    public Category getCategoryById(int id) {
        String sql = "select [id]\n"
                + "           ,[name]\n"
                + "   FROM [dbo].[Category] where id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Category c = new Category();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));

                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    //xoa
    public void delete(int id) {
        String sql = "Delete from [dbo].[Category]\n"
                + "     where id =? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

//    public void insert(Category c) {
//        String sql = "insert into Category values(?,?,?)";
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
    public void insert(Category c) {
        String sql = "INSERT INTO [dbo].[Category]\n"
                + "           ([name])\n"
                + "     VALUES\n"
                + "           (?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            
            st.setString(1, c.getName());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    // update 
    public void update(Category c) {
        String sql = "UPDATE [dbo].[Category]\n"
                + "   SET [name] = ?\n"
                + " WHERE id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, c.getName());

            st.setInt(3, c.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        CategoryDAO d = new CategoryDAO();
        List<Category> ls = d.getAll();
        d.insert(new Category(6, "Chay"));
//        d.delete(6);
    }

}
