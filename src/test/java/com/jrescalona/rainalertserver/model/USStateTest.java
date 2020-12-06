package com.jrescalona.rainalertserver.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static com.jrescalona.rainalertserver.model.USState.*;

class USStateTest {
    USState california = CA;
    USState colorado = CO;
    USState maryland = MD;

    @Test
    void name() {
        assertEquals(maryland.name(), "MD");
        assertEquals(colorado.name(), "CO");
        assertEquals(california.name(), "CA");
    }

    @Test
    void getFullName() {
        assertEquals(maryland.getFullName(), "Maryland");
        assertEquals(colorado.getFullName(), "Colorado");
        assertEquals(california.getFullName(), "California");
    }
}