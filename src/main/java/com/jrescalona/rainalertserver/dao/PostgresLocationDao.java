package com.jrescalona.rainalertserver.dao;

import com.jrescalona.rainalertserver.mapper.LocationMapper;
import com.jrescalona.rainalertserver.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("PostgresLocationDao")
public class PostgresLocationDao implements ILocationDao {
    public final JdbcTemplate jdbcTemplate;

    @Autowired
    public PostgresLocationDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertLocation(UUID locationId, Location location) {
        final String INSERT_SQL = "INSERT INTO location(" +
                                        "id, " +
                                        "grid_id, " +
                                        "grid_x, " +
                                        "grid_y, " +
                                        "longitude, " +
                                        "latitude) " +
                                    "VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(
                INSERT_SQL,
                locationId,
                location.getGridId(),
                location.getGridX(),
                location.getGridY(),
                location.getLongitude(),
                location.getLatitude());

        return 0;
    }

    /**
     * Finds a location by id,
     * @param id
     * @return Optional<Location>
     * @throws EmptyResultDataAccessException
     */
    @Override
    public Optional<Location> selectLocationById(UUID id) throws EmptyResultDataAccessException {
        final String SELECT_SQL = "SELECT " +
                                    "id as location_id, " +
                                    "grid_id, " +
                                    "grid_x, " +
                                    "grid_y, " +
                                    "longitude, " +
                                    "latitude " +
                                "FROM location " +
                                "WHERE id = ?";
        Location location = jdbcTemplate.queryForObject(SELECT_SQL, new LocationMapper(), id);
        return Optional.ofNullable(location);
    }

    @Override
    public List<Location> selectAllLocations() {
        final String SELECT_SQL = "SELECT " +
                                    "id as location_id, " +
                                    "grid_id, " +
                                    "grid_x, " +
                                    "grid_y, " +
                                    "longitude, " +
                                    "latitude " +
                                "FROM location";
        return jdbcTemplate.query(SELECT_SQL, new LocationMapper());

    }

    @Override
    public List<Location> selectAllLocationsByValues(Location location) {
        final String FIND_SQL = "SELECT id FROM location " +
                "WHERE " +
                    "grid_id = ?"+
                    "\nAND grid_x = ?" +
                    "\nAND grid_y = ?" +
                    "\nAND longitude = ?" +
                    "\nAND latitude = ?";
        List<Location> locations = new ArrayList<>();
        List<UUID> ids = jdbcTemplate.query(
                FIND_SQL,
                (resultSet, i) -> UUID.fromString(resultSet.getString("id")),
                location.getGridId(),
                location.getGridX(),
                location.getGridY(),
                location.getLongitude(),
                location.getLatitude());
        for(UUID id : ids) {
            locations.add(selectLocationById(id).orElse(null));
        }

        return locations;
    }

    @Override
    public int updateLocationById(UUID id, Location updateLocation) {
        final String UPDATE_SQL = "UPDATE location " +
                "SET " +
                    "grid_id = ?, "+
                    "grid_x = ?, " +
                    "grid_y = ? ," +
                    "longitude = ? ," +
                    "latitude = ? " +
                "WHERE id = ?";
        jdbcTemplate.update(
                UPDATE_SQL,
                updateLocation.getGridId(),
                updateLocation.getGridX(),
                updateLocation.getGridY(),
                updateLocation.getLongitude(),
                updateLocation.getLatitude(),
                id);
        return 0;
    }

    @Override
    public int deleteLocationById(UUID id) {
        final String DELETE_SQL = "DELETE FROM location CASCADE WHERE id = ? ";
        jdbcTemplate.update(DELETE_SQL, id);
        return 0;
    }

    @Override
    public boolean isExisting(Location location) {
        List<Location> locations = selectAllLocationsByValues(location);
        return locations.size() > 0;
    }
}
