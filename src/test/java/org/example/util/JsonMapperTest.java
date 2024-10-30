package org.example.util;

import org.example.model.Location;
import org.example.services.WeatherApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashSet;


public class JsonMapperTest {

    private JsonMapper jsonMapper;
    private WeatherApiService weatherApiService;

    @BeforeEach
    public void setUp() {
        jsonMapper = new JsonMapper();
        weatherApiService = new WeatherApiService();
    }

    @Test
    public void testJsonToLocation() throws IOException {

        String jsonResponse = weatherApiService.getByName("london",1);
        System.out.println("JSON Response: " + jsonResponse);

        Location location = jsonMapper.convertJsonToLocation(jsonResponse);

        // Выводим данные Location
        System.out.println("Город: " + location.getName());
        System.out.println("Широта: " + location.getLat());
        System.out.println("Долгота: " + location.getLon());
        System.out.println("Страна: " + location.getCountry());


    }
}
