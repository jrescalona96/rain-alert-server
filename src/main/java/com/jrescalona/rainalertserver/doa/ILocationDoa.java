package com.jrescalona.rainalertserver.doa;

import com.jrescalona.rainalertserver.model.Location;
import com.jrescalona.rainalertserver.model.Project;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ILocationDoa {
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
