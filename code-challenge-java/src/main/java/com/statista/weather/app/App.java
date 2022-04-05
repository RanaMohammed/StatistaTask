package com.statista.weather.app;


import com.statista.weather.app.DTO.WeatherDTO;
import com.statista.weather.app.constants.APIConstants;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class App {

    /**
     * Either create a simple console application
     * or a javaFx application which sources the DarkSky
     * API to retrieve the current weather information for the given
     * location on the planet.
     *
     * @param args
     */
    public static void main(String[] args) {
        DarkSkyWeatherService darkSkyWeatherService =  new DarkSkyWeatherService();
        WeatherDTO  weatherByCity = darkSkyWeatherService.getWeatherByCity("London");
        DarkSkyWeatherService.printData(weatherByCity);
        WeatherDTO weatherByCoordinates = darkSkyWeatherService.getWeatherByCoordinates(-0.1257f,51.5085f);
        DarkSkyWeatherService.printData(weatherByCoordinates);
}
}
