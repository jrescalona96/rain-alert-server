package com.jrescalona.rainalertserver.doa;

import com.jrescalona.rainalertserver.mapper.ProjectMapper;
import com.jrescalona.rainalertserver.model.Address;
import com.jrescalona.rainalertserver.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.sql.PreparedStatement;
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
            // create address id
            // TODO: store new address to database if new
            UUID addressId = UUID.randomUUID();
            addressDoa.insertAddress(addressId, project.getAddress());
            final String SQL = "INSERT INTO project(id, user_id, name, description, address_id)" +
                                "VALUES(" +
                                "'" + id + "'," +
                                "'" + project.getUserId() + "'," +
                                "'" + project.getName() + "'," +
                                "'" + project.getDescription() + "'," +
                                "'" + addressId + "'" +
                                ")";
            jdbcTemplate.execute(SQL);
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
        final String SQL = "SELECT " +
                            "p.id as project_id," +
                            "p.name," +
                            "p.description," +
                            "a.id as address_id," +
                            "a.address_line1," +
                            "a.address_line2," +
                            "a.city," +
                            "a.state," +
                            "a.postal_code" +
                        "\nFROM project p" +
                        "\nJOIN address a" +
                        "\nON a.id = p.address_id" +
                        "\nWHERE p.id = ?";
        Project foundProject = jdbcTemplate.queryForObject(SQL, new ProjectMapper(), id);
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
        final String SQL = "SELECT p.id as project_id," +
                        "p.name," +
                        "p.description," +
                        "a.id as address_id," +
                        "a.address_line1," +
                        "a.address_line2," +
                        "a.city," +
                        "a.state," +
                        "a.postal_code" +
                    "\nFROM project p" +
                    "\nJOIN address a" +
                    "\nON a.id = p.address_id";
        return jdbcTemplate.query(SQL, new ProjectMapper());
    }

    /**
     * Selects all projects associated with user
     * @param id user's id
     * @return List<Projects> list of all projects by user
     */
    @Override
    public List<Project> selectAllProjectsByUserId(UUID id) {
        // Get list of project ids associated with user
        final String SQL = "SELECT id FROM project WHERE user_id = ?";
        List<UUID> projectIds = jdbcTemplate.query(SQL, (resultSet, i) -> UUID.fromString(resultSet.getString("id")) ,id);

        // Add projects
        List<Project> projects = new ArrayList<>();
        for (UUID uuid : projectIds) {
            selectProjectById(uuid).ifPresent(projects::add);
        }

        return projects;
    }

    /**
     * Update a project using project id
     * @param id project id
     * @param updateProject new project details
     * @return
     */
    @Override
    public int updateProjectById (UUID id, Project updateProject) {
        Optional<Project> project = selectProjectById(id);
        Optional<Address> address = addressDoa.selectAddressById( updateProject.getAddress().getId() );
        if(project.isPresent()) {
           final String SQL = "UPDATE project" +
                    "SET name = '" + updateProject.getName() + "'" +
                    "description = '" + updateProject.getDescription() + "'" +
                    "WHERE id = ?";
            jdbcTemplate.update(SQL, id);
            return 0;
        }
        return 1;
    }

    /**
     * Delete one project
     * @param id project id
     * @return
     */
    @Override
    public int deleteProjectById (UUID id) {
        String sql = "DELETE FROM project WHERE id = ? CASCADE";
        jdbcTemplate.update(sql, id);
        return 0;
    }

}
