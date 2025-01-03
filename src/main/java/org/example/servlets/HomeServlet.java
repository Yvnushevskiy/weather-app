package org.example.servlets;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.container.DependencyContainer;
import org.example.model.Location;
import org.example.model.User;
import org.example.modelDTO.LocationDTO;
import org.example.repositories.UserRepository;
import org.example.repositories.UserRepositoryImpl;
import org.example.services.LocationService;
import org.example.services.UserService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    LocationService locationService = DependencyContainer.getInstance().getLocationService();
    UserService userService = DependencyContainer.getInstance().getUserService();




    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     //todo remove weather(location)
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {






        TemplateEngine templateEngine = (TemplateEngine) getServletContext().getAttribute("templateEngine");

        if (templateEngine == null) {
            resp.getWriter().write("Cant find HTML file");
        }else {
            Optional<List<Location>> locationsOptional = locationService.getAllLocationsByUsername(req.getAttribute("username").toString());
            List<Location> locations = locationsOptional.orElse(new ArrayList<>()); // Если локации не найдены, используем пустой список
            Context context = new Context();
            context.setVariable("locations", locations);
            context.setVariable("username",req.getAttribute("username").toString());
            templateEngine.process("index", context, resp.getWriter());
        }
    }
}
