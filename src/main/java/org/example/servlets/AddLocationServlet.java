package org.example.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.container.DependencyContainer;

import org.example.model.Location;
import org.example.services.LocationService;
import org.example.services.UserService;
import org.thymeleaf.context.Context;


import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/addLocation")
public class AddLocationServlet extends HttpServlet {
    LocationService locationService = DependencyContainer.getInstance().getLocationService();
    UserService userService = DependencyContainer.getInstance().getUserService();



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Location location = new Location();
        location.setName(req.getParameter("locationName"));
        location.setLat(new BigDecimal(req.getParameter("locationLat")));
        location.setLon(new BigDecimal(req.getParameter("locationLon")));
        location.setCountry(req.getParameter("locationCountry"));
        location.setState(req.getParameter("locationState"));
        location.setUser(userService.getByUsername(req.getAttribute("username").toString()));
        locationService.saveLocation(location);
        resp.sendRedirect("home");

    }
}
