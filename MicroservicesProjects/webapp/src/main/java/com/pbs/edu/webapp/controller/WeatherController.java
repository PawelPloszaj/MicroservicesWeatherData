package com.pbs.edu.webapp.controller;

import com.pbs.edu.webapp.model.User;
import com.pbs.edu.webapp.model.WeatherData;
import com.pbs.edu.webapp.service.AuthService;
import com.pbs.edu.webapp.service.WeatherServiceClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class WeatherController {

    private final WeatherServiceClient weatherServiceClient;
    private final AuthService authService;

    public WeatherController(WeatherServiceClient weatherServiceClient, AuthService authService) {
        this.weatherServiceClient = weatherServiceClient;
        this.authService = authService;
    }

    @GetMapping("/weather")
    public String getWeather(@RequestParam(value = "city", required = false) String city,
                             @RequestParam(value = "country", required = false) String country,
                             Model model,
                             HttpSession session) {
        User authenticatedUser = (User) session.getAttribute("user");
        if (authenticatedUser == null) {
            return "redirect:/login"; // Przekierowanie na stronę logowania, jeśli użytkownik nie jest zalogowany
        }

        if (city == null || country == null) {
            city = authenticatedUser.getCity();
            country = authenticatedUser.getCountry();
        }

        WeatherData currentWeather = weatherServiceClient.getCurrentWeather(city, country);
        model.addAttribute("currentWeather", currentWeather);
        model.addAttribute("cityName", city);
        model.addAttribute("user", session.getAttribute("user"));
        return "weather";
    }

    @GetMapping("/history")
    public String getWeatherHistory(@RequestParam(value = "city", required = false) String city,
                                    @RequestParam(value = "country", required = false) String country,
                                    Model model,
                                    HttpSession session) {
        User authenticatedUser = (User) session.getAttribute("user");
        if (authenticatedUser == null) {
            return "redirect:/login"; // Przekierowanie na stronę logowania, jeśli użytkownik nie jest zalogowany
        }

        if (city == null || country == null) {
            city = authenticatedUser.getCity();
            country = authenticatedUser.getCountry();
        }

        List<WeatherData> historicalWeather = weatherServiceClient.getHistoricalWeather(city, country);
        model.addAttribute("historicalWeather", historicalWeather);
        model.addAttribute("cityName", city);
        model.addAttribute("user", session.getAttribute("user"));
        return "history";
    }
}
