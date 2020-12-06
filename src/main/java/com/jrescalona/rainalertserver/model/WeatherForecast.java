package com.jrescalona.rainalertserver.model;

import java.util.UUID;

public class WeatherForecast {
    private final UUID id;
    private final String description;
    private final Precipitation precipitation;

    public WeatherForecast(UUID id, String description, Precipitation precipitation) {
        this.id = id;
        this.description = description;
        this.precipitation = precipitation;
    }

    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Precipitation getPrecipitation() {
        return precipitation;
    }

    /**
     * Checks equality of field values
     * @param weatherForecast
     * @return true if all fields are the same, false otherwise
     */
    public boolean equals(WeatherForecast weatherForecast) {
        return description.equals(weatherForecast.getDescription())
                && precipitation.equals(weatherForecast.getPrecipitation());
    }
}
