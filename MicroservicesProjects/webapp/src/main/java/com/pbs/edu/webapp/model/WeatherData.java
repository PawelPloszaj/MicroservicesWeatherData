package com.pbs.edu.webapp.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class WeatherData {

    private Long id;

    private double lon;
    private double lat;

    private double temp;
    private double feelsLike;
    private double tempMin;
    private double tempMax;
    private int pressure;
    private int humidity;

    private double windSpeed;
    private int windDeg;
    private double windGust;

    private String main;
    private String description;

    private String country;
    private String cityName;

    private int clouds;
    private int visibility;
    private long sunrise;
    private long sunset;

    private LocalDateTime date;
}

