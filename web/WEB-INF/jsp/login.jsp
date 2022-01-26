<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<%@include file="header.jsp"%>

<form action="${pageContext.request.contextPath}/login" method="post">
    <label for="emailId" title="email"> <fmt:message key="page.login.email"/>
        <input type="text" name="email" id="emailId" value="${param.email}" required>
    </label>
    <br/>
    <label for="passwordId" title="password"> <fmt:message key="page.login.password"/>
        <input type="password" name="password" id="passwordId" required>
    </label>
    <br/>
    <button type="submit"> <fmt:message key="page.login.login"/></button>
    <a href="${pageContext.request.contextPath}/registration">
        <button type="button">
            <fmt:message key="page.login.registration"/>
        </button>
    </a>
    <br/>
    <div>
        <c:if test="${param.error != null}">
            <span style="color:red"><fmt:message key="page.login.error"/></span>
        </c:if>
    </div>
</form>
</body>
</html>
