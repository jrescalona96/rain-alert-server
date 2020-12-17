package com.jrescalona.rainalertserver.mapper;

import com.jrescalona.rainalertserver.model.Location;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class LocationMapper implements RowMapper<Location> {
    @Override
    public Location mapRow(ResultSet resultSet, int i) throws SQLException {
        UUID id = (UUID) resultSet.getObject("location_id");
        String gridID = resultSet.getString("grid_id");
        int gridX = resultSet.getInt("grid_x");
        int gridY = resultSet.getInt("grid_y");
        double longitude = resultSet.getDouble("longitude");
        double latitude = resultSet.getDouble("latitude");
        return new Location(id, gridID, gridX, gridY, longitude, latitude);
    }
}
