package com.jrescalona.rainalertserver.mapper;

import com.jrescalona.rainalertserver.model.*;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ProjectMapper implements RowMapper<Project> {

    @Override
    public Project mapRow(ResultSet resultSet, int i) throws SQLException {

        // Project results
        UUID projectId = (UUID)resultSet.getObject("project_id");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");

        // Address results
        UUID addressId = (UUID)resultSet.getObject("address_id");
        String addressLine1 = resultSet.getString("address_line1");
        String addressLine2 = resultSet.getString("address_line2");
        String city = resultSet.getString("city");
        String state = resultSet.getString("state");
        String postalCode = resultSet.getString("postal_code");
        Address address = new Address(addressId, addressLine1, addressLine2, city, state, postalCode, null);

        return new Project(projectId, name, description, address);
    }
}
