package edu.volkov.mvc.filter;

import edu.volkov.mvc.dto.UserDto;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/admin")
public class A_UnsafeFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        System.out.println("UnsafeFilter in");

        if (servletRequest instanceof HttpServletRequest &&
                servletResponse instanceof HttpServletResponse) {
            var userObj =
                    ((HttpServletRequest) servletRequest)
                            .getSession()
                            .getAttribute("user");
            if (userObj instanceof UserDto) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                ((HttpServletResponse) servletResponse).sendRedirect("/registration");
            }
        } else {
            throw new RuntimeException("Not Http request or response");
        }

        System.out.println("UnsafeFilter out");
    }
}
