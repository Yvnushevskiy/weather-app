package org.example.servlets;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

@WebServlet("/")
public class mainPageServlet extends HttpServlet {




    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine templateEngine = (TemplateEngine) getServletContext().getAttribute("templateEngine");

        if (templateEngine == null) {
            resp.getWriter().write("Cant find HTML file");
        }else {
            Context context = new Context();
            context.setVariable("error", req.getParameter("error"));
            templateEngine.process("mainpage", context, resp.getWriter());
        }
    }
}
