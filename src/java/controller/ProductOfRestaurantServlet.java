/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.*;
import model.Product;

/**
 *
 * @author phaml
 */
@WebServlet(name="ProductOfRestaurantServlet", urlPatterns={"/productofrestaurant"})
public class ProductOfRestaurantServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       int resId = 1;
       ProductDAO pd = new ProductDAO();
       List<Product> lp;
       List<Product> xLp = new ArrayList<>();
       lp = pd.getProductsRestaurant();
        for (Product product : lp) {
            if (product.getRestaurantId()== resId) {
                xLp.add(product);
            }
        }
        request.setAttribute("listProduct", xLp);
        request.getRequestDispatcher("productofrestaurant.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       
    }

}
