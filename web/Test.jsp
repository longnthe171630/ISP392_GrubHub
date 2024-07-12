<%-- 
    Document   : viewRating
    Created on : Jul 5, 2024, 1:06:16 AM
    Author     : Admin
--%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Rating</title>
    </head>
    <body>
        <h1>Rating Details</h1>
        <%
            String rate = request.getParameter("rate");
            String description = request.getParameter("description");

            if (rate != null && description != null) {
                out.println("<p>Rating: " + rate + "</p>");
                out.println("<p>Description: " + description + "</p>");
            } else {
                out.println("<p>No rating or description provided.</p>");
            }
        %>
    </body>
</html>
