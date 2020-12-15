package com.jrescalona.rainalertserver.doa;

import com.jrescalona.rainalertserver.mapper.ProjectMapper;
import com.jrescalona.rainalertserver.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("postgresProjectsDb")
public class PostgresProjectService implements IProjectsDoa {

    public final JdbcTemplate jdbcTemplate;

    @Autowired
    public PostgresProjectService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertProject(UUID projectId, Project project) {
        return 0;
    }

    @Override
    public Optional<Project> selectProjectById(UUID projectId) {
        return Optional.empty();
    }

    @Override
    public List<Project> selectAllProjects() {
        return null;
    }

    @Override
    public List<Project> selectProjectsByUserId(UUID userId) {
        String sql = "SELECT p.id as project_id," +
                    "p.name," +
                    "p.description," +
                    "a.id as address_id," +
                    "a.address_line1," +
                    "a.address_line2," +
                    "a.city," +
                    "a.state," +
                    "a.postal_code\n" +
                "FROM project p\n" +
                "JOIN address a\n" +
                    "ON p.user_id = ? AND a.id = p.address_id";
        return jdbcTemplate.query(sql, new ProjectMapper(), userId);
    }

    @Override
    public int updateProjectById (UUID projectId, Project project){
        return 0;
    }

    @Override
    public int deleteProjectById (UUID projectId){
        return 0;
    }

}
