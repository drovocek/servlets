package edu.volkov.mvc.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static edu.volkov.mvc.util.UrlPath.LOCALE_URL;
import static edu.volkov.mvc.util.UrlPath.LOGIN_URL;

@WebServlet(LOCALE_URL)
public class LocaleServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var lang = req.getParameter("lang");
        req.getSession().setAttribute("lang", lang);

        var prevPage = req.getHeader("referer");
        var page = prevPage != null ? prevPage : LOGIN_URL;
        resp.sendRedirect(page + "?lang=" + lang);
    }
}
