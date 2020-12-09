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


    Location l1 = new Location(id, gridId, gridX, gridY, longitude, latitude);
    Location l2 = new Location(id, gridId, gridX, gridY, longitude, latitude);
    Location l3 = new Location(UUID.randomUUID(), gridId, gridX, gridY, longitude, latitude);
    Location l4 = new Location(UUID.randomUUID(), "MAD", "401",  "60", "35.1234", "-113.1923");

    @Test
    void getId() {
        assertEquals(l1.getId(), id);
    }

    @Test
    void setId() {
        UUID expected = UUID.randomUUID();
        l1.setId(expected);
        assertEquals(expected, l1.getId());
    }

    @Test
    void getGridId() {
        assertEquals(l1.getGridId(), gridId);
    }

    @Test
    void getGridX() {
        assertEquals(l1.getGridX(), gridX);
    }

    @Test
    void getGridY() {
        assertEquals(l1.getGridY(), gridY);
    }

    @Test
    void equalsSameParametersShouldReturnTrue() {
            assertTrue(l1.equals(l2));
    }

    @Test
    void equalsSameIdShouldReturnTrue() {
        assertTrue(l1.equals(l3));
    }

    @Test
    void equalsShouldReturnFalse() {
        assertFalse(l1.equals(l4));
    }
}