package edu.volkov.mvc.servlet;

import edu.volkov.mvc.exception.ValidationException;
import edu.volkov.mvc.util.JspHelper;
import edu.volkov.mvc.util.UrlPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import edu.volkov.mvc.dto.CreateUserDto;
import edu.volkov.mvc.service.UserService;

import java.io.IOException;
import java.util.List;

@MultipartConfig(fileSizeThreshold = 1024 * 1024)
@WebServlet(UrlPath.REGISTRATION_URL)
public class RegistrationServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", List.of("USER", "ADMIN"));
        req.setAttribute("genders", List.of("MALE", "FEMALE"));
        req.getRequestDispatcher(JspHelper.getJspPath("registration")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            userService.create(CreateUserDto.builder()
                    .name(req.getParameter("name"))
                    .image(req.getPart("image"))
                    .birthday(req.getParameter("birthday"))
                    .email(req.getParameter("email"))
                    .password(req.getParameter("password"))
                    .role(req.getParameter("role"))
                    .gender(req.getParameter("gender"))
                    .build());
        } catch (ValidationException e) {
            e.getErrors().forEach(System.out::println);
            req.setAttribute("errors", e.getErrors());
            doGet(req, resp);
        }

        resp.sendRedirect("/login");
    }
}
