package com.statista.weather.app;

import com.statista.weather.app.DTO.WeatherDTO;

/**
 * Please add useful methods to this interface to retrieve weather information
 */
public interface IWeatherService {
        WeatherDTO getWeatherByCity(String city);
        WeatherDTO getWeatherByCoordinates(float longitude, float latitude);
}
