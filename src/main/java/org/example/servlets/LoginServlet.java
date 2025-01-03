package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.container.DependencyContainer;
import org.example.exception.Database.UserNotFoundException;
import org.example.model.User;
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
        try {
            User user = userService.getUserByUsernameAndPassword(username, password);
            resp.addCookie(new Cookie("WeatherUUID", sessionService.addNewSessionToUser(user).getId().toString()));
            resp.sendRedirect("home");
        } catch (UserNotFoundException e) {
            resp.sendRedirect("login?error=true");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getCookies() != null) {
            for (Cookie cookie : req.getCookies()) {
                if ("WeatherUUID".equals(cookie.getName())) {
                    resp.sendRedirect("home");
                    return;
                }
            }
        }


        TemplateEngine templateEngine = (TemplateEngine) getServletContext().getAttribute("templateEngine");

        if (templateEngine == null) {
            resp.getWriter().write("Cant find HTML file");
        }else {
            Context context = new Context();
            String error = req.getParameter("error");
            if(error==null){
                templateEngine.process("sign-in", context, resp.getWriter());
            } else{
                templateEngine.process("sign-in-with-errors", context, resp.getWriter());
            }

        }
    }
}
