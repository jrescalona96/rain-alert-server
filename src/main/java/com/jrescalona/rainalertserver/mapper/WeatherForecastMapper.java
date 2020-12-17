package com.jrescalona.rainalertserver.mapper;

import com.jrescalona.rainalertserver.model.Precipitation;
import com.jrescalona.rainalertserver.model.WeatherForecast;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class WeatherForecastMapper implements RowMapper<WeatherForecast> {
    @Override
    public WeatherForecast mapRow(ResultSet resultSet, int i) throws SQLException {
        UUID id = (UUID) resultSet.getObject("weather_forecast_id");
        String description = resultSet.getString("description");
        double rainQuantity = resultSet.getDouble("rain_quantity");
        double rainPercentage = resultSet.getDouble("rain_percentage");
        Precipitation precipitation = new Precipitation(rainPercentage, rainQuantity);
        return new WeatherForecast(id, description, precipitation);
    }
}
