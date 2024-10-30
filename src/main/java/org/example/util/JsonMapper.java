package org.example.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Location;

import java.io.IOException;

public class JsonMapper {
    public Location convertJsonToLocation(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Location[] locations = objectMapper.readValue(json, Location[].class);
        return (locations.length > 0) ? locations[0] : null;
    }
}
