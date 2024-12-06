package org.example.modelDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.example.model.Location;
import org.example.model.User;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationDTO {
    private String name;
    private String country;
    private BigDecimal lat;
    private BigDecimal lon;
    private String state;

    public Location LocationDTOtoEntity(LocationDTO locationDTO) {
        Location location = new Location();
        location.setCountry(locationDTO.getCountry());
        location.setLat(locationDTO.getLat());
        location.setLon(locationDTO.getLon());
        location.setState(locationDTO.getState());
        location.setName(locationDTO.getName());
        return location;
    }
}

