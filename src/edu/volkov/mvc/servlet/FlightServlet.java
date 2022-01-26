package edu.volkov.mvc.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import edu.volkov.mvc.dto.FlightDto;
import edu.volkov.mvc.service.FlightService;

import java.io.IOException;
import java.util.List;

import static edu.volkov.mvc.util.JspHelper.getJspPath;

@WebServlet("/flights")
public class FlightServlet extends HttpServlet {

    private final FlightService flightService = FlightService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<FlightDto> flights = flightService.findAll();
        req.setAttribute("flights", flights);
        req.getRequestDispatcher(getJspPath("flights")).forward(req, resp);

//        resp.setContentType("text/html");
//        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
//        try (var writer = resp.getWriter()) {
//            writer.write("<h1>Список перелетов:</h1>");
//            writer.write("<ul>");
//            flightService.findAll().forEach(flightDto ->
//                    writer.write("""
//                            <li>
//                            <a href="/tickets?flightId=%d">%s</a>
//                            </li>
//                            """.formatted(
//                            flightDto.getId(),
//                            flightDto.getDescription())));
//            writer.write("</ul>");
//        }
    }
}
