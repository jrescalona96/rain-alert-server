package com.jrescalona.rainalertserver.doa;

import com.jrescalona.rainalertserver.model.WeatherForecast;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IWeatherForecastDoa {
    int insertWeatherForecast(WeatherForecast weatherForecast);
    int insertWeatherForecast(UUID id, WeatherForecast weatherForecast);
    Optional<WeatherForecast> selectWeatherForecastById(UUID id);
    List<WeatherForecast> selectAllWeatherForecasts();
    int updateWeatherForecastById(UUID id, WeatherForecast weatherForecast);
    int deleteWeatherForecastById(UUID id);
}
