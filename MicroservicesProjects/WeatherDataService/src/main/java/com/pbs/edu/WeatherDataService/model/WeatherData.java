package com.pbs.edu.WeatherDataService.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class WeatherData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Koordynaty
    private double lon;
    private double lat;

    // Główne dane pogodowe
    private double temp;
    private double feelsLike;
    private double tempMin;
    private double tempMax;
    private int pressure;
    private int humidity;

    // Wiatr
    private double windSpeed;
    private int windDeg;
    private double windGust;

    // Opis pogody
    private String main;
    private String description;

    // Inne dane
    private String country;
    private String cityName;

    // Dodatkowe dane
    private int clouds;
    private int visibility;
    private long sunrise;
    private long sunset;

    // Metody deserializacji danych JSON
    @JsonProperty("coord")
    public void setCoordinates(Coordinates coord) {
        this.lon = coord.lon;
        this.lat = coord.lat;
    }

    @JsonProperty("main")
    public void setMain(Main main) {
        this.temp = main.temp;
        this.feelsLike = main.feelsLike;
        this.tempMin = main.tempMin;
        this.tempMax = main.tempMax;
        this.pressure = main.pressure;
        this.humidity = main.humidity;
    }

    @JsonProperty("wind")
    public void setWind(Wind wind) {
        this.windSpeed = wind.speed;
        this.windDeg = wind.deg;
        this.windGust = wind.gust != null ? wind.gust : 0;
    }

    @JsonProperty("weather")
    public void setWeather(List<Weather> weather) {
        if (weather != null && !weather.isEmpty()) {
            this.main = weather.get(0).main;
            this.description = weather.get(0).description;
        }
    }

    @JsonProperty("sys")
    public void setSys(Sys sys) {
        this.country = sys.country;
        this.sunrise = sys.sunrise;
        this.sunset = sys.sunset;
    }

    @JsonProperty("name")
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @JsonProperty("clouds")
    public void setCloudsData(Clouds clouds) {
        this.clouds = clouds.all;
    }

    @JsonProperty("visibility")
    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    // Klasy wewnętrzne odpowiadające za dane zagnieżdżone w odpowiedzi JSON
    public static class Coordinates {
        public double lon;
        public double lat;
    }

    public static class Main {
        public double temp;
        @JsonProperty("feels_like") public double feelsLike;
        @JsonProperty("temp_min") public double tempMin;
        @JsonProperty("temp_max") public double tempMax;
        public int pressure;
        public int humidity;
    }

    public static class Wind {
        public double speed;
        public int deg;
        public Double gust;
    }

    public static class Weather {
        public String main;
        public String description;
    }

    public static class Sys {
        public String country;
        public long sunrise;
        public long sunset;
    }

    public static class Clouds {
        public int all;
    }
}
