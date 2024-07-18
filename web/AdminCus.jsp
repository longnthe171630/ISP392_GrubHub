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
                <li >
                    <a href="admin?action=home">
                        <i class='bx bxs-dashboard' ></i>
                        <span class="text">Dashboard</span>
                    </a>
                </li>
                <li class="active">
                    <a href="admin?action=cus">
                        <i class='bx bxs-user'></i>
                        <span class="text">Customer Account </span>
                    </a>
                </li>
                <li>
                    <a href="admin?action=res">
                        <i class='bx bx-restaurant'></i>
                        <span class="text">Restaurant Account</span>
                    </a>
                </li>
                <li>
                    <a href="admin?action=deli">
                        <i class='bx bxs-message-dots' ></i>
                        <span class="text">Delivery</span>
                    </a>
                </li>
                <li>
                    <a href="admin?action=feed">
                        <i class='bx bxs-message-dots' ></i>
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
                <!--			<a href="#" class="nav-link">Categories</a>-->
                <form action="DashboardServlet">
                    
                </form>
               
                
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
                            <li><i class='bx bx-chevron-right' ></i></li>
                            <li>
                                <a class="active" href="#">Customer Account</a>
                            </li>
                        </ul>
                    </div>

                </div>               
                <div class="table-data">
                    <div class="order">
                        <div class="head">
                            <h3>List User</h3>
                            <div class="search-container">
                                <input type="search" id="searchInput" placeholder="Search for name,email, phone">
                                <button type="button" class="search-btn" onclick="searchCus()"><i class='bx bx-search'></i></button>
                            </div>
                            <i class='bx bx-filter' onclick="sortCus()"></i>
                        </div>

                        <table>
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Username</th>
                                    <th>Email</th>
                                    <th>Phonenumber</th>
                                </tr>
                            </thead>
                            <tbody>

                                <c:forEach items="${requestScope.listAccount}" var="o" varStatus="loop">
                                    <tr id="row${loop.index + 1}" onclick="openModalCus(${o.id})" style="cursor: pointer;">
                                        <td>${o.id}</td>
                                        <td>${o.username}</td>
                                        <td>${o.email}</td>
                                        <td>${o.phonenumber}</td>   
                                        <td>
                                            <a href="admin?action=ban&accountId=${o.id}" class="ban-btn">Ban</a>
                                        </td>
                                    </tr>
                                </c:forEach>


                            </tbody>
                        </table>
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
                                    let sorted = false; // Track current sort state

                                    function sortCus() {
                                        let table = document.querySelector("table");
                                        let rows = Array.from(table.querySelectorAll("tbody tr"));

                                        rows.sort((row1, row2) => {
                                            let username1 = row1.querySelector("td:nth-child(2)").textContent.trim();
                                            let username2 = row2.querySelector("td:nth-child(2)").textContent.trim();

                                            // Toggle sorting order based on current state
                                            if (sorted) {
                                                return username1.localeCompare(username2); // Ascending order
                                            } else {
                                                return username2.localeCompare(username1); // Descending order
                                            }
                                        });

                                        // Remove existing rows from table
                                        rows.forEach(row => table.querySelector("tbody").removeChild(row));

                                        // Append sorted rows back to the table
                                        rows.forEach(row => table.querySelector("tbody").appendChild(row));

                                        sorted = !sorted; // Toggle sort state
                                    }

                                    function searchCus() {
                                        let input = document.getElementById("searchInput").value.toUpperCase();
                                        let table = document.querySelector("table");
                                        let rows = table.getElementsByTagName("tr");

                                        for (let i = 0; i < rows.length; i++) {
                                            let usernameCol = rows[i].getElementsByTagName("td")[1];
                                            let emailCol = rows[i].getElementsByTagName("td")[2];
                                            let phoneCol = rows[i].getElementsByTagName("td")[3];

                                            if (usernameCol || emailCol || phoneCol) {
                                                let usernameText = usernameCol.textContent || usernameCol.innerText;
                                                let emailText = emailCol.textContent || emailCol.innerText;
                                                let phoneText = phoneCol.textContent || phoneCol.innerText;

                                                if (usernameText.toUpperCase().indexOf(input) > -1 ||
                                                        emailText.toUpperCase().indexOf(input) > -1 ||
                                                        phoneText.toUpperCase().indexOf(input) > -1) {
                                                    rows[i].style.display = "";
                                                } else {
                                                    rows[i].style.display = "none";
                                                }
                                            }
                                        }
                                    }


        </script>
    </body>
</html>