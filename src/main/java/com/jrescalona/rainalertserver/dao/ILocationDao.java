package com.jrescalona.rainalertserver.dao;

import com.jrescalona.rainalertserver.model.Location;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ILocationDao {
    /**
     * Creates a random UUID
     * then inserts new location using UUID
     * Invokes overloaded method
     * @param location Location
     * @return 0 if successful, 1 otherwise
     */
    default int insertLocation(Location location) {
        UUID locationId = UUID.randomUUID();
        return insertLocation(locationId, location);
    }
    int insertLocation(UUID id, Location location);
    Optional<Location> selectLocationById(UUID id);
    List<Location> selectAllLocations();
    int updateLocationById(UUID id, Location location);
    int deleteLocationById(UUID id);
}
