<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Explore | Order Food</title>
<link rel="stylesheet" type="text/css" href="css/foodlist.css">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
</head>
<body>
    <button onclick="topFunction()" id="myBtn" title="Go to top">
        <span class="glyphicon glyphicon-chevron-up"></span>
    </button>
    <script type="text/javascript">
        window.onscroll = function() {
            scrollFunction()
        };

        function scrollFunction() {
            if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
                document.getElementById("myBtn").style.display = "block";
            } else {
                document.getElementById("myBtn").style.display = "none";
            }
        }

        function topFunction() {
            document.body.scrollTop = 0;
            document.documentElement.scrollTop = 0;
        }
    </script>
    <nav class="navbar navbar-inverse navbar-fixed-top navigation-clean-search" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#myNavbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.jsp">Le Cafe'</a>
            </div>
            <div class="collapse navbar-collapse" id="myNavbar">
                <ul class="nav navbar-nav">
                    <li><a href="index.jsp">Home</a></li>
                    <li><a href="aboutus.jsp">About</a></li>
                    <li><a href="contactus.jsp">Contact Us</a></li>
                </ul>
                <% if(session.getAttribute("login_user1") != null) { %>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#"><span class="glyphicon glyphicon-user"></span> Welcome
                            <%= session.getAttribute("login_user1") %></a></li>
                    <li><a href="myrestaurant.jsp">MANAGER CONTROL PANEL</a></li>
                    <li><a href="logout_m.jsp"><span class="glyphicon glyphicon-log-out"></span>
                            Log Out </a></li>
                </ul>
                <% } else if(session.getAttribute("login_user2") != null) { %>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#"><span class="glyphicon glyphicon-user"></span> Welcome
                            <%= session.getAttribute("login_user2") %></a></li>
                    <li class="active"><a href="foodlist.jsp"><span
                                class="glyphicon glyphicon-cutlery"></span> Food Zone </a></li>
                    <li><a href="cart.jsp"><span class="glyphicon glyphicon-shopping-cart"></span> Cart
                            (<%
                            if(session.getAttribute("cart") != null) {
                                int count = ((HashMap<String, Integer>)session.getAttribute("cart")).size();
                                out.print(count);
                            } else {
                                out.print("0");
                            }
                            %>)
                        </a></li>
                    <li><a href="logout_u.jsp"><span class="glyphicon glyphicon-log-out"></span> Log
                            Out </a></li>
                </ul>
                <% } else { %>
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown"><a href="#" class="dropdown-toggle active" data-toggle="dropdown"
                            role="button" aria-haspopup="true" aria-expanded="false"><span
                                class="glyphicon glyphicon-user"></span> Sign Up <span class="caret"></span> </a>
                        <ul class="dropdown-menu">
                            <li><a href="customersignup.jsp"> User Sign-up</a></li>
                            <li><a href="managersignup.jsp"> Manager Sign-up</a></li>
                            <li><a href="#"> Admin Sign-up</a></li>
                        </ul>
                    </li>
                    <li class="dropdown"><a href="#" class="dropdown-toggle active" data-toggle="dropdown"
                            role="button" aria-haspopup="true" aria-expanded="false"><span
                                class="glyphicon glyphicon-log-in"></span> Login <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li><a href="customerlogin.jsp"> User Login</a></li>
                            <li><a href="managerlogin.jsp"> Manager Login</a></li>
                            <li><a href="#"> Admin Login</a></li>
                        </ul>
                    </li>
                </ul>
                <% } %>
            </div>
        </div>
    </nav>
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner">
            <div class="item active">
                <img src="images/slide002.jpg" style="width:100%;">
                <div class="carousel-caption">
                </div>
            </div>
            <div class="item">
                <img src="images/slide001.jpg" style="width:100%;">
                <div class="carousel-caption">
                </div>
            </div>
            <div class="item">
                <img src="images/slide003.jpg" style="width:100%;">
                <div class="carousel-caption">
                </div>
            </div>
        </div>
        <a class="left carousel-control" href="#myCarousel" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#myCarousel" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right"></span>
            <span class="sr-only">Next</span>
        </a>
   
