package com.jrescalona.rainalertserver.dao;

import com.jrescalona.rainalertserver.model.WeatherForecast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PostgresWeatherForecastDao implements IWeatherForecastDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PostgresWeatherForecastDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertWeatherForecast(UUID weatherForecastId, WeatherForecast weatherForecast) {
        return 0;
    }

    @Override
    public Optional<WeatherForecast> selectWeatherForecastById(UUID weatherForecastId) {
        return Optional.empty();
    }

    @Override
    public List<WeatherForecast> selectAllWeatherForecastsByLocationId(UUID id) {
        return null;
    }

    @Override
    public int updateWeatherForecastById(UUID weatherForecastId, WeatherForecast weatherForecast) {
        return 0;
    }

    @Override
    public int deleteWeatherForecastById(UUID weatherForecastId) {
        return 0;
    }
}
