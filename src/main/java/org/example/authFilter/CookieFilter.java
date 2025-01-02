package org.example.authFilter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.container.DependencyContainer;
import org.example.exception.Database.SessionNotFoundException;
import org.example.model.Session;
import org.example.services.SessionService;

import java.io.IOException;
import java.util.UUID;
//TODO if smthg going wrong need swap  counts of url  for WebFilter(urlPatterns=) and count all URL

@WebFilter(urlPatterns = "/*")
public class CookieFilter implements Filter {
    SessionService sessionService = DependencyContainer.getInstance().getSessionService();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (request.getRequestURI().endsWith("login")) {
            filterChain.doFilter(request, response);
            return;
        }

        if (request.getRequestURI().endsWith("register")) {
            filterChain.doFilter(request, response);
            return;
        }



        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("WeatherUUID".equals(cookie.getName())) {
                    UUID sessionID = UUID.fromString(cookie.getValue());
                    try {
                        request.setAttribute("user",sessionService.getSessionById(sessionID).getUser().getLogin());
                        filterChain.doFilter(request, response);
                        return;
                    } catch(SessionNotFoundException e) {
                        response.sendRedirect(request.getContextPath() + "/login");
                        filterChain.doFilter(request, response);
                        return;

                    }
                }
            }
        }
        response.sendRedirect(request.getContextPath() + "/login");
    }
}
