package org.example.config;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.Getter;


public class ApiService {
    private final Dotenv dotenv = Dotenv.load();
    @Getter
    private final String ApiKey = dotenv.get("API_KEY");


}
