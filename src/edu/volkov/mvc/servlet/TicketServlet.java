package edu.volkov.mvc.servlet;

import edu.volkov.mvc.util.JspHelper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import edu.volkov.mvc.service.TicketService;

@WebServlet("/tickets")
public class TicketServlet extends HttpServlet {

    private final TicketService ticketService = TicketService.getInstance();

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Long flightId = Long.valueOf(req.getParameter("flightId"));
        req.setAttribute("tickets", ticketService.findAllByFlightId(flightId));
        req.getRequestDispatcher(JspHelper.getJspPath("tickets")).forward(req, resp);

//        resp.setContentType("text/html");
//        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
//        try (var writer = resp.getWriter()) {
//            writer.write("<h1>Купленные билеты:</h1>");
//            writer.write("<ul>");
//            ticketService.findAllByFlightId(flightId).forEach(ticketDto ->
//                    writer.write("""
//                            <li>
//                            %s
//                            </li>
//                            """.formatted(ticketDto.getSeatNo())));
//            writer.write("</ul>");
//        }
    }
}
