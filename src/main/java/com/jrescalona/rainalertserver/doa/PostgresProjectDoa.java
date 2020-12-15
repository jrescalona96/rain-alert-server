package com.jrescalona.rainalertserver.doa;

import com.jrescalona.rainalertserver.mapper.ProjectMapper;
import com.jrescalona.rainalertserver.model.Address;
import com.jrescalona.rainalertserver.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository("PostgresProjectDoa")
public class PostgresProjectDoa implements IProjectsDoa {

    private final JdbcTemplate jdbcTemplate;
    private final IAddressDoa addressDoa;

    @Autowired
    public PostgresProjectDoa(JdbcTemplate jdbcTemplate, @Qualifier("PostgresAddressDoa") IAddressDoa addressDoa) {
        this.jdbcTemplate = jdbcTemplate;
        this.addressDoa = addressDoa;
    }

    /**
     * Insert new project to db
     * @param id project id
     * @param project new project to insert
     * @return 0 if successful, 1 otherwise
     */
    @Override
    public void insertProject(UUID id, Project project) throws RuntimeException {
        try {
            // set project id
            project.setId(id);
            // create address id
            // TODO: store new address to database if new
            UUID addressId = UUID.randomUUID();
            addressDoa.insertAddress(addressId, project.getAddress());
            String sql = "INSERT INTO project(id, user_id, name, description, address_id)" +
                        "VALUES(" +
                        "'" + id + "'," +
                        "'" + project.getUserId() + "'," +
                        "'" + project.getName() + "'," +
                        "'" + project.getDescription() + "'," +
                        "'" + addressId + "'" +
                        ")";
            jdbcTemplate.execute(sql);
        } catch (RuntimeException e) {
            System.err.println(e.getMessage());
            throw e;
        }
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
