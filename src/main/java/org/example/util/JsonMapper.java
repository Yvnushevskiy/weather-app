package org.example.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Location;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonMapper {

    public List<Location> parseJson(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, Location.class));
    }
    public List<Location> convertJsonToLocation(String json) throws IOException {
        List<Location> locations = parseJson(json);
        return (locations != null && !locations.isEmpty()) ? locations : null;
    }
}
