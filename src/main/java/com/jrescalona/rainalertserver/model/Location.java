package com.jrescalona.rainalertserver.model;

import java.util.UUID;

public class Location {
    private UUID id;
    private final String gridId;
    private final String gridX;
    private final String gridY;
    private final String longitude;
    private final String latitude;

    public Location(UUID id, String gridId, String gridX, String gridY, String longitude, String latitude) {
        this.id = id;
        this.gridId = gridId;
        this.gridX = gridX;
        this.gridY = gridY;
        this.longitude = longitude;
        this.latitude = latitude;
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

    public String getGridX() {
        return gridX;
    }

    public String getGridY() {
        return gridY;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    /**
     * Checks equality of field values
     * @param location
     * @return true if all fields are the same, false otherwise
     */
    public boolean equals(Location location) {
        return gridId.equals(location.getGridId())
                && gridX.equals(location.getGridX())
                && gridY.equals(location.getGridY());
    }

}
