package com.pbs.edu.WeatherDataService.controller;

import com.pbs.edu.WeatherDataService.model.WeatherData;
import com.pbs.edu.WeatherDataService.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    @GetMapping(value = "/weather", produces = "application/json")
    public WeatherData getWeather(@RequestParam String city, @RequestParam String countryCode) {
        return weatherService.getWeatherData(city, countryCode);
    }

    @GetMapping("/weather/history")
    public List<WeatherData> getWeatherHistory(
            @RequestParam(name = "city", required = false) String city,
            @RequestParam(name = "country", required = false) String country
    ) {
        if (city != null && country != null) {
            return weatherService.getWeatherHistory(city, country);
        } else {
            return weatherService.getWeatherHistory();
        }
    }
}

