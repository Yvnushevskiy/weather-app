package org.example.services;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import static org.example.config.ApiService.ApiKey;

public class WeatherApiService {
    static HttpClient client = HttpClient.newHttpClient();
//    HttpRequest request = HttpRequest.newBuilder()
//            .uri(new URI())
    public static String getByName(String name,int limit){
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://api.openweathermap.org/geo/1.0/direct?q="+name+"&limit="+limit+"&appid="+ ApiKey))
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
