<%@ page import="edu.volkov.mvc.service.TicketService" %>
<%@ page import="edu.volkov.mvc.dto.TicketDto" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../../index.html" %>
<html>
<head>
    <title>Flights</title>
</head>
    <body>
        <%@ include file="header.jsp"%>
        <h1>Flights</h1>
        <ul>
            <c:forEach var="flight" items="${requestScope.flights}">
                <li>
                    <a href="${pageContext.request.contextPath}/tickets?flightId=${flight.id}">
                            ${fn:toLowerCase(flight.description)}
                    </a>
                </li>
            </c:forEach>
        </ul>
    </body>
</html>
