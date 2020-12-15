package com.jrescalona.rainalertserver.mapper;

import com.jrescalona.rainalertserver.model.Location;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class LocationMapper implements RowMapper<Location> {
    @Override
    public Location mapRow(ResultSet resultSet, int i) throws SQLException {
        UUID id = (UUID) resultSet.getObject("id");
        String gridID = resultSet.getString("gridId");
        int gridX = resultSet.getInt("gridX");
        int gridY = resultSet.getInt("gridY");
        double longitude = resultSet.getDouble("longitude");
        double latitude = resultSet.getDouble("latitude");
        return new Location(id, gridID, gridX, gridY, longitude, latitude);
    }
}
