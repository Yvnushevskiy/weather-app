package org.example.servlets;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    UserService userService;
    UserRepositoryImpl userRepository;

    public LoginServlet() {
        this.userRepository = new UserRepositoryImpl();
        this.userService = new UserService(userRepository);
    }

    private static final long EXPIRATION_TIME = 60 * 30;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if(userService.isValidUser(username,password)){
            resp.sendRedirect(req.getContextPath() + "/");
        }else {
            resp.sendRedirect("login?error=true");
        }


        /*TODO
        if (userservice.isvaliduser(username,password)){
       Http session = req.getSession
       session.setAttribute("username",username);
       session.setMaxInactiveInterval(EXPIRATION_TIME);
       sessionrepository.addsession(userRepository.findbyusername(username),session)
       }else{
       chto-to s parolem
       }




         */


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
