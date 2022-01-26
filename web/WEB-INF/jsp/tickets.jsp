<%@ page import="edu.volkov.mvc.service.TicketService" %>
<%@ page import="edu.volkov.mvc.dto.TicketDto" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../../index.html" %>
<html>
<head>
    <title>Tickets</title>
</head>
<body>
    <h1>Tickets</h1>
    <ul>
        <c:forEach var="ticket" items="${requestScope.tickets}">
            <li>${fn:toLowerCase(ticket.seatNo)}</li>
        </c:forEach>
        <%--    &lt;%&ndash; JSP Declaration &ndash;%&gt;--%>
        <%--    <%!--%>
        <%--        public void jspInit(){--%>
        <%--            super.jspInit();--%>
        <%--            System.out.println("Hello!");--%>
        <%--        }--%>
        <%--    %>--%>

        <%--    &lt;%&ndash; JSP Scriplet &ndash;%&gt;--%>
        <%--    <%--%>
        <%--        String flightIdStr = request.getParameter("flightId");--%>
        <%--        Long flightId = flightIdStr == null ? 1L : Long.parseLong(flightIdStr);--%>
        <%--        TicketService ticketService = TicketService.getInstance();--%>
        <%--        List<TicketDto> ticketDtos = ticketService.findAllByFlightId(flightId);--%>
        <%--        for (int i = 0; i < ticketDtos.size(); i++) {--%>
        <%--            TicketDto ticketDto = ticketDtos.get(i);--%>
        <%--            out.write(String.format("<li>%s</li>", ticketDto.getSeatNo()));--%>
        <%--        }--%>
        <%--    %>--%>
    </ul>
</body>
</html>
