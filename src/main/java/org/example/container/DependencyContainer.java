package org.example.container;

import org.example.repositories.*;
import org.example.services.LocationService;
import org.example.services.SessionService;
import org.example.services.UserService;
import org.example.services.WeatherApiService;
import org.example.util.JsonMapper;

public class DependencyContainer {
    private static final DependencyContainer instance = new DependencyContainer();

    private final WeatherApiService weatherApiService = new WeatherApiService();
    private final JsonMapper jsonMapper = new JsonMapper();
    private final UserRepository userRepository = new UserRepositoryImpl();
    private final SessionRepository sessionRepository = new SessionRepositoryImpl();
    private final SessionService sessionService = new SessionService(sessionRepository);
    private final LocationRepositoryImpl locationRepository = new LocationRepositoryImpl();
    private final UserService userService = new UserService(userRepository,sessionService);
    private final LocationService locationService = new LocationService(locationRepository, userService);

    private DependencyContainer() {}

    public static DependencyContainer getInstance() {
        return instance;
    }

    public SessionService getSessionService() {
        return sessionService;
    }

    public UserService getUserService() {
        return userService;
    }
    public LocationService getLocationService() {
        return locationService;
    }
    public JsonMapper getJsonMapper() {
        return jsonMapper;
    }
    public WeatherApiService getWeatherApiService() {
        return weatherApiService;
    }
}
