package com.jrescalona.rainalertserver.doa;

import com.jrescalona.rainalertserver.mapper.ProjectMapper;
import com.jrescalona.rainalertserver.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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

    /**
     * Select project by project id
     * @param id project id
     * @return Project if project exists, null otherwise
     */
    @Override
    public Optional<Project> selectProjectById(UUID id) {
        String sql = "SELECT " +
                        "p.id as project_id," +
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
                    "ON a.id = p.address_id\n" +
                    "WHERE p.id = ?";
        Project foundProject = jdbcTemplate.queryForObject(sql, new ProjectMapper(), id);
        return Optional.ofNullable(foundProject);
    }
    /**
     * Returns all projects in the database
     * Do not expose to the public
     * Used for testing purposes
     * @return List<Projects>
     */
    @Override
    public List<Project> selectAllProjects() {
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
                    "ON a.id = p.address_id";
        return jdbcTemplate.query(sql, new ProjectMapper());
    }

    /**
     * Selects all projects associated with user
     * @param id user's id
     * @return List<Projects> list of all projects by user
     */
    @Override
    public List<Project> selectAllProjectsByUserId(UUID id) {
        // Get list of project ids associated with user
        String sql = "SELECT id FROM project WHERE user_id = ?";
        List<UUID> projectIds = jdbcTemplate.query(sql, (resultSet, i) -> UUID.fromString(resultSet.getString("id")) ,id);

        // Add projects
        List<Project> projects = new ArrayList<>();
        for (UUID uuid : projectIds)
            selectProjectById(uuid)
                    .ifPresent(projects::add);

        return projects;
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
