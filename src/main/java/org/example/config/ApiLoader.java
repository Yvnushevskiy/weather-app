package org.example.config;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.Getter;

import java.io.IOException;


public class ApiLoader {  //TODO переделать с констант и статиков , на инициализацию в конструкторе," используя переменное окружение"
    private static final Dotenv DOTENV;
    private static final String API_KEY;

    static {
        try {
            DOTENV = Dotenv.load();
        } catch (Exception e) {
            throw new RuntimeException("File '.env' not found" + e.getMessage(), e);
        }
        try {
            API_KEY = DOTENV.get("API_KEY");
        } catch (Exception e) {
            throw new RuntimeException("API key in '.env' not found" + e.getMessage(), e);
        }

    }

    public static String getApiKey() {
        return API_KEY;
    }


}
