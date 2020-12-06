package com.jrescalona.rainalertserver.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    String gridId  = "LOX";
    String gridX = "432";
    String gridY = "10";
    String longitude = "34.0589";
    String latitude = "-117.8194";
    UUID locationGridId = UUID.randomUUID();
    Location locationGrid = new Location(
            locationGridId,
            gridId,
            gridX,
            gridY,
            longitude,
            latitude);

    String addressLine1 = "1234 Five Six Ave.";
    String addressLine2 = "Apt. 7";
    String city = "Eight Heights";
    String state = "NY";
    String postalCode = "98765";

    UUID id = UUID.randomUUID();
    Address address = new Address(
            id,
            addressLine1,
            addressLine2,
            city,
            state,
            postalCode,
            locationGrid);;

    @Test
    void getId() {
        assertEquals(address.getId(), id);
    }

    @Test
    void getAddressLine1() {
        assertEquals(address.getAddressLine1(), addressLine1);
    }

    @Test
    void getAddressLine2() {
        assertEquals(address.getAddressLine2(), addressLine2);
    }

    @Test
    void getCity() {
        assertEquals(address.getCity(), city);
    }

    @Test
    void getState() {
        assertEquals(address.getState(), state);
    }

    @Test
    void getPostalCode() {
        assertEquals(address.getPostalCode(), postalCode);
    }

    @Test
    void getLocationGrid() {
        assertEquals(address.getLocation(), locationGrid);
    }

    @Test
    void testToString() {
        String fullAddress = "1234 Five Six Ave., Apt. 7, Eight Heights, NY 98765";
        assertEquals(address.toString(), fullAddress);
    }

    @Test
    void equalsSameParametersShouldReturnTrue() {
        Address otherAddress = new Address(
                id,
                addressLine1,
                addressLine2,
                city,
                state,
                postalCode,
                locationGrid);

        assertTrue(address.equals(otherAddress));
    }

    @Test
    void equalsDiffIdShouldReturnTrue() {
        Address otherAddress = new Address(
                UUID.randomUUID(),
                addressLine1,
                addressLine2,
                city,
                state,
                postalCode,
                locationGrid);
        assertTrue(address.equals(otherAddress));
    }

    @Test
    void equalsWithSameLocationGridOnlyShouldReturnFalse() {
        Address otherAddress = new Address(
                UUID.randomUUID(),
                "7834 Nowhere St.",
                "Suite 1902",
                "Los Angeles",
                "CA",
                "90045",
                locationGrid);
        assertFalse(address.equals(otherAddress));
    }
}