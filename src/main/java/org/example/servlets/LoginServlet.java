package org.example.servlets;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.repositories.UserRepository;
import org.example.repositories.UserRepositoryImpl;
import org.example.services.UserService;

import java.io.IOException;
import java.util.Date;
import java.util.stream.Stream;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long EXPIRATION_TIME = 60 * 30;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        UserRepositoryImpl userRepository = new UserRepositoryImpl();
        UserService userService = new UserService(userRepository);   // TODO в конструктор переместить

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
}