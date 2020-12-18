
package com.jrescalona.rainalertserver.dao;

import com.jrescalona.rainalertserver.PostgresTestDatabaseInitializer;
import com.jrescalona.rainalertserver.datasource.PostgresDataSource;
import com.jrescalona.rainalertserver.model.Address;
import com.jrescalona.rainalertserver.model.Location;
import com.jrescalona.rainalertserver.model.Project;
import com.jrescalona.rainalertserver.model.User;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class PostgresProjectDaoTest {



    JdbcTemplate jdbcTemplate = new PostgresTestDatabaseInitializer().getJdbcTemplate();
    PostgresProjectDao projectDao = new PostgresProjectDao(
                                        jdbcTemplate, new PostgresAddressDao(
                                                jdbcTemplate, new PostgresLocationDao(jdbcTemplate)));

    User u1 = new User(UUID.randomUUID(), "John", "Escalona", "PM", "johnrichmondescalona@gmail.com", "test1");
    User u2 = new User(UUID.randomUUID(), "Jo Ann", "Cacho", "PM", "joann@yahoo.com", "test2");
    Location l1 = new Location(UUID.randomUUID(), "DAS", 48, 8, -261.253486, -196.207582);
    Location l2 = new Location(UUID.randomUUID(), "UED", 93, 51, 355.769283, -234.434096);
    Location l3 = new Location(UUID.randomUUID(), "CCL", 55, 10, -185.751602, 349.972757);
    Location l4 = new Location(UUID.randomUUID(), "USA", 1, 1, -200.000000, 500.000000);
    Address a1 = new Address(UUID.randomUUID(),"33243 Vahlen Drive", null,"Clearwater", "FL", "33763", l1);
    Address a2 = new Address(UUID.randomUUID(),"19733 Katie Crossing", null,"New Orleans", "LA", "70187", l2);
    Address a3 = new Address(UUID.randomUUID(),"96301 Twin Pines Road", null,"Milwaukee", "WI", "53277", l3);
    Address a4 = new Address(UUID.randomUUID(),"1111 Single Pine2 Road", "Apt. N","Los Angeles", "CA", "90045", l3);
    Project p1 = new Project(UUID.randomUUID(),"Fundamental client-driven time-frame","Profound non-volatile approach", a1);
    Project p2 = new Project(UUID.randomUUID(),"Team-oriented bandwidth-monitored challenge","Optimized client-driven focus group", a2);
    Project p3 = new Project(UUID.randomUUID(),"Cross-group heuristic focus group","Object-based fault-tolerant Graphic Interface", a3);
    Project p4 = new Project(UUID.randomUUID(),"NEW PROJECT TO BE ADDED/DELETED","Describing the new project that was added/removed", a4);

    List<Project> projects = new ArrayList<>(Arrays.asList(p1,p2,p3));
    List<Address> addresses = new ArrayList<>(Arrays.asList(a1,a2,a3));
    List<User> users = new ArrayList<>(Arrays.asList(u1,u2));
    List<Location> locations  = new ArrayList<>(Arrays.asList(l1,l2,l3));


    @BeforeEach
    void setUp() {
        PostgresLocationDao locationDao = new PostgresLocationDao(jdbcTemplate);
        PostgresAddressDao addressDao = new PostgresAddressDao(jdbcTemplate, locationDao);
        projectDao = new PostgresProjectDao(jdbcTemplate, addressDao);


        // insert users
        for (User u : users) {
            String sql = "INSERT INTO users(id, f_name, l_name, email, password, role) " +
                    "VALUES(" +
                    "'" + u.getId() + "'," +
                    "'" + u.getFName() + "'," +
                    "'" + u.getLName() + "'," +
                    "'" + u.getEmail() + "'," +
                    "'testing'," +
                    "'" + u.getRole() + "'" +
                    ")";
            jdbcTemplate.execute(sql);
        }

        // insert locations
        for (Location l : locations) {
            String sql = "INSERT INTO location(id, grid_id, grid_x, grid_y, longitude, latitude) " +
                    "VALUES(" +
                    "'" + l.getId() + "'," +
                    "'" + l.getGridId() + "'," +
                    "'" + l.getGridX() + "'," +
                    "'" + l.getGridY()+ "'," +
                    "'" + l.getLongitude() + "'," +
                    "'" + l.getLatitude() + "'" +
                    ")";
            jdbcTemplate.execute(sql);
        }

        // insert addresses
        for (Address a : addresses) {
            String sql = "INSERT INTO address(id, location_id, address_line1, address_line2, city, state, postal_code) " +
                    "VALUES(" +
                    "'" + a.getId() + "'," +
                    "'" + a.getLocation().getId() + "'," +
                    "'" + a.getAddressLine1() + "'," +
                    "'" + a.getAddressLine2() + "'," +
                    "'" + a.getCity() + "'," +
                    "'" + a.getState() + "'," +
                    "'" + a.getPostalCode() + "'" +
                    ")";
            jdbcTemplate.execute(sql);
        }

        // insert projects
        for (Project p : projects) {
            /**
             * P1 & P2 => u1
             * P3 => u2
             */
            UUID user_id = projects.indexOf(p) == 0 || projects.indexOf(p) == 1 ? u1.getId() : u2.getId();
            String sql = "INSERT INTO project(id, user_id, address_id, name, description) " +
                    "VALUES(" +
                    "'" + p.getId() + "'," +
                    "'" + user_id + "'," +
                    "'" + p.getAddress().getId() + "'," +
                    "'" + p.getName() + "'," +
                    "'" + p.getDescription() + "'" +
                    ")";
            jdbcTemplate.execute(sql);
        }
    }

    @AfterEach
    void tearDown() {

        // delete projects
        final String SQL_DELETE_PROJECTS = "DELETE FROM project";
        jdbcTemplate.execute(SQL_DELETE_PROJECTS);

        // delete users
        final String SQL_DELETE_USERS = "DELETE FROM users";
        jdbcTemplate.execute(SQL_DELETE_USERS);


        // delete addresses
        final String SQL_DELETE_ADDRESSES = "DELETE FROM address";
        jdbcTemplate.execute(SQL_DELETE_ADDRESSES);

        // delete locations
        final String SQL_DELETE_LOCATIONS = "DELETE FROM location";
        jdbcTemplate.execute(SQL_DELETE_LOCATIONS);

        printAll();
    }

    @Test
    void insertProjectShouldInsertProject() {
        projectDao.insertProject(p4);
        Project p  = projectDao.selectProjectById(p4.getId()).orElse(null);
        assertNotNull(p);
        assertEquals(p4.getId(), p.getId());
    }

    @Test
    void selectProjectByIdShouldHaveTheCorrectProjectId() {
        UUID expectedId = p1.getId();
        Project result = projectDao.selectProjectById(expectedId).orElse(null);
        assertTrue(result.equals(p1));
    }

    @Test
    void selectAllProjectsShouldReturnThreeProjects() {
        List<Project> results = projectDao.selectAllProjects();
        assertEquals(3, results.size());
    }

    @Test
    void selectProjectsByUserIdShouldReturnTwoProjects() {
        UUID userId = u1.getId();
        List<Project> results = projectDao.selectAllProjectsByUserId(userId);
        assertEquals(2, results.size());
        assertEquals(p1.getId(), results.get(0).getId());
        assertEquals(p2.getId(), results.get(1).getId());
    }

    @Test
    void updateProjectByIdShouldUpdateProjectName() {
        Project update = new Project(p1.getId(),"UPDATED NAME",p1.getDescription(), p1.getAddress());
        projectDao.updateProjectById(p1.getId(), update);
        Project p1Updated = projectDao.selectProjectById(p1.getId()).orElse(null);
        assertNotEquals(p1.getName(), p1Updated.getName());
    }

    @Test
    void updateProjectByIdShouldUpdateProjectDescription() {
        Project update = new Project(p1.getId(),p1.getName(),"UPDATED DESCRIPTION", p1.getAddress());
        projectDao.updateProjectById(p1.getId(), update);
        Project p1Updated = projectDao.selectProjectById(p1.getId()).orElse(null);
        assertNotEquals(p1.getDescription(), p1Updated.getDescription());
    }

    @Test
    void deleteProjectByIdShouldDeleteProject() {
        projectDao.deleteProjectById(p1.getId());
        assertNull(projectDao.selectProjectById(p1.getId()));
    }

    @Test
    void deleteProjectByIdShouldNotDeleteAnyProject() {
        projectDao.deleteProjectById(UUID.randomUUID());
        assertEquals(3, projectDao.selectAllProjects().size());
    }

    void printAll() {
        List<Project> projects = projectDao.selectAllProjects();
        for (Project p : projects) {
            System.out.println("\n================================================================");
            System.out.println("Printing results:");
            System.out.println("Project Id : " + p.getId().toString());
            System.out.println("Project Name : " + p.getName());
            System.out.println("Project Description" + p.getDescription());
            System.out.println("Project Address : " + p.getAddress().toString());
            System.out.println("================================================================\n");
        }
    }
}