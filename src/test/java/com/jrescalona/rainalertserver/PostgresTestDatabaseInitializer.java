package com.jrescalona.rainalertserver;

import com.jrescalona.rainalertserver.dao.PostgresLocationDao;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;

public class PostgresTestDatabaseInitializer {

    private final JdbcTemplate jdbcTemplate;

    public PostgresTestDatabaseInitializer() {
        this.jdbcTemplate =  new JdbcTemplate(
                DataSourceBuilder
                        .create()
                        .type(HikariDataSource.class)
                        .url("jdbc:postgresql://localhost:5432/rain_alert_db_test")
                        .username("postgres")
                        .password("dbfortesting")
                        .build()
        );
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
