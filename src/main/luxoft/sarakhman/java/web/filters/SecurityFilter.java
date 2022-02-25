package web.filters;

import services.SecurityService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class SecurityFilter extends HttpFilter {
    private final SecurityService securityService;

    public SecurityFilter(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            response.sendRedirect("/login");
        }

        boolean existToken = securityService.checkToken(cookies);
        if (!existToken) {
            response.sendRedirect("/login");
        } else {
            chain.doFilter(request, response);
        }

    }
}
