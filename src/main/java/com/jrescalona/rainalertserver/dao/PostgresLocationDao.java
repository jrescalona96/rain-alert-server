package com.jrescalona.rainalertserver.dao;

import com.jrescalona.rainalertserver.mapper.LocationMapper;
import com.jrescalona.rainalertserver.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
    public int insertLocation(UUID id, Location location) {
        final String  INSERT_SQL= "INSERT INTO location(id, grid_id, grid_x, grid_y, longitude, latitude)" +
                        "VALUES(" +
                        "'" + id + "'," +
                        "'" + location.getGridId() + "'," +
                            location.getGridX() + "," +
                            location.getGridY() + "," +
                            location.getLongitude() + "," +
                            location.getLatitude() +
                        ")";
        jdbcTemplate.execute(INSERT_SQL);
        return 0;
    }

    @Override
    public Optional<Location> selectLocationById(UUID id) {
        final String SELECT_SQL = "SELECT * FROM location WHERE id = ?";
        Location location = jdbcTemplate.queryForObject(SELECT_SQL, new LocationMapper(), id);
        return Optional.ofNullable(location);
    }

    @Override
    public List<Location> selectAllLocations() {
        return null;
    }

    @Override
    public int updateLocationById(UUID id, Location location) {
        return 0;
    }

    @Override
    public int deleteLocationById(UUID id) {
        return 0;
    }
}
