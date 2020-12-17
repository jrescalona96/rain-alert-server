package com.jrescalona.rainalertserver.dao;

import com.jrescalona.rainalertserver.mapper.UserMapper;
import com.jrescalona.rainalertserver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PostgresUserDao implements IUserDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PostgresUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertUser(UUID id, User user) {
        return 0;
    }

    @Override
    public Optional<User> selectUserById(UUID id) {
        return Optional.empty();
    }

    @Override
    public List<User> selectAllUsers() {
        final String SQL = "SELECT * FROM users";
        List<User> users = jdbcTemplate.query(SQL, new UserMapper());
        return null;
    }

    @Override
    public int updateUserById(UUID id, User user) {
        return 0;
    }

    @Override
    public int deleteUserById(UUID id) {
        return 0;
    }
}
