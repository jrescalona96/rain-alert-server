package com.jrescalona.rainalertserver.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class WeatherForecastTest {

    UUID id = UUID.randomUUID();
    String description = "Sunny and Philadelphia";
    Precipitation precipitation = new Precipitation(11.11, 22.22);
    WeatherForecast wf = new WeatherForecast(id, description, precipitation);
    @Test
    void getId() {
        assertEquals(wf.getId(), id);
    }

    @Test
    void getDescription() {
        assertEquals(wf.getDescription(), description);
    }

    @Test
    void getPrecipitation() {
        Precipitation otherPrecipitation = new Precipitation(11.11,22.22);
        assertTrue(precipitation.equals(otherPrecipitation));
    }

    @Test
    void equalsSameParametersShouldReturnTrue() {
        WeatherForecast other = new WeatherForecast(id, description, precipitation);
        assertTrue(wf.equals(other));
    }

    @Test
    void equalsDiffIdShouldReturnTrue() {
        WeatherForecast other = new WeatherForecast(UUID.randomUUID(), description, precipitation);
        assertTrue(wf.equals(other));
    }

    @Test
    void equalsWithSameAddressOnlyShouldReturnFalse() {
        Precipitation p = new Precipitation(33.33,44.44);
        String d = "Rainy in Seattle";
        UUID id = UUID.randomUUID();
        WeatherForecast other = new WeatherForecast(id, d, p);
        assertFalse(wf.equals(other));
    }
}