<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col-sm-3">
    <div class="card bg-light mb-3 category_block">
        <div class="card-header bg-primary text-white text-uppercase">
        </div>
        <div class="category_items">
            <c:forEach items="${requestScope.listCC}" var="category">
                <a href="category?id=${category.id}" class="category_item text-white ${tag == category.id ? 'active' : ''}">
                    ${category.name}
                </a>
            </c:forEach>
        </div>
    </div>
</div>