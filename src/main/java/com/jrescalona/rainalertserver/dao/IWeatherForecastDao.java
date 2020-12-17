package com.jrescalona.rainalertserver.dao;

import com.jrescalona.rainalertserver.model.WeatherForecast;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IWeatherForecastDao {
    /**
     * Creates a random UUID
     * then inserts new weatherForecast using UUID
     * Invokes overloaded method
     * @param weatherForecast WeatherForecast
     * @return 0 if successful, 1 otherwise
     */
    default int insertWeatherForecast(WeatherForecast weatherForecast) {
        UUID id = UUID.randomUUID();
        return insertWeatherForecast(id, weatherForecast);
    }
    int insertWeatherForecast(UUID weatherForecastId, WeatherForecast weatherForecast);
    Optional<WeatherForecast> selectWeatherForecastById(UUID weatherForecastId);
    List<WeatherForecast> selectAllWeatherForecastsByLocationId(UUID id);
    int updateWeatherForecastById(UUID weatherForecastId, WeatherForecast weatherForecast);
    int deleteWeatherForecastById(UUID weatherForecastId);
}
