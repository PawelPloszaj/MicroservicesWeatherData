package com.pbs.edu.webapp.service;

import com.pbs.edu.webapp.model.WeatherData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class WeatherServiceClient {

    @Value("${weather.service.url}")
    private String weatherServiceUrl;

    private final RestTemplate restTemplate;

    public WeatherServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherData getCurrentWeather(String city, String country) {
        String url = weatherServiceUrl + "/weather?city=" + city + "&countryCode=" + country;
        return restTemplate.getForObject(url, WeatherData.class);
    }

    public List<WeatherData> getHistoricalWeather(String city, String country) {
        String url = weatherServiceUrl + "/weather/history?city=" + city + "&country=" + country;
        return List.of(restTemplate.getForObject(url, WeatherData[].class));
    }
}
