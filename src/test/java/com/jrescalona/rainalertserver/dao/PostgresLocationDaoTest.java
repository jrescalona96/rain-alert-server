package com.jrescalona.rainalertserver.dao;

import com.jrescalona.rainalertserver.model.Location;
import com.jrescalona.rainalertserver.PostgresTestDatabaseInitializer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class PostgresLocationDaoTest {

    // connect to db
    JdbcTemplate jdbcTemplate = new PostgresTestDatabaseInitializer().getJdbcTemplate();
    ILocationDao locationDao = new PostgresLocationDao(jdbcTemplate);

    Location l1 = new Location(UUID.randomUUID(), "DAS", 48, 8, -261.253486, -196.207582);
    Location l2 = new Location(UUID.randomUUID(), "UED", 93, 51, 355.769283, -234.434096);
    Location l3 = new Location(UUID.randomUUID(), "CCL", 55, 10, -185.751602, 349.972757);
    Location l4 = new Location(UUID.randomUUID(), "USA", 1, 1, -200.000000, 500.000000);
    List<Location> locations = new ArrayList<>(Arrays.asList(l1,l2,l3));

    @BeforeEach
    void setUp() {
        locationDao = new PostgresLocationDao(jdbcTemplate);
        // Populate location table
        for (Location l : locations) {
            String sql = "INSERT INTO location(id, grid_id, grid_x, grid_y, longitude, latitude) " +
                    "VALUES(" +
                    "'" + l.getId() + "'," +
                    "'" + l.getGridId() + "'," +
                    l.getGridX() + "," +
                    l.getGridY()+ "," +
                    l.getLongitude() + "," +
                    l.getLatitude() +
                    ")";
            jdbcTemplate.execute(sql);
        }

    }

    @AfterEach
    void tearDown() {
        // Reset location table
        jdbcTemplate.execute("DELETE FROM location");
    }

    @Test
    void insertLocationShouldInsertLocation() {
        locationDao.insertLocation(l4.getId(), l4);
        Location result = locationDao.selectLocationById(l4.getId()).orElse(null);
        assertNotNull(result);
        assertEquals(l4.getId(), result.getId());
    }

    @Test
    void selectLocationByIdShouldReturnLocation() {
        UUID locationId = l1.getId();
        Location result = locationDao.selectLocationById(locationId).orElse(null);
        assertNotNull(result);
        assertEquals(locationId, result.getId());
    }

    @Test
    void selectAllLocationsShouldHaveThreeLocations() {
        List<Location> results = locationDao.selectAllLocations();
        assertEquals(3, results.size());
    }

    @Test
    void updateLocationByIdShouldUpdateGridIdGridXGridY() {
        UUID locationId = l1.getId();
        Location updateLocation = new Location(null, "LOL", 10, 1, l1.getLongitude(), l1.getLatitude());

        locationDao.updateLocationById(locationId, updateLocation);

        Location result = locationDao.selectLocationById(locationId).orElse(null);
        assertNotNull(result);
        assertNotEquals(l1.getGridId(), result.getGridId());
        assertNotEquals(l1.getGridX(), result.getGridX());
        assertNotEquals(l1.getGridY(), result.getGridY());
    }

    @Test
    void updateLocationByIdShouldUpdateLongitudeLatitude() {
        UUID locationId = l1.getId();
        Location updateLocation = new Location(null, l1.getGridId(), l1.getGridX(), l1.getGridY(), 10.123467, 12.3456789);

        locationDao.updateLocationById(l1.getId(), updateLocation);

        Location result = locationDao.selectLocationById(locationId).orElse(null);
        assertNotNull(result);
        assertNotEquals(l1.getLongitude(), result.getLongitude());
        assertNotEquals(l1.getLatitude(), result.getLatitude());
    }

    @Test
    void selectAllLocationsByValuesShouldReturnOne() {
        assertEquals(1, locationDao.selectAllLocationsByValues(l1).size());
    }

    @Test
    void deleteLocationByIdShouldDeleteLocation() {
        UUID locationId = l2.getId();
        locationDao.deleteLocationById(locationId);
        assertThrows(EmptyResultDataAccessException.class, () -> locationDao.selectLocationById(locationId).orElse(null));
    }

    @Test
    void deleteAddressByIdShouldNotDeleteAnyAddress() {
        locationDao.deleteLocationById(UUID.randomUUID());
        List<Location> results = locationDao.selectAllLocations();
        assertEquals(3, results.size());
    }
}