package controller;

import dao.ProductDAO;
import model.Cart;
import model.CartItem;
import model.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

public class BuyServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       ProductDAO dao = new ProductDAO();
        List<Product> list = dao.getProducts();
        Cookie[] arr = request.getCookies();
        String txt = "";
        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("cart")) {
                    txt += o.getValue();
                    o.setMaxAge(0);
                    response.addCookie(o);
                }
            }
        }
        String num = request.getParameter("num");
        String id = request.getParameter("id");
        if (txt.isEmpty()) {
            txt =id+":"+num;
        } else {
            txt =txt+"/"+id+":"+num;
        }
        Cookie c = new Cookie("cart", txt);
        c.setMaxAge(2 * 24 * 60 * 60);
        response.addCookie(c);
        request.getRequestDispatcher("home").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
