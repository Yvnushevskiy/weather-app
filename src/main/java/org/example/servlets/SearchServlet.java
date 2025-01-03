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
import org.example.modelDTO.LocationDTO;
import org.example.repositories.UserRepository;
import org.example.repositories.UserRepositoryImpl;
import org.example.services.UserService;
import org.example.services.WeatherApiService;
import org.example.util.JsonMapper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    WeatherApiService weatherApiService = DependencyContainer.getInstance().getWeatherApiService();
    JsonMapper jsonMapper = DependencyContainer.getInstance().getJsonMapper();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String locationName = req.getParameter("name");
        String jsonResponse = weatherApiService.getLocationByName(locationName, 2);
        List<LocationDTO> locations1 = jsonMapper.convertJsonToLocation(jsonResponse);
        req.setAttribute("locations", locations1);

        TemplateEngine templateEngine = (TemplateEngine) getServletContext().getAttribute("templateEngine");

        if (templateEngine == null) {
            resp.getWriter().write("Cant find HTML file");
        } else {
            Context context = new Context();
            List<LocationDTO> locations = (List<LocationDTO>) req.getAttribute("locations");
            context.setVariable("locations", locations);
            templateEngine.process("search-results", context, resp.getWriter());

        }
    }
}

