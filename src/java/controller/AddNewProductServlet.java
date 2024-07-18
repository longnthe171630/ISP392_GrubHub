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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import model.Category;
import model.Product;
import utils.Validate;

/**
 *
 * @author phaml
 */
@MultipartConfig
@WebServlet(name = "AddNewProductServlet", urlPatterns = {"/addnewproduct"})
public class AddNewProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoryDAO cd = new CategoryDAO();
        List<Category> lc = cd.getCategorys();
        request.setAttribute("lc", lc);
        request.getRequestDispatcher("addnewproduct.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDAO pd = new ProductDAO();
        int restaurantId = 1;

        String name = request.getParameter("name");
        String priceStr = request.getParameter("price");
        String quantityStr = request.getParameter("quantity");
        String description = request.getParameter("description");
        String categoryStr = request.getParameter("category");
        Date date = new Date();

        int xPrice = 0;
        int xQuantity = 0;
        int xCategory = 0;
        boolean hasError = false;
        String errorMessage = "";

        try {
            xPrice = Integer.parseInt(priceStr);
            xQuantity = Integer.parseInt(quantityStr);
            xCategory = Integer.parseInt(categoryStr);
        } catch (NumberFormatException e) {
            hasError = true;
            errorMessage = "Giá và số lượng phải là số nguyên.";
        }

        // File upload handling
        String filename = "";
        Part part = request.getPart("img");
        if (part != null && part.getSize() > 0) {
            String realPath = request.getServletContext().getRealPath("/images/Product");
            filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
            if (!Files.exists(Paths.get(realPath))) {
                Files.createDirectory(Paths.get(realPath));
            }
            part.write(realPath + "/" + filename);
        }

        // Check if there was an error
        if (hasError) {
            // Set error message and retain user inputs in request attributes
            request.setAttribute("error", errorMessage);
            request.setAttribute("nameValue", name);
            request.setAttribute("priceValue", priceStr);
            request.setAttribute("quantityValue", quantityStr);
            request.setAttribute("descriptionValue", description);
            request.setAttribute("categoryValue", categoryStr);

            // Forward back to editproduct.jsp with error message and retained inputs
            request.getRequestDispatcher("editproduct.jsp").forward(request, response);
            return;
        }

        // No error, create Product object and insert into database
        Product p = new Product(name, xPrice, xQuantity, description, filename, true, date, xCategory, restaurantId);
        pd.insert(p);
        response.sendRedirect("productofrestaurant");
    }

}
