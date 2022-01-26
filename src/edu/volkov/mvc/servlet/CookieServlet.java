package edu.volkov.mvc.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet("/cookies")
public class CookieServlet extends HttpServlet {

    private static final String UNIQUE_ID = "userIdMy";
    private static final AtomicInteger counter = new AtomicInteger();

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Boolean hasUniqueId = Optional.ofNullable(req.getCookies())
                .map(cookies -> Arrays.stream(cookies)
                        .map(Cookie::getName)
                        .anyMatch(UNIQUE_ID::equals))
                .orElse(false);
        if (!hasUniqueId) {
            var cookie = new Cookie(UNIQUE_ID, "777");
            cookie.setPath("/cookies");
            cookie.setMaxAge(15 * 60);
            resp.addCookie(cookie);
            counter.incrementAndGet();
        }
        resp.setContentType("text/html");
        resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
        try (var writer = resp.getWriter()) {
            writer.write("Количество уникальных пользователей:");
            writer.write("""
                    <h3>%s</h3>
                    """.formatted(counter.get()));
        }
    }
}
