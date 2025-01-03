package org.example.servlets;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.container.DependencyContainer;
import org.example.exception.Database.UserPersistException;
import org.example.model.User;
import org.example.repositories.UserRepository;
import org.example.repositories.UserRepositoryImpl;
import org.example.services.UserService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Stream;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    UserService userService = DependencyContainer.getInstance().getUserService();



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if(!userService.DoesUserExist(username)){             //TODO doesnt work with more then 1 user
                userService.addUser(username, password);
                resp.sendRedirect("login");
        } else {
            resp.sendRedirect("/register?error=user-already-exists");
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
            context.setVariable("username",req.getAttribute("username").toString());
            context.setVariable("error", req.getParameter("error"));
            templateEngine.process("register", context, resp.getWriter());
        }
    }
}
