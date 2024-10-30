package org.example.config;

import io.github.cdimascio.dotenv.Dotenv;


public class ApiService {
    private static final Dotenv dotenv = Dotenv.load();
    public static final String ApiKey = dotenv.get("API_KEY");


}
