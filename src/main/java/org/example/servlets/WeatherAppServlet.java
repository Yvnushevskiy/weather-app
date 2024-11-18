package org.example.servlets;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
//TODO в конце проекта пройтись через mvn dependency:analyze

@WebServlet("/home")
public class WeatherAppServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        //init если нужно накидать что будет инициализироваться вместе с сервлетом Todo
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (session.getAttribute("user") != null) {
        }
    }

}
