package com.jrescalona.rainalertserver.model;

import java.util.UUID;

public class Location {
    private UUID id;
    private final String gridId;
    private final int gridX;
    private final int gridY;
    private final double longitude;
    private final double latitude;

    public Location(
            UUID id,
            String gridId,
            int gridX,
            int gridY,
            double longitude,
            double latitude) {
        this.id = id;
        this.gridId = gridId;
        this.gridX = gridX;
        this.gridY = gridY;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Location(
            String gridId,
            int gridX,
            int gridY,
            double longitude,
            double latitude) {
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
                && gridY == location.getGridY();
    }

}
