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
import org.example.model.Location;
import org.example.model.User;
import org.example.repositories.UserRepository;
import org.example.repositories.UserRepositoryImpl;
import org.example.services.LocationService;
import org.example.services.UserService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@WebServlet("/removeLocation")
public class RemoveLocationServlet extends HttpServlet {
    LocationService locationService = DependencyContainer.getInstance().getLocationService();
    UserService userService = DependencyContainer.getInstance().getUserService();



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int locationId = locationService.getLocationIDByUsernameAndLocationName(
                req.getAttribute("username").toString(), req.getParameter("locationName")).get();

        Location location = locationService.getLocationByID(locationId).get();

        locationService.removeLocation(location);

        resp.sendRedirect("home");


    }
}
