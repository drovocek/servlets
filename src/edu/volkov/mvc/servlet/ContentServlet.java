package edu.volkov.mvc.servlet;

import edu.volkov.mvc.dto.FlightDto;
import edu.volkov.mvc.service.FlightService;
import edu.volkov.mvc.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@WebServlet("/content")
public class ContentServlet extends HttpServlet {

    public static final FlightService flightService = FlightService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<FlightDto> flights = flightService.findAll();
        Map<Long, String> flightsMap = flights.stream()
                .collect(toMap(FlightDto::getId, FlightDto::getDescription));
        req.setAttribute("flights", flights);
        req.getSession().setAttribute("flightsMap", flightsMap);

        req.getRequestDispatcher(JspHelper.getJspPath("content")).forward(req, resp);
    }
}
