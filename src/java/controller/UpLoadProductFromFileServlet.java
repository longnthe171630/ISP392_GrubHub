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
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.*;
import utils.CSVParser;
import utils.XMLParser;

/**
 *
 * @author phaml
 */
@WebServlet(name = "UpLoadProductFromFileServlet", urlPatterns = {"/uploadproductfromfile"})
@MultipartConfig
public class UpLoadProductFromFileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoryDAO cd = new CategoryDAO();
        List<Category> lc = cd.getCategorys();
        request.setAttribute("lc", lc);
        request.getRequestDispatcher("displayproducts.jsp").forward(request, response);
    }
    private static final long serialVersionUID = 1L;

    @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = "";
        CategoryDAO cd = new CategoryDAO();
        List<Category> lc = cd.getCategorys();
        List<Product> products = null;

        try {
            Part filePart = request.getPart("file");
            if (filePart == null) {
                return;
            }

            fileName = filePart.getSubmittedFileName();
            InputStream fileContent = filePart.getInputStream();

            // Read and process the file here
            if (fileName.endsWith(".csv")) {
                // Process CSV file
                products = CSVParser.parse(fileContent);
            } else if (fileName.endsWith(".xml")) {
                // Process XML file
                products = XMLParser.parse(fileContent);
            } else {
                throw new IOException("Unsupported file format");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Generate unique IDs for products
        int nextId = 1; // Starting ID
        for (Product product : products) {
            product.setId(nextId++);
        }

        // Save products to session
        HttpSession session = request.getSession();
        session.setAttribute("uploadedProducts", products);

        // Set attributes and forward to displayproducts.jsp
        request.setAttribute("products", products);
        request.setAttribute("lc", lc);
        request.getRequestDispatcher("displayproducts.jsp").forward(request, response);
    }

}
