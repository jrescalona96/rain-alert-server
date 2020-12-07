package com.jrescalona.rainalertserver.doa;

import com.jrescalona.rainalertserver.model.Location;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ILocationDoa {
    int insertLocation(Location location);
    int insertLocation(UUID id, Location location);
    Optional<Location> selectLocationById(UUID id);
    List<Location> selectAllLocations();
    int updateLocationById(UUID id, Location location);
    int deleteLocationById(UUID id);
}
