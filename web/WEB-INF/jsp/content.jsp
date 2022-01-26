<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 16.01.2022
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
</head>
<body>
<%@ include file="header.jsp" %>
<div>
    <span>Content. Русский</span>
    <p>size: ${requestScope.flights.size()}</p>
    <p>id: ${requestScope.flights.get(0).id}</p>
    <%-- null safe variant--%>
    <p>id null safe: ${requestScope.flights[1].id}</p>
    <p>description: ${sessionScope.flightsMap[1]}</p>
    <p>JSESSIONID: ${cookie["JSESSIONID"].value}</p>>
    <p>Param id: ${param.id}</p>
    <p>Param test: ${param.test}</p>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>
