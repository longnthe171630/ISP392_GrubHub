package dal;

import model.Category;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO extends MyDAO {

    public List<Category> getCategorys() {
        List<Category> t = new ArrayList<>();
        xSql = "select * from Category";
        int xCategoryId;
        String xCategoryName;

        Category x;
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                xCategoryId = rs.getInt("id");
                xCategoryName = rs.getString("name");
                x = new Category(xCategoryId, xCategoryName);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public List<String> getCategoryNames() {
        List<String> t = new ArrayList<>();
        xSql = "select name from Category";
        String xCategoryName;
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                xCategoryName = rs.getString("name");
                t.add(xCategoryName);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public void insert(Category x) {
        xSql = "insert into Category (name) values (?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, x.getName());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(String categoryId) {
        xSql = "delete from Category where id=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, categoryId);
            ps.executeUpdate();
            //con.commit();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(String xRollno, Category x) {
        xSql = "update Student set Category where id=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, x.getName());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getListCategory(int xxProductId) {
        List<String> lc = new ArrayList<>();
        xSql = "SELECT Category.name "
                + "FROM Product "
                + "INNER JOIN Category ON Product.category_id = Category.id "
                + "WHERE Product.id = ? ";

        String xCategoryName;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, xxProductId);
            rs = ps.executeQuery();
            while (rs.next()) {
                xCategoryName = rs.getString("name");
                lc.add(xCategoryName);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lc;
    }

    public static void main(String[] args) {
        CategoryDAO c = new CategoryDAO();
        List<Category> lc = c.getCategorys();
        if (lc == null) {
            System.out.println("List empty");
        } else {
            for (Category d : lc) {
                System.out.println(d);
            }
        }
    }

    public String getBookCategoryName(int xxBookId) {
        xSql = "select name from category where id= ?";

        String xCategoryName = null;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, xxBookId);
            rs = ps.executeQuery();
            while (rs.next()) {
                xCategoryName = rs.getString("CategoryName");
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xCategoryName;
    }

    public int getBookCategoryId(String xxCategoryName) {
        xSql = "select CategoryID  from category where CategoryName  = ?";

        int xCategoryId = 0;
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, xxCategoryName);
            rs = ps.executeQuery();
            while (rs.next()) {
                xCategoryId = rs.getInt("CategoryID");
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xCategoryId;
    }

}
