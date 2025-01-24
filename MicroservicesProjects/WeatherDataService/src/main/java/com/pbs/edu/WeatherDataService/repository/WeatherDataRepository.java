package com.pbs.edu.WeatherDataService.repository;

import com.pbs.edu.WeatherDataService.model.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {
    List<WeatherData> findByCityNameAndCountry(String city, String country);
}
