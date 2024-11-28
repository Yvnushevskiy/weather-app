package org.example.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Location;
import org.example.modelDTO.LocationDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonMapper {
    public List<LocationDTO> parseJsonToDTO(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, LocationDTO.class));
    }
    public List<LocationDTO> convertJsonToLocation(String json) throws IOException {
        List<LocationDTO> locationDTOs = parseJsonToDTO(json);
        return (locationDTOs != null && !locationDTOs.isEmpty()) ? locationDTOs : null;
    }
}
