<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Boxicons -->
    <link href='https://unpkg.com/boxicons@2.0.9/css/boxicons.min.css' rel='stylesheet'>
    <!-- My CSS -->
    <link rel="stylesheet" href="css/admin.css">

    <title>AdminHub</title>
</head>
<body>

<!-- SIDEBAR -->
<section id="sidebar">
    <a href="admin?action=home" class="brand">
        <i class='bx bxs-smile'></i>
        <span class="text">AdminHub</span>
    </a>
    <ul class="side-menu top">
        <li>
            <a href="admin?action=home">
                <i class='bx bxs-dashboard'></i>
                <span class="text">Dashboard</span>
            </a>
        </li>
        <li>
            <a href="admin?action=cus">
                <i class='bx bxs-user'></i>
                <span class="text">Customer Account</span>
            </a>
        </li>
        <li class="active">
            <a href="admin?action=res">
                <i class='bx bx-restaurant'></i>
                <span class="text">Restaurant Account</span>
            </a>
        </li>
        <li>
            <a href="admin?action=deli">
                <i class='bx bxs-message-dots'></i>
                <span class="text">Delivery</span>
            </a>
        </li>
        <li>
            <a href="admin?action=feed">
                <i class='bx bxs-message-dots'></i>
                <span class="text">Feedback</span>
            </a>
        </li>
        <li>
            <a href="admin?action=ban">
                <i class='bx bxs-no-entry'></i>
                <span class="text">Ban</span>
            </a>
        </li>
    </ul>
    <ul class="side-menu">
        <li>
            <a href="Home.jsp" class="logout">
                <i class='bx bxs-log-out-circle'></i>
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
        <i class='bx bx-menu'></i>
        <a href="#" class="profile">
            <img src="img/people.png">
        </a>
    </nav>
    <!-- NAVBAR -->

    <!-- MAIN -->
    <main>
        <div class="head-title">
            <div class="left">
                <h1>Dashboard</h1>
                <ul class="breadcrumb">
                    <li>
                        <a href="#">Dashboard</a>
                    </li>
                    <li><i class='bx bx-chevron-right'></i></li>
                    <li>
                        <a class="active" href="#">Restaurant Account</a>
                    </li>
                </ul>
            </div>
        </div>

        <div class="table-data">
            <div class="order">
                <div class="head">
                    <h3>List Restaurant</h3>
                    <i class='bx bx-search'></i>
                    <i class='bx bx-filter'></i>
                    <select id="stateFilter" onchange="filterRestaurants()">
                        <option value="">All States</option>
                        <c:forEach items="${requestScope.listRes}" var="r">
                            <option value="${r.state}">${r.state}</option>
                        </c:forEach>
                    </select>
                </div>
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Phone Number</th>
                            <th>Details</th>
                            <th>State</th>
                            <th>Street</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${requestScope.listRes}" var="o">
                            <tr class="restaurant-row" data-state="${o.state}" onclick="openModal(${o.id})" style="cursor: pointer;">
                                <td>${o.id}</td>
                                <td>${o.name}</td>
                                <td>${o.phonenumber}</td>
                                <td>${o.details}</td>
                                <td>${o.state}</td>
                                <td>${o.street}</td>
                                
                            </tr>
                            <!-- Placeholder for the detailed content row -->
                            <tr class="details-row">
                                <td colspan="6" class="details-cell"></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <!-- Modal -->
                <div id="myModal" class="modal">
                    <div class="modal-content">
                        <span class="close" onclick="closeModal();">&times;</span>
                        <div id="modalContent">
                            <!-- N?i dung chi ti?t s? ???c t?i vào ?ây -->
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <!-- MAIN -->
</section>
<!-- CONTENT -->

<script src="js/admin.js"></script>
<script>
function filterRestaurants() {
    const filter = document.getElementById("stateFilter").value.toLowerCase();
    const rows = document.querySelectorAll(".restaurant-row");

    rows.forEach(row => {
        const state = row.getAttribute("data-state").toLowerCase();
        if (filter === "" || state === filter) {
            row.style.display = "";
            row.nextElementSibling.style.display = ""; // Show the corresponding details row
        } else {
            row.style.display = "none";
            row.nextElementSibling.style.display = "none"; // Hide the corresponding details row
        }
    });
}
</script>
</body>
</html>
