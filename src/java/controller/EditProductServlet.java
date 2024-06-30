/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CategoryDAO;
import dao.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import model.Category;
import model.Product;

/**
 *
 * @author phaml
 */
@MultipartConfig
@WebServlet(name = "EditProductServlet", urlPatterns = {"/editproduct"})
public class EditProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idProduct = Integer.parseInt(request.getParameter("id"));
        ProductDAO pd = new ProductDAO();
        CategoryDAO cd = new CategoryDAO();
        List<Category> lc = cd.getCategorys();
        Product p = pd.getProduct(idProduct);
        request.setAttribute("product", p);
        request.setAttribute("lc", lc);
        request.getRequestDispatcher("editproduct.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Check if any field is empty
        if (isAnyFieldEmpty(request)) {
            response.sendRedirect("editproduct.jsp?error=empty_fields");
            return;
        }

        try {
            int productId = Integer.parseInt(request.getParameter("productId"));
            String action = request.getParameter("action");
            ProductDAO pd = new ProductDAO();
            Product p = pd.getProduct(productId);

            switch (action) {
                case "save":
                    String name = request.getParameter("name");
                    int categoryId = Integer.parseInt(request.getParameter("category"));
                    int price = Integer.parseInt(request.getParameter("price"));
                    int quantity = Integer.parseInt(request.getParameter("quantity"));
                    String description = request.getParameter("description");
                    Part part = request.getPart("img");

                    // If no new image is uploaded, retain the current image
                    String filename = p.getImage(); // Default to current image
                    if (part != null && part.getSize() > 0) {
                        String realPath = request.getServletContext().getRealPath("/images/Product");
                        filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
                        if (!Files.exists(Paths.get(realPath))) {
                            Files.createDirectory(Paths.get(realPath));
                        }
                        part.write(realPath + "/" + filename);
                    }

                    // Update product object
                    p.setName(name);
                    p.setCategoryId(categoryId);
                    p.setPrice(price);
                    p.setQuantity(quantity);
                    p.setDescription(description);
                    p.setImage(filename);

                    // Update product in database
                    pd.updateProduct(p);
                    break;

                case "delete":
                    // Delete product from database
                    pd.delete(productId);
                    break;

                default:
                    // Handle unknown action
                    break;
            }

            // Redirect to dashboard after processing
            response.sendRedirect("productofrestaurant");

        } catch (NumberFormatException e) {
            // Handle parsing errors (e.g., invalid numeric input)
            // Redirect back to edit form with error message and retain user inputs
            int idProduct = Integer.parseInt(request.getParameter("productId"));
            ProductDAO pd = new ProductDAO();
            CategoryDAO cd = new CategoryDAO();
            List<Category> lc = cd.getCategorys();
            Product p = pd.getProduct(idProduct);
            request.setAttribute("product", p);
            request.setAttribute("lc", lc);
            request.setAttribute("msg", "Price and quantity must be number");
            request.getRequestDispatcher("editproduct.jsp").forward(request, response);
        }
    }

// Phương thức để kiểm tra xem có trường nào trống không
    private boolean isAnyFieldEmpty(HttpServletRequest request) {
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        String price = request.getParameter("price");
        String quantity = request.getParameter("quantity");
        String description = request.getParameter("description");

        return name.isEmpty() || category.isEmpty() || price.isEmpty() || quantity.isEmpty() || description.isEmpty();
    }

// Phương thức để kiểm tra xem một chuỗi có phải là số không
    private boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
