<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table class="vertical-table">
    <tbody>
    <c:forEach items="${requestScope.listDetail}" var="l">
            <tr>
                <th>Name:</th>
                <td>${l.username}</td>
            </tr>
            <tr>
                <th>Email:</th>
                <td>${l.email}</td>
            </tr>
            <tr>
                <th>Phonenumber:</th>
                <td>${l.phonenumber}</td>
            </tr>
            <tr>
                <th>Role:</th>
                <td>${l.role}</td>
            </tr>
            <tr>
                <th>Status:</th>
                <td>${l.active}</td>
            </tr>
        </c:forEach>
    </tbody>
    <style>
    .vertical-table td {
        padding-left: 10px; /* Cài ??t kho?ng cách phía bên ph?i c?a th? <th> */
    }
</style>
</table>
