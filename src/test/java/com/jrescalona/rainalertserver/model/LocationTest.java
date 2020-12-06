package com.jrescalona.rainalertserver.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class LocationTest {

    UUID id = UUID.randomUUID();
    String gridId = "LOX";
    String gridX = "123";
    String gridY = "45";
    String longitude = "34.0589";
    String latitude = "-117.8194";


    Location lg1 = new Location(id, gridId, gridX, gridY, longitude, latitude);
    Location lg2 = new Location(id, gridId, gridX, gridY, longitude, latitude);
    Location lg3 = new Location(UUID.randomUUID(), gridId, gridX, gridY, longitude, latitude);
    Location lg4 = new Location(UUID.randomUUID(), "MAD", "401",  "60", "35.1234", "-113.1923");

    @Test
    void getId() {
        assertEquals(lg1.getId(), id);
    }

    @Test
    void getGridId() {
        assertEquals(lg1.getGridId(), gridId);
    }

    @Test
    void getGridX() {
        assertEquals(lg1.getGridX(), gridX);
    }

    @Test
    void getGridY() {
        assertEquals(lg1.getGridY(), gridY);
    }

    @Test
    void equalsSameParametersShouldReturnTrue() {
            assertTrue(lg1.equals(lg2));
    }

    @Test
    void equalsSameIdShouldReturnTrue() {
        assertTrue(lg1.equals(lg3));
    }

    @Test
    void equalsShouldReturnFalse() {
        assertFalse(lg1.equals(lg4));
    }
}