package com.jrescalona.rainalertserver.service;

import com.jrescalona.rainalertserver.doa.ILocationDoa;
import com.jrescalona.rainalertserver.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LocationService {
    private final ILocationDoa locationDoa;

    @Autowired
    public LocationService(@Qualifier("PostgresLocationDoa") ILocationDoa locationDoa) {
        this.locationDoa = locationDoa;
    }

    /**
     * Adds a new address
     * @param address Location
     */
    public void addLocation(Location address) throws RuntimeException {
        locationDoa.insertLocation(address);
    }

    /**
     * Gets all available projects
     * @return List<Location>
     */
    public List<Location> getAllLocations() {
        return locationDoa.selectAllLocations();
    }

    /**
     * Gets project with given id
     * @param id UUID
     * @return Location if found, null otherwise
     */
    public Location getLocationById(UUID id) {
        return locationDoa.selectLocationById(id).orElse(null);
    }

    /**
     * Update a project with new project instance using id
     * @param id UUID
     * @param address Location
     * @return 0 if successful, 1 otherwise
     */
    public int updateLocationById(UUID id, Location address) {
        return locationDoa.updateLocationById(id, address);
    }

    /**
     * Delete a project using id
     * @param id UUID
     * @return 0 if successful, 1 otherwise
     */
    public int deleteLocationById(UUID id) {
        return locationDoa.deleteLocationById(id);
    }

}
