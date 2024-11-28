package org.example.modelDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationDTO {
    private String name;
    private String country;
    private BigDecimal lat;
    private BigDecimal lon;
    private String state;
}
