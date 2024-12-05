package com.pbs.edu.WeatherDataService.service;

import com.pbs.edu.WeatherDataService.model.WeatherData;
import com.pbs.edu.WeatherDataService.repository.WeatherDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Autowired
    private WeatherDataRepository weatherDataRepository;

    @Value("${weather.api.url}")
    private String apiUrl;

    @Value("${weather.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public WeatherData getWeatherData(String city, String countryCode) {
        String url = String.format("%s?q=%s,%s&APPID=%s&units=metric", apiUrl, city, countryCode, apiKey);
        WeatherData weatherData = restTemplate.getForObject(url, WeatherData.class);

        if(weatherData != null) {
            weatherData.setCityName(city);
            weatherData.setCountry(countryCode);
            weatherDataRepository.save(weatherData);
        }

        return weatherData;
    }

    public Iterable<WeatherData> getWeatherHistory() {
        return weatherDataRepository.findAll();
    }
}

