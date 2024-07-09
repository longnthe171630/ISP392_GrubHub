package controller;

import dao.CategoryDAO;
import dao.ProductDAO;
import dao.RestaurantDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.Category;
import model.Product;
import model.Restaurant;

@WebServlet(name = "DetailServlet", urlPatterns = {"/detail"})
public class DetailServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        // Get parameters from request
        String productId = request.getParameter("product_id");
        String restaurantId = request.getParameter("id");
        
        // Initialize DAOs
        ProductDAO productDAO = new ProductDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        RestaurantDAO restaurantDAO = new RestaurantDAO();
        
        // Retrieve data from DAOs
        Product product = productDAO.getProduct(productId);
        Restaurant restaurant = restaurantDAO.getRestaurant(restaurantId); // Assuming getRestaurant method exists
        List<Category> categories = categoryDAO.getCategorys(); // Assuming getCategories method exists
        
        // Set attributes in request scope
        request.setAttribute("restaurant", restaurant);
        request.setAttribute("detail", product);
        request.setAttribute("listCC", categories);
        
        // Forward request to Detail.jsp
        request.getRequestDispatcher("Detail.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet for displaying product details";
    }
}
