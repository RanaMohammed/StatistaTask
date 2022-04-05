package com.statista.weather.app;

import com.statista.weather.app.DTO.WeatherDTO;
import com.statista.weather.app.constants.APIConstants;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Please refer to https://darksky.net/dev/docs on how to access
 * the DarkSky API. The API key to use will be provided.
 */
public class DarkSkyWeatherService implements IWeatherService {

    @Override
    public WeatherDTO getWeatherByCity(String city) {
        String apiUrl = APIConstants.getBaseUrl()+"?q="+city+"&appid="+APIConstants.getKEY();
        return sendHttpRequest(apiUrl);

    }

    @Override
    public WeatherDTO getWeatherByCoordinates(float longitude, float latitude) {
        String apiUrl = APIConstants.getBaseUrl()+"?lat="+latitude+"&lon="+longitude+"&appid="+APIConstants.getKEY();
        return sendHttpRequest(apiUrl);    }

    public WeatherDTO sendHttpRequest(String apiUrl){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .GET()
                .build();
        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
//            System.out.println(response.body());
            return convertJSONToWeatherDTO(response.body());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static WeatherDTO convertJSONToWeatherDTO(String response) {
        WeatherDTO weatherResponse = new WeatherDTO();
        JSONObject jsonResponse = new JSONObject(response);
        weatherResponse.setCoord(jsonResponse.getJSONObject("coord"));
        weatherResponse.setCityName(jsonResponse.getString("name"));
        weatherResponse.setCountry(jsonResponse.getJSONObject("sys").getString("country"));
        weatherResponse.setDescription(jsonResponse.getJSONArray("weather").getJSONObject(0).getString("description"));
        JSONObject mainData = jsonResponse.getJSONObject("main");
        weatherResponse.setTemp(mainData.getFloat("temp"));
        weatherResponse.setTempMax(mainData.getFloat("temp_max"));
        weatherResponse.setTempMin(mainData.getFloat("temp_min"));
        weatherResponse.setHumidity(mainData.getInt("humidity"));
        weatherResponse.setPressure(mainData.getInt("pressure"));

        return weatherResponse;

    }

    static public void printData(WeatherDTO apiResponse){
        System.out.println("============================================");
        System.out.println("Country: "+apiResponse.getCountry());
        System.out.println("City: "+apiResponse.getCityName());
        System.out.println("Temperature: " + apiResponse.getTemp());
        System.out.println("Maximum Temperature: "+ apiResponse.getTempMax());
        System.out.println("Minimum Temperature: "+ apiResponse.getTempMin());
        System.out.println("Humidity: "+ apiResponse.getHumidity());
        System.out.println("Pressure: "+ apiResponse.getPressure());
    }

}
