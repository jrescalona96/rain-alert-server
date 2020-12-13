package com.jrescalona.rainalertserver.mapper;

import com.jrescalona.rainalertserver.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        UUID id = (UUID) resultSet.getObject("id");
        String fName = resultSet.getString("f_name");
        String lName = resultSet.getString("l_name");
        String role = resultSet.getString("role");
        String email = resultSet.getString("email");
        return new User(id, fName, lName, role, email);
    }
}
