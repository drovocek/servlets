package edu.volkov.mvc.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import edu.volkov.mvc.dto.UserDto;
import edu.volkov.mvc.service.UserService;

import java.io.IOException;

import static edu.volkov.mvc.util.JspHelper.getJspPath;
import static edu.volkov.mvc.util.UrlPath.LOGIN_URL;

@MultipartConfig(fileSizeThreshold = 1024 * 1024)
@WebServlet(LOGIN_URL)
public class LoginServlet extends HttpServlet {

    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(getJspPath("login")).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        userService.login(
                        req.getParameter("email"),
                        req.getParameter("password"))
                .ifPresentOrElse(
                        userDto -> onLoginSuccess(userDto, req, resp),
                        () -> onLoginFail(req, resp));
    }

    @SneakyThrows
    private void onLoginSuccess(UserDto userDto, HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().setAttribute("user", userDto);
        resp.sendRedirect("/flights");
    }

    @SneakyThrows
    private void onLoginFail(HttpServletRequest req, HttpServletResponse resp) {
        resp.sendRedirect("/login?error&email=".concat(req.getParameter("email")));
    }
}
