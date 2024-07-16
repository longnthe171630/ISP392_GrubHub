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
       String indexPage = request.getParameter("index");
       if (indexPage == null) {
           indexPage = "1";
       }
       int index = Integer.parseInt(indexPage);
       ProductDAO pd = new ProductDAO();
       int count = pd.getTotalProductOfRestaurant(resId);
       int endPage = count / 5;
       if (count % 5 != 0) {
           endPage++;
       }
       List<Product> lp = pd.paginProduct(index, resId);
       
       request.setAttribute("currentPage", index);
       request.setAttribute("endP", endPage);
       request.setAttribute("listProduct", lp);
       request.getRequestDispatcher("productofrestaurant.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       
    }

}
