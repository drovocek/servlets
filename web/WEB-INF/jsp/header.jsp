<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div>
    <div id="logout">
        <form action="${pageContext.request.contextPath}/logout" method="post">
            <c:if test="${not empty sessionScope.user}">
                <button type="submit">Logout</button>
            </c:if>
        </form>
    </div>
    <div id="locale">
        <form action="${pageContext.request.contextPath}/locale" method="post">
            <button type="submit" name="lang" value="en_EN">EN</button>
            <button type="submit" name="lang" value="ru_RU">RU</button>
        </form>
    </div>
    <fmt:setLocale
            value="${sessionScope.lang!=null?
            sessionScope.lang: (param.lang!=null)? param.lang: 'ru_RU'}"/>
    <fmt:setBundle basename="translation"/>
</div>