package com.jrescalona.rainalertserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.UUID;

public class Location implements Serializable {
    private UUID id;
    private final String gridId;
    private final int gridX;
    private final int gridY;
    private final double longitude;
    private final double latitude;

    public Location(
            @JsonProperty UUID id,
            @JsonProperty("gridId")  String gridId,
            @JsonProperty("gridX")  int gridX,
            @JsonProperty("gridY")  int gridY,
            @JsonProperty("longitude")  double longitude,
            @JsonProperty("latitude")  double latitude) {
        this.id = id;
        this.gridId = gridId;
        this.gridX = gridX;
        this.gridY = gridY;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Location(
            @JsonProperty("gridId") String gridId,
            @JsonProperty("gridX") int gridX,
            @JsonProperty("gridY") int gridY,
            @JsonProperty("longitude") double longitude,
            @JsonProperty("latitude") double latitude) {
        this.gridId = gridId;
        this.gridX = gridX;
        this.gridY = gridY;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Location() {
        gridId = null;
        gridX = 0;
        gridY = 0;
        longitude = 0;
        latitude = 0;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getGridId() {
        return gridId;
    }

    public int getGridX() {
        return gridX;
    }

    public int getGridY() {
        return gridY;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    /**
     * Checks equality of field values
     * @param location
     * @return true if all fields are the same, false otherwise
     */
    public boolean equals(Location location) {
        return gridId.equals(location.getGridId())
                && gridX == location.getGridX()
                && gridY == location.getGridY()
                && longitude == location.getLongitude()
                && latitude == location.getLatitude();
    }

    @Override
    public String toString() {
        return "\n Location { " +
                "id = " + id +
                ", gridId = '" + gridId + '\'' +
                ", gridX = " + gridX +
                ", gridY = " + gridY +
                ", longitude = " + longitude +
                ", latitude = " + latitude +
                "}";
    }
}
