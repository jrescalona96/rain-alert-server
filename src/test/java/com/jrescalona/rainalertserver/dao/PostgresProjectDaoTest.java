package com.jrescalona.rainalertserver.dao;

import com.jrescalona.rainalertserver.datasource.PostgresDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import static org.junit.jupiter.api.Assertions.*;

class PostgresProjectDaoTest {

    private DataSource dataSource = new PostgresDataSource().hikariDataSource();
    private JdbcTemplate jdbcTemplate;

    @Test
    void insertProject() {
    }

    @Test
    void selectProjectById() {
    }

    @Test
    void selectAllProjects() {
    }

    @Test
    void selectAllProjectsByUserId() {
    }

    @Test
    void updateProjectById() {
    }

    @Test
    void deleteProjectById() {
    }
}