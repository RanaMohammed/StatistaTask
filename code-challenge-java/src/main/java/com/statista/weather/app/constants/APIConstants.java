package com.statista.weather.app.constants;

public class APIConstants {
    private static final String KEY = "cf606a433feb579c0955e9dfcad79565";
    private static final String BASE_URL  = "https://api.openweathermap.org/data/2.5/weather";

    public static String getKEY() {
        return KEY;
    }
    public static String getBaseUrl() {
        return BASE_URL;
    }

}
