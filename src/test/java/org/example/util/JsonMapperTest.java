package org.example.util;

import org.example.model.Location;
import org.example.services.WeatherApiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;


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

        String jsonResponse = weatherApiService.getByName("london", 2);

        List<Location> locations = jsonMapper.convertJsonToLocation(jsonResponse);

        for (Location location : locations) {
            assert location.getName() != null && !location.getName().isEmpty() : "Город не должен быть пустым";
            assert location.getLat() != null : "Широта не должна быть null";
            assert location.getLon() != null : "Долгота не должна быть null";
            assert location.getCountry() != null && !location.getCountry().isEmpty() : "Страна не должна быть пустой";
        }


    }
}
