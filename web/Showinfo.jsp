<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.*" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Profile - Grubhub</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
        <style type="text/css">
            body{
                margin-top:50px;
                color: #9b9ca1;
            }
            .bg-secondary-soft {
                background-color: rgba(208, 212, 217, 0.1) !important;
            }
            .rounded {
                border-radius: 5px !important;
            }
            .py-5 {
                padding-top: 3rem !important;
                padding-bottom: 3rem !important;
            }
            .px-4 {
                padding-right: 1.5rem !important;
                padding-left: 1.5rem !important;
            }
            .file-upload .square {
                height: 250px;
                width: 250px;
                margin: auto;
                vertical-align: middle;
                border: 1px solid #e5dfe4;
                background-color: #fff;
                border-radius: 5px;
            }
            .text-secondary {
                --bs-text-opacity: 1;
                color: rgba(208, 212, 217, 0.5) !important;
            }
            .btn-success-soft {
                color: #28a745;
                background-color: rgba(40, 167, 69, 0.1);
            }
            .btn-danger-soft {
                color: #dc3545;
                background-color: rgba(220, 53, 69, 0.1);
            }
            .form-control {
                display: block;
                width: 100%;
                padding: 0.5rem 1rem;
                font-size: 0.9375rem;
                font-weight: 400;
                line-height: 1.6;
                color: #29292e;
                background-color: #fff;
                background-clip: padding-box;
                border: 1px solid #e5dfe4;
                -webkit-appearance: none;
                -moz-appearance: none;
                appearance: none;
                border-radius: 5px;
                -webkit-transition: border-color 0.15s ease-in-out, -webkit-box-shadow 0.15s ease-in-out;
                transition: border-color 0.15s ease-in-out, -webkit-box-shadow 0.15s ease-in-out;
                transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out;
                transition: border-color 0.15s ease-in-out, box-shadow 0.15s ease-in-out, -webkit-box-shadow 0.15s ease-in-out;
            }

        </style>
        <style type="text/css">
            .generated-text {
                font-family:Arial, sans-serif;
                font-size:20px;
                font-weight:bold;
                line-height:0.2in;
                text-align:center;
                color:#ffffff;

                border-color:#0a0ef7;
                border-style:solid;
                border-radius:15px;
                background-color:#2b44ff;
                padding:20px;
            }
            .default_input{
                background-color: white;
            }
        </style>
    </head>
    <body>
        <jsp:include page="Hearder.jsp"></jsp:include><br><br>
        <%
            // Lấy thông tin tài khoản từ session attribute
            Account account = (Account) request.getAttribute("acc");
            Customer customer = (Customer) request.getAttribute("customer");
            String status = (String) request.getAttribute("alert");
            Address address = (Address) request.getAttribute("address");
            if (customer == null) {
                response.sendRedirect("logincus");
                
            }
        %>
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="my-5">
                        <h3>Grubhub</h3>
                        <hr>
                    </div>
                    <%-- Hiển thị thông báo trạng thái nếu có --%>
                    <% if (status != null) { %>
                    <div class="alert alert-info" role="alert">
                        <%= status %>
                    </div>
                    <% } %>
                </div>
            </div>
            <form id="form-1" action="edit" method="get" class="file-upload" enctype="multipart/form-data">
                <div class="row mb-5 gx-5">
                    <div class="col-xxl-8 mb-5 mb-xxl-0">
                        <div class="bg-secondary-soft px-4 py-5 rounded">
                            <div class="row g-3">
                                <h4 class="mb-4 mt-0">My Profile</h4>
                                 <div class="col-md-6">
                                    <label class="form-label">Name</label>
                                    <input type="text" class="form-control" name="name" placeholder aria-label="name" value="<%= customer.getName() %>" readonly> 
                                </div>
                                <div class="col-md-6">
                                    <label class="form-label">Username</label>
                                    <input type="text" class="form-control" name="username" placeholder aria-label="Username" value="<%= account.getUsername()%>" readonly> 
                                </div>
                                <div class="col-md-6">
                                    <label class="form-label">Email</label>
                                    <input type="text" class="form-control" name="email" placeholder aria-label="Email" value="<%= account.getEmail() %>" readonly>
                                </div>
                                <div class="col-md-6">
                                    <label class="form-label">Phone number</label>
                                    <input type="text" class="form-control" name="phonenumber" placeholder aria-label="Phonenumber" value="<%= account.getPhonenumber() %>" readonly>
                                </div>
                                <div class="col-md-6">
                                    <label class="form-label">Date of birth</label>
                                    <input type="date" class="form-control" name="dob" placeholder aria-label="Date of birth" value="<%= customer.getDob() != null ? customer.getDob() : "" %>" readonly>
                                </div>
                                 <div class="col-md-6">
                                    <label class="form-label">Details</label>
                                    <input type="text" class="form-control" name="details" placeholder aria-label=" Address details" value="<%= address.getDetails() %>" readonly>
                                </div>
                                 <div class="col-md-6">
                                    <label class="form-label">state</label>
                                    <input type="text" class="form-control" name="state" placeholder aria-label="Address state" value="<%=address.getState()%>" readonly>
                                </div>
                                 <div class="col-md-6">
                                    <label class="form-label">street</label>
                                    <input type="text" class="form-control" name="street" placeholder aria-label="Address street" value="<%= address.getStreet()%>" readonly>
                                </div>
                                <div class="gend">
                                    <a>Gender</a><br>
                                    <input type="radio" name="gender" id="male" value="Male" <%= !customer.isGender() ? "checked" : "" %> disabled>
                                    <label for="male">Male</label><br>
                                    <input type="radio" name="gender" id="female" value="Female" <%= customer.isGender() ? "checked" : "" %> disabled>
                                    <label for="female">Female</label>
                                </div>
                            </div>
                            <button onclick="changeType(this)"type="button"  id="edit" class="generated-text"  >Edit</button>
                        </div>
                    </div>
                </div>
        </div>
    </form>
</div>
</div>
</div>
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
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
<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>
<!--                            <div class="col-xxl-4">
                                               <div class="bg-secondary-soft px-4 py-5 rounded">
                                                   <div class="row g-3">
                                                       <h4 class="mb-4 mt-0">Upload your profile photo</h4>
               
                                                       <div class="text-center">
               
                                                           <div class="square position-relative display-2 mb-3">
                                                               <i class="fas fa-fw fa-user position-absolute top-50 start-50 translate-middle text-secondary"></i>
                                                           </div>
               
                                                           <input type="file" id="customFile" name="file" hidden>
                                                           <label class="btn btn-success-soft btn-block" for="customFile">Upload</label>
                                                           <button type="button" class="btn btn-danger-soft">Remove</button>
               
                                                           <p class="text-muted mt-3 mb-0"><span class="me-1">Note:</span>Minimum size 300px x 300px</p>
                                                       </div>
                                                       <input >
                                                   </div>
                                               </div>
                                           </div>-->
