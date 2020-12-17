package com.jrescalona.rainalertserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.UUID;

public class Address implements Serializable {
    private UUID id;
    private final String addressLine1;
    private final String addressLine2;
    private final String city;
    private final String state;
    private final String postalCode;
    private final Location location;

    public Address(UUID id,
                   @JsonProperty("addressLine1") String addressLine1,
                   @JsonProperty("addressLine2") String addressLine2,
                   @JsonProperty("city") String city,
                   @JsonProperty("state") String state,
                   @JsonProperty("postalCode") String postalCode,
                   @JsonProperty("location") Location location) {
        this.id = id;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.location = location;
    }

    public Address(@JsonProperty("addressLine1") String addressLine1,
                   @JsonProperty("addressLine2") String addressLine2,
                   @JsonProperty("city") String city,
                   @JsonProperty("state") String state,
                   @JsonProperty("postalCode") String postalCode,
                   @JsonProperty("location") Location location) {
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

    public void setId(UUID id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "\n Address { " +
                "id = " + id +
                ", addressLine1 = '" + addressLine1 + '\'' +
                ", addressLine2 = '" + addressLine2 + '\'' +
                ", city = '" + city + '\'' +
                ", state = '" + state + '\'' +
                ", postalCode = '" + postalCode + '\'' +
                ", location = " + location +
                " }";
    }

}
