<%-- 
    Document   : restaurant_dashboard
    Created on : Jun 10, 2024, 8:44:09 AM
    Author     : phaml
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.*" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel = "icon" 
              href="images/icon/logo.png" 
              type="image/x-icon">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Boxicons -->
        <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
        <!-- My CSS -->
        <link rel="stylesheet" href="css\restaurant_dashboard.css">
        <meta charset="utf-8" />
        <link rel="apple-touch-icon" sizes="76x76" href="../assets/img/apple-icon.png">
        <link rel="icon" type="image/png" href="../assets/img/favicon.ico">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no' name='viewport' />
        <!--     Fonts and icons     -->
        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700,200" rel="stylesheet" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" />
        <!-- CSS Files -->
        <link href="assets/css/bootstrap.min.css" rel="stylesheet" />
        <link href="assets/css/light-bootstrap-dashboard.css?v=2.0.0 " rel="stylesheet" />
        <title>Grubhub - Restaurant</title>
    </head>
    <body>
        <%
            Account account = (Account) request.getAttribute("acc");
            Restaurant restaurant = (Restaurant) request.getAttribute("restaurant");    
            String alert = (String) request.getAttribute("alert");
            Address address = (Address) request.getAttribute("address");
        %>

        <!-- SIDEBAR -->
        <section id="sidebar">
            <a href="#" class="brand">
                <i class='bx bxs-smile'></i>
                <span class="text">Grubhub</span>
            </a>
            <ul class="side-menu top">
                <li class="active">
                    <a href="#">
                        <i class='bx bxs-dashboard' ></i>
                        <span class="text">Dashboard</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class='bx bxs-shopping-bag-alt' ></i>
                        <span class="text">My Store</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class='bx bxs-doughnut-chart' ></i>
                        <span class="text">Analytics</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class='bx bxs-message-dots' ></i>
                        <span class="text">Message</span>
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class='bx bxs-group' ></i>
                        <span class="text">Team</span>
                    </a>
                </li>
                <li>
                    <a href="#" id="edit">
                        <i class='bx bxs-group' ></i>
                        <span class="text">Edit Restaurant</span>
                    </a>
                </li>
            </ul>
            <ul class="side-menu">			
                <li>
                    <a href="logout" class="logout">
                        <i class='bx bxs-log-out-circle' ></i>
                        <span class="text">Logout</span>
                    </a>
                </li>
            </ul>
        </section>
        <!-- SIDEBAR -->

        <!-- CONTENT -->
        <section id="content">
            <!-- NAVBAR -->
            <nav>
                <i class='bx bx-menu' ></i>
                <a href="#" class="nav-link">Categories</a>
                <form action="#">
                    <div class="form-input">
                        <input type="search" placeholder="Search...">
                        <button type="submit" class="search-btn"><i class='bx bx-search' ></i></button>
                    </div>
                </form>
                <input type="checkbox" id="switch-mode" hidden>
                <label for="switch-mode" class="switch-mode"></label>
                <a href="#" class="notification">
                    <i class='bx bxs-bell' ></i>
                    <span class="num">8</span>
                </a>
                <a href="#" class="profile">
                    <img src="img/people.png">
                </a>
            </nav>
            <!-- NAVBAR -->

            <!-- MAIN -->
            <main>
                <div class="head-title">
                    <div class="left">
                        <h1>Restaurant</h1>
                    </div>
                </div>

                <div class="content">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-8">
                                <div class="card">
                                    <div class="card-header">
                                        <h4 class="card-title">Edit Restaurant Information</h4>
                                    </div>
                                    <div class="card-header">
                                        <span style="color: red;" id="error-msg">
                                            <!-- This will be populated with error messages if any -->
                                            <% if (alert != null) { %>
                                            <div style="color: red;"><%= alert %></div>
                                            <% } %>
                                        </span>
                                    </div>
                                    <div class="card-body">
                                        <form id="form-1" action="editres" method="get" enctype="multipart/form-data">
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label>Name</label>
                                                        <% if (restaurant != null) { %>
                                                        <input name="name" type="text" class="form-control" placeholder="Name" value="<%= restaurant.getName() %>" readonly>
                                                        <% } else { %>
                                                        <input name="name" type="text" class="form-control" placeholder="Name" value="No Restaurant Data" readonly>
                                                        <% } %>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label>Username</label>
                                                        <% if (account != null) { %>
                                                        <input name="username" type="text" class="form-control" placeholder="Username" value="<%= account.getUsername() %>" readonly>
                                                        <% } else { %>
                                                        <input name="username" type="text" class="form-control" placeholder="Username" value="No Account Data" readonly>
                                                        <% } %>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label>Email</label>
                                                        <% if (account != null) { %>
                                                        <input name="email" type="text" class="form-control" placeholder="abc@xyz.com" value="<%= account.getEmail() %>" readonly>
                                                        <% } else { %>
                                                        <input name="email" type="text" class="form-control" placeholder="abc@xyz.com" value="No Email Data" readonly>
                                                        <% } %>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-12">
                                                    <div class="form-group">
                                                        <label>Phone number</label>
                                                        <% if (account != null) { %>
                                                        <input name="phonenumber" type="text" class="form-control" placeholder="0987654321" value="<%= account.getPhonenumber() %>" readonly>
                                                        <% } else { %>
                                                        <input name="phonenumber" type="text" class="form-control" placeholder="0987654321" value="No Phone Number Data" readonly>
                                                        <% } %>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-4 pr-1">
                                                    <div class="form-group">
                                                        <label>Address details</label>
                                                        <% if (address != null) { %>
                                                        <input name="details" type="text" class="form-control" placeholder="details" value="<%= address.getDetails() %>" readonly>
                                                        <% } else { %>
                                                        <input name="details" type="text" class="form-control" placeholder="details" value="No Address Data" readonly>
                                                        <% } %>
                                                    </div>
                                                </div>
                                                <div class="col-md-4 px-1">
                                                    <div class="form-group">
                                                        <label>State</label>
                                                        <% if (address != null) { %>
                                                        <input name="state" type="text" class="form-control" placeholder="state" value="<%= address.getState() %>" readonly>
                                                        <% } else { %>
                                                        <input name="state" type="text" class="form-control" placeholder="state" value="No State Data" readonly>
                                                        <% } %>
                                                    </div>
                                                </div>
                                                <div class="col-md-4 pl-1">
                                                    <div class="form-group">
                                                        <label>Street</label>
                                                        <% if (address != null) { %>
                                                        <input name="street" type="text" class="form-control" placeholder="Street" value="<%= address.getStreet() %>" readonly>
                                                        <% } else { %>
                                                        <input name="street" type="text" class="form-control" placeholder="Street" value="No Street Data" readonly>
                                                        <% } %>
                                                    </div>
                                                </div>
                                            </div>

                                            <button onclick="changeType(this)" type="button" id="edit" class="btn btn-info btn-fill pull-right">Edit</button>
                                            <div class="clearfix"></div>
                                        </form>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </main>
            <!-- MAIN -->
        </section>
        <!-- CONTENT -->

        <script src="js\restaurant_dashboard.js"></script>
        <script type="text/javascript">
                                                function changeType(button) {
                                                    var inputElements = document.querySelectorAll(".form-control");
                                                    var genderRadioButtons = document.querySelectorAll('input[type="radio"][name="gender"]');

                                                    if (button.id === "edit") {
                                                        button.id = "save";
                                                        button.textContent = "Save";
                                                        inputElements.forEach(x => {
                                                            if (x.name !== "username") {
                                                                x.readOnly = false;
                                                                x.classList.add("default_input");
                                                            }
                                                        });
                                                        genderRadioButtons.forEach(rb => {
                                                            rb.disabled = false; // Remove the disabled attribute
                                                        });
                                                    } else {
                                                        document.getElementById("form-1").submit();
                                                        button.id = "edit";
                                                        button.textContent = "Edit";
                                                        inputElements.forEach(x => {
                                                            if (x.name !== "username") {
                                                                x.readOnly = true;
                                                                x.classList.remove("default_input");
                                                            }
                                                        });
                                                        genderRadioButtons.forEach(rb => {
                                                            rb.disabled = true; // Add the disabled attribute back
                                                        });
                                                        document.getElementById("form-1").submit();
                                                    }
                                                }
        </script>
    </body>
</html>
