/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.ProductDAO;
import dao.RestaurantDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Product;
import model.Restaurant;

/**
 *
 * @author manh0
 */
@WebServlet(name="RestaurantServlet", urlPatterns={"/restaurant"})
public class RestaurantServlet extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String resId = request.getParameter("restaurant_id");
        ProductDAO dao = new ProductDAO();
        RestaurantDAO dao2 = new RestaurantDAO();
        List<Restaurant> restaurantList = dao2.getRestaurants();
        List<Product> list = dao.getProductByRID(resId);

        // Setting attributes for JSP
        request.setAttribute("listP", list);
        request.setAttribute("listR", restaurantList);
        request.setAttribute("tag", resId);
        
        // Forwarding the request to Home.jsp
        request.getRequestDispatcher("Restaurant.jsp").forward(request, response);
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
}

