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
        Address address = new AddressMapper().mapRow(resultSet,i);

        return new Project(projectId, name, description, address);
    }
}
