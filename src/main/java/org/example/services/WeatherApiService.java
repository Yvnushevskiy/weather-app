package org.example.services;

import jakarta.ws.rs.core.UriBuilder;
import org.example.config.ApiLoader;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class WeatherApiService {
    private final HttpClient client;

    public WeatherApiService() {
        this.client = HttpClient.newHttpClient();
    }


    public String getLocationByName(String name, int limit) {
        try {
            URI uri = UriBuilder.fromUri(ApiLoader.getApiUrl())
                    .path("/geo/1.0/direct")
                    .queryParam("q",name)
                    .queryParam("limit",limit)
                    .queryParam("appid",ApiLoader.getApiKey())
                    .build();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();

            return client.send(request, HttpResponse.BodyHandlers.ofString()).body();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Request failed:"+e.getMessage(),e);
        }
    }

    public  String getWeatherByCoordinates(double lat, double lon){
        try{
            URI uri = UriBuilder.fromUri(ApiLoader.getApiUrl())
                    .path("data/2.5/weather")
                    .queryParam("lat", lat)
                    .queryParam("lon",lon)
                    .queryParam("appid",ApiLoader.getApiKey())
                    .build();


            HttpRequest request= HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();
            return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (IOException | InterruptedException e){
            throw new RuntimeException("Request failed:"+e.getMessage(),e);
        }
   }
}
