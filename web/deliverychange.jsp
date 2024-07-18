<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    String lang = request.getParameter("lang");
    if (lang != null) {
        session.setAttribute("lang", lang);
    } else if (session.getAttribute("lang") == null) {
        session.setAttribute("lang", "en");
    }
%>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="lang.create" var="lang" />
