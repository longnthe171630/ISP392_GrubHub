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
import jakarta.servlet.http.HttpSession;
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
@WebServlet(name = "EditProductFileServlet", urlPatterns = {"/editproductfile"})
@MultipartConfig
public class EditProductFileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null && action.equalsIgnoreCase("delete")) {
            // Delete action
            int productId = Integer.parseInt(request.getParameter("id"));
            HttpSession session = request.getSession();
            List<Product> lp = (List<Product>) session.getAttribute("uploadedProducts");

            // Find the product to delete
            Product p = null;
            for (Product product : lp) {
                if (product.getId() == productId) {
                    p = product;
                    break;
                }
            }

            // Remove the product from the list
            if (p != null) {
                lp.remove(p);
                // Optionally, you might want to perform any additional clean-up or processing here

                // Redirect back to the uploadproductfromfile servlet or any other appropriate page
                response.sendRedirect("uploadproductfromfile");
                return;
            }
        }

        // Edit action or initial display
        HttpSession session = request.getSession();
        List<Product> lp = (List<Product>) session.getAttribute("uploadedProducts");

        int idProduct = Integer.parseInt(request.getParameter("id"));
        Product p = null;
        for (Product product : lp) {
            if (product.getId() == idProduct) {
                p = product;
                break;
            }
        }

        CategoryDAO cd = new CategoryDAO();
        List<Category> lc = cd.getCategorys();

        // Set attributes for JSP rendering
        request.setAttribute("product", p);
        request.setAttribute("lc", lc);

        // Forward the request to editproductfile.jsp for further processing
        request.getRequestDispatcher("editproductfile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int productId = Integer.parseInt(request.getParameter("productId"));
            String action = request.getParameter("action");
            Product p = new Product();
            HttpSession session = request.getSession();
            List<Product> lp = (List<Product>) session.getAttribute("uploadedProducts");
            for (Product product : lp) {
                if (product.getId() == productId) {
                    p = product;
                }
            }

            switch (action) {
                case "save" -> {
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
                    p.setName(name);
                    p.setCategoryId(categoryId);
                    p.setPrice(price);
                    p.setQuantity(quantity);
                    p.setDescription(description);
                    p.setImage(filename);

                    break;
                }

                case "delete" -> // Delete product from database
                    lp.remove(p);

            }
            response.sendRedirect("uploadproductfromfile");

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
            request.getRequestDispatcher("editproductfile.jsp").forward(request, response);
        }
    }
}
