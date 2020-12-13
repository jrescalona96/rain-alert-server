package com.jrescalona.rainalertserver.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrecipitationTest {

    Precipitation precipitation = new Precipitation(0.0, 100.0);

    @Test
    void getPercentage() {
        assertEquals(precipitation.getPercentRainChance(), 0.0);
    }

    @Test
    void getQuantity() {
        assertEquals(precipitation.getQuantity(), 100.0);
    }

    @Test
    void testToString() {
        String s  = "Chance of 0.0% of about 100.0.";
        assertEquals(precipitation.toString(), s);
    }


    @Test
    void equalsSameParametersShouldReturnTrue() {
        Precipitation other = new Precipitation(0.0, 100.0);
        assertTrue(precipitation.equals(other));
    }

    @Test
    void equalsWithDiffQuantityShouldReturnFalse() {
        Precipitation other = new Precipitation(0.12, 100.0);
        assertFalse(precipitation.equals(other));
    }

    @Test
    void equalsWithDiffPercentageShouldReturnFalse() {
        Precipitation other = new Precipitation(0.0, 50.0);
        assertFalse(precipitation.equals(other));
    }

    @Test
    void setPercentRainChanceShouldSucceed() {
        Precipitation p = new Precipitation(50,0.6);
        p.setPercentRainChance(0.4);
        assertEquals(0.4, p.getPercentRainChance());
    }

    @Test
    void setQuantityShouldSucceed() {
        Precipitation p = new Precipitation(50,0.6);
        p.setQuantity(10.0);
        assertEquals(10.0, p.getQuantity());
    }
}