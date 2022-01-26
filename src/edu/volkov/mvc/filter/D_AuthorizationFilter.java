package edu.volkov.mvc.filter;

import edu.volkov.mvc.util.UrlPath;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

@WebFilter("/*")
public class D_AuthorizationFilter implements Filter {

    public static final Set<String> PUBLIC_PATH =
            Set.of(UrlPath.LOGIN_URL, UrlPath.REGISTRATION_URL, UrlPath.IMAGES_URL, UrlPath.LOCALE_URL);

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        System.out.println("AuthorizationFilter in");
        var uri = ((HttpServletRequest) servletRequest).getRequestURI();
        if (isPublickPath(uri) || isUserLogin(servletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            String prevPage = ((HttpServletRequest) servletRequest).getHeader("referer");
            ((HttpServletResponse) servletResponse).sendRedirect(prevPage != null ? prevPage : UrlPath.LOGIN_URL);
        }
        System.out.println("AuthorizationFilter out");
    }

    private boolean isPublickPath(String uri) {
        return PUBLIC_PATH.stream().anyMatch(uri::startsWith);
    }

    private boolean isUserLogin(ServletRequest servletRequest) {
        return ((HttpServletRequest) servletRequest).getSession().getAttribute("user") != null;
    }
}
