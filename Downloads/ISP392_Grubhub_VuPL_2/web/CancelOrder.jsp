<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cancel Order</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"/>
    <style>
        /* Add your custom styles here */
    </style>
</head>
<body>
    <jsp:include page="Hearder.jsp"></jsp:include>
    
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-6 mt-5">
                <div class="card">
                    <h5 class="card-header">Cancel Order</h5>
                    <div class="card-body">
                        <%-- Check if message attribute exists and display it --%>
                        <c:if test="${not empty message}">
                            <div class="alert alert-success" role="alert">
                                ${message}
                            </div>
                        </c:if>
                        
                        <form action="cancelorder" method="post">
                            <div class="form-group">
                                <label for="reason">Reason for Cancellation:</label>
                                <textarea class="form-control" id="reason" name="reason" rows="3" required></textarea>
                            </div>
                            <input type="hidden" name="orderId" value="${orderId}"> <!-- Replace ${orderId} with actual orderId -->
                            <button type="submit" class="btn btn-danger">Cancel Order</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>
