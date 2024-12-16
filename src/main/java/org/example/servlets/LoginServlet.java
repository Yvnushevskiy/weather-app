package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.container.DependencyContainer;
import org.example.services.SessionService;
import org.example.services.UserService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    SessionService sessionService = DependencyContainer.getInstance().getSessionService();
    UserService userService = DependencyContainer.getInstance().getUserService();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        resp.addCookie(new Cookie("WeatherUUID",userService.login(username, password).toString()));
        resp.sendRedirect("home");
//            resp.sendRedirect("login?error=true"); todo



    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine templateEngine = (TemplateEngine) getServletContext().getAttribute("templateEngine");

        if (templateEngine == null) {
            resp.getWriter().write("Cant find HTML file");
        }else {
            Context context = new Context();
            context.setVariable("error", req.getParameter("error"));
            templateEngine.process("login", context, resp.getWriter());
        }
    }
}
