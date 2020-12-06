package com.jrescalona.rainalertserver.model;

import java.util.UUID;

public class Address {

    private final UUID id;
    private final String addressLine1;
    private final String addressLine2;
    private final String city;
    private final String state;
    private final String postalCode;
    private final Location location;

    public Address(UUID id,
                   String addressLine1,
                   String addressLine2,
                   String city,
                   String state,
                   String postalCode,
                   Location location) {
        this.id = id;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.location = location;
    }

    public UUID getId() {
        return id;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public Location getLocation() {
        return location;
    }

    /**
     * Checks equality of field values
     * @param address : Address
     * @return true if all fields are the same, false otherwise
     */
    public boolean equals(Address address) {
        return  addressLine1.equals(address.addressLine1)
                && addressLine2.equals(address.addressLine2)
                && city.equals(address.city)
                && state.equals(address.state)
                && postalCode.equals(address.postalCode)
                && location.equals(location);
    }

    public String toString() {
        String street = String.format("%s, %s",addressLine1, addressLine2);
        return String.format("%s, %s, %s %s", street, city, state, postalCode);
    }

}
