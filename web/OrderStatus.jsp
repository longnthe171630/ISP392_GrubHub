<%@ page import="model.Order" %>
<%@ page import="model.OrderDetails" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Order Status</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"/>
        <style>
            @import url('https://fonts.googleapis.com/css?family=Open+Sans&display=swap');body{
                background-color: #eeeeee;
                font-family: 'Open Sans',serif
            }
            .container{
                margin-top:50px;
                margin-bottom: 50px
            }
            .card{
                position: relative;
                display: -webkit-box;
                display: -ms-flexbox;
                display: flex;
                -webkit-box-orient: vertical;
                -webkit-box-direction: normal;
                -ms-flex-direction: column;
                flex-direction: column;
                min-width: 0;
                word-wrap: break-word;
                background-color: #fff;
                background-clip: border-box;
                border: 1px solid rgba(0, 0, 0, 0.1);
                border-radius: 0.10rem
            }
            .card-header:first-child{
                border-radius: calc(0.37rem - 1px) calc(0.37rem - 1px) 0 0
            }
            .card-header{
                padding: 0.75rem 1.25rem;
                margin-bottom: 0;
                background-color: #fff;
                border-bottom: 1px solid rgba(0, 0, 0, 0.1)
            }
            .track{
                position: relative;
                background-color: #ddd;
                height: 7px;
                display: -webkit-box;
                display: -ms-flexbox;
                display: flex;
                margin-bottom: 60px;
                margin-top: 50px
            }
            .track .step{
                -webkit-box-flex: 1;
                -ms-flex-positive: 1;
                flex-grow: 1;
                width: 25%;
                margin-top: -18px;
                text-align: center;
                position: relative
            }
            .track .step.active:before{
                background: #FF5722
            }
            .track .step::before{
                height: 7px;
                position: absolute;
                content: "";
                width: 100%;
                left: 0;
                top: 18px
            }
            .track .step.active .icon{
                background: #ee5435;
                color: #fff
            }
            .track .icon{
                display: inline-block;
                width: 40px;
                height: 40px;
                line-height: 40px;
                position: relative;
                border-radius: 100%;
                background: #ddd
            }
            .track .step.active .text{
                font-weight: 400;
                color: #000
            }
            .track .text{
                display: block;
                margin-top: 7px
            }
            .itemside{
                position: relative;
                display: -webkit-box;
                display: -ms-flexbox;
                display: flex;
                width: 100%
            }
            .itemside .aside{
                position: relative;
                -ms-flex-negative: 0;
                flex-shrink: 0
            }
            .img-sm{
                width: 80px;
                height: 80px;
                padding: 7px
            }
            ul.row, ul.row-sm{
                list-style: none;
                padding: 0
            }
            .itemside .info{
                padding-left: 15px;
                padding-right: 7px
            }
            .itemside .title{
                display: block;
                margin-bottom: 5px;
                color: #212529
            }
            p{
                margin-top: 0;
                margin-bottom: 1rem
            }
            .btn-warning{
                color: #ffffff;
                background-color: #ee5435;
                border-color: #ee5435;
                border-radius: 1px
            }
            .btn-warning:hover{
                color: #ffffff;
                background-color: #ff2b00;
                border-color: #ff2b00;
                border-radius: 1px
            }
        </style>
    </head>
    <body>
        <jsp:include page="Hearder.jsp"></jsp:include>
            <br><!-- comment -->
            <br><!-- comment -->
            <br><!-- comment -->
            <br><!-- comment -->
            <br><!-- comment -->
            <br><!-- comment -->

            <div class="container">
                <article class="card">
                    <div class="card-body">

                        <h6>Order ID: <c:out value="${order.id}" /></h6>
                    <article class="card">
                        <div class="card-body row">
                            <div class="col">
                                <strong>Order Date:</strong> <br>
                                <c:out value="${order.order_date}" />
                            </div>
                            <div class="col">
                                <strong>Status:</strong> <br>
                                <c:out value="${order.status}" />
                            </div>
                            <div class="col">
                                <strong>Total Amount:</strong> <br>
                                <c:out value="${order.total_amount}" />đ
                            </div>
                            <div class="col">
                                <strong>Restaurant: </strong> <br>
                                <c:out value="${order.restaurant_id}" />
                            </div>
                        </div>
                    </article>
                    <div class="track">
                        <c:choose>
                            <c:when test="${order.status == 'Success'}">
                                <div class="step active"> 
                                    <span class="icon"> <i class="fa fa-check"></i> </span> 
                                    <span class="text">Order confirmed</span> 
                                </div>
                                <div class="step active"> 
                                    <span class="icon"> <i class="fa fa-user"></i> </span> 
                                    <span class="text"> Waiting restaurant </span> 
                                </div>
                                <div class="step active"> 
                                    <span class="icon"> <i class="fa fa-user"></i> </span> 
                                    <span class="text"> Waiting by shipper </span> 
                                </div>
                                <div class="step active"> 
                                    <span class="icon"> <i class="fa fa-truck"></i> </span> 
                                    <span class="text"> On the way </span> 
                                </div>
                                <div class="step active"> 
                                    <span class="icon"> <i class="fa fa-box"></i> </span> 
                                    <span class="text"> Ready for pickup </span> 
                                </div>
                                <div class="step active"> 
                                    <span class="icon"> <i class="fa fa-check"></i> </span> 
                                    <span class="text"> Success </span> 
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="step ${order.status.compareTo('Waiting restaurant') <= 0? 'active' : ''}"> 
                                    <span class="icon"> <i class="fa fa-check"></i> </span> 
                                    <span class="text">Order confirmed</span> 
                                </div>
                                <div class="step ${order.status.compareTo('Waiting restaurant') <= 0? 'active' : ''}"> 
                                    <span class="icon"> <i class="fa fa-user"></i> </span> 
                                    <span class="text"> Waiting restaurant </span> 
                                </div>
                                <div class="step ${order.status.compareTo('Waiting delivery') <= 0? 'active' : ''}"> 
                                    <span class="icon"> <i class="fa fa-user"></i> </span> 
                                    <span class="text"> Waiting by shipper </span> 
                                </div>
                                <div class="step ${order.status.compareTo('Picking up') <= 0 ? 'active' : ''}"> 
                                    <span class="icon"> <i class="fa fa-truck"></i> </span> 
                                    <span class="text"> On the way </span> 
                                </div>
                                <div class="step ${order.status.compareTo('Delivering') <= 0 ? 'active' : ''}"> 
                                    <span class="icon"> <i class="fa fa-box"></i> </span> 
                                    <span class="text"> Ready for pickup </span> 
                                </div>
                                <div class="step ${order.status.compareTo('Success') == 0 ? 'active' : ''}"> 
                                    <span class="icon"> <i class="fa fa-check"></i> </span> 
                                    <span class="text"> Success </span> 
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </div>

                    <c:if test="${order.status == 'Cancelled'}">
                        <div class="step active"> 
                            <span class="icon"> <i class="fa fa-times"></i> </span> 
                            <span class="text">Order Cancelled</span> 
                        </div>
                    </c:if>
                </div>
                <hr>
                <ul class="row">
                    <c:forEach var="orderDetail" items="${orderDetailsList}">
                        <li class="col-md-4">
                            <figure class="itemside mb-3">                    
                                <figcaption class="info align-self-center">
                                    <div class="aside"><img src=images/Product/"<c:out value="${orderDetail.product.image}" />" class="img-sm border"></div>
                                    <p class="title">Name:<c:out value="${orderDetail.product.name}" /></p>
                                    <span class="text-muted">Giá :<c:out value="${orderDetail.price}" />đ</span>
                                    <p><span class="text-muted">Số lượng: <c:out value="${orderDetail.quantity}" /></span>
                                </figcaption>
                            </figure>
                        </li>
                    </c:forEach>
                </ul>
                <hr>
                <div style="float: right;">
                    <form action="cancelorder" method="GET">
                        <input type="hidden" name="orderId" value="${order.id}" />
                        <button type="submit" class="btn btn-danger">Cancel Order</button>
                    </form>

                </div>
                <a href="home" class="btn btn-warning" data-abc="true"> <i class="fa fa-chevron-left"></i> Back to orders</a>
        </div>
    </article>
</div>
<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>
