package org.example.services;

import org.example.config.ApiLoader;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class WeatherApiService {
    private final ApiLoader apiService;
    private final HttpClient client;

    public WeatherApiService() {
        this.apiService = new ApiLoader();
        this.client = HttpClient.newHttpClient();
    }


    public String getByName(String name, int limit) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://api.openweathermap.org/geo/1.0/direct?q=" + name + "&limit=" + limit + "&appid=" + ApiLoader.getApiKey()))
                    .GET()
                    .build();
            return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*public static String getByCoordinates(double lat, double lon){
        try{
            HttpRequest request= HttpRequest.newBuilder()
                    .uri(URI.create("https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key})"))

        }
   }*/
}
