package com.pbs.edu.WeatherDataService.repository;

import com.pbs.edu.WeatherDataService.model.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {

}
