package com.pbs.edu.webapp.scheduler;

import com.pbs.edu.webapp.model.User;
import com.pbs.edu.webapp.model.WeatherData;
import com.pbs.edu.webapp.service.NotificationServiceClient;
import com.pbs.edu.webapp.service.UserServiceClient;
import com.pbs.edu.webapp.service.WeatherServiceClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class WeatherNotificationScheduler {

    private final UserServiceClient userService;
    private final WeatherServiceClient weatherService;
    private final NotificationServiceClient notificationService;

    public WeatherNotificationScheduler(UserServiceClient userService, WeatherServiceClient weatherService, NotificationServiceClient notificationService) {
        this.userService = userService;
        this.weatherService = weatherService;
        this.notificationService = notificationService;
    }

    @Scheduled(fixedRate = 30000)
    public void sendWeatherNotifications() {
        List<User> users = userService.getAllUsers();

        for (User user : users) {
            WeatherData weatherData = weatherService.getCurrentWeather(user.getCity(), user.getCountry());

            if (shouldNotify(user, weatherData)) {
                notificationService.sendNotification(user.getEmail(), "Weather Alert", generateMessage(weatherData), LocalDateTime.now(), false);
            }
        }
    }

    private boolean shouldNotify(User user, WeatherData weatherData) {
        boolean notifyForRain = user.isRainNotification() && "Rain".equalsIgnoreCase(weatherData.getMain());
        boolean notifyForSnow = user.isSnowNotification() && "Snow".equalsIgnoreCase(weatherData.getMain());
        boolean notifyForWind = user.isWindNotification() && weatherData.getWindSpeed() > 20;
        boolean notifyForHighTemp = user.isHighTempNotification() && weatherData.getTemp() > 30;
        boolean notifyForLowTemp = user.isLowTempNotification() && weatherData.getTemp() < -5;

        return notifyForRain || notifyForSnow || notifyForWind || notifyForHighTemp || notifyForLowTemp;
    }

    private String generateMessage(WeatherData weatherData) {
        return "Current weather: " + weatherData.getMain() + ", " + weatherData.getDescription();
    }
}
