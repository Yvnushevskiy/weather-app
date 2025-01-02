package org.example.authFilter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
//TODO if smthg going wrong need swap  28 for WebFilter(urlPatterns=) and count all URL

@WebFilter(urlPatterns = "/*")
public class CookieFilter implements Filter {

    //todo change if for methods
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO need check can i add here smthg for load every time buy loading every servlet.
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
                    String sessionID = cookie.getValue();


                }
            }

        }
    }
}
