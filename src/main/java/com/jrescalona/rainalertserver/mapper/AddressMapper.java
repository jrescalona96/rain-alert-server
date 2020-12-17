package com.jrescalona.rainalertserver.mapper;

import com.jrescalona.rainalertserver.model.Address;
import com.jrescalona.rainalertserver.model.Location;
import com.jrescalona.rainalertserver.model.USState;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class AddressMapper implements RowMapper<Address> {
    @Override
    public Address mapRow(ResultSet resultSet, int i) throws SQLException {
        // address mapping
        UUID id = (UUID) resultSet.getObject("address_id");
        String addressLine1 = resultSet.getString("address_line1");
        String addressLine2 = resultSet.getString("address_line2");
        String city = resultSet.getString("city");
        String state = resultSet.getString("state");
        String postalCode = resultSet.getString("postal_code");

        // location mapping
        UUID locationId = UUID.fromString(resultSet.getString("location_id"));
        String gridId = resultSet.getString("grid_id");
        int gridX = resultSet.getInt("grid_x");
        int gridY = resultSet.getInt("grid_y");
        double longitude = resultSet.getDouble("longitude");
        double latitude = resultSet.getDouble("latitude");

        return new Address(id, addressLine1, addressLine2, city, state, postalCode,
                new Location( locationId, gridId, gridX, gridY, longitude, latitude));
    }
}
