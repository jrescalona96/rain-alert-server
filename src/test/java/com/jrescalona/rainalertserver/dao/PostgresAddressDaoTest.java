package com.jrescalona.rainalertserver.dao;

import com.jrescalona.rainalertserver.model.Address;
import com.jrescalona.rainalertserver.model.Location;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class PostgresAddressDaoTest {
    PostgresAddressDao addressDao;
    // connect to db
    JdbcTemplate jdbcTemplate = new JdbcTemplate(
            DataSourceBuilder
                    .create()
                    .type(HikariDataSource.class)
                    .url("jdbc:postgresql://localhost:5432/rain_alert_db_test")
                    .username("postgres")
                    .password("dbfortesting")
                    .build()
    );

    Location l1 = new Location(UUID.randomUUID(), "DAS", 48, 8, -261.253486, -196.207582);
    Location l2 = new Location(UUID.randomUUID(), "UED", 93, 51, 355.769283, -234.434096);
    Location l3 = new Location(UUID.randomUUID(), "CCL", 55, 10, -185.751602, 349.972757);
    Location l4 = new Location(UUID.randomUUID(), "USA", 1, 1, -200.000000, 500.000000);
    Address a1 = new Address(UUID.randomUUID(),"33243 Vahlen Drive", null,"Clearwater", "FL", "33763", l1);
    Address a2 = new Address(UUID.randomUUID(),"19733 Katie Crossing", null,"New Orleans", "LA", "70187", l2);
    Address a3 = new Address(UUID.randomUUID(),"96301 Twin Pines Road", null,"Milwaukee", "WI", "53277", l3);
    Address a4 = new Address(UUID.randomUUID(),"1111 Single Pine2 Road", "Apt. N","Los Angeles", "CA", "90045", l3);

    List<Address> addresses = new ArrayList<>(Arrays.asList(a1,a2,a3));
    List<Location> locations  = new ArrayList<>(Arrays.asList(l1,l2,l3));

    @BeforeEach
    void setUp() {
        PostgresLocationDao locationDao = new PostgresLocationDao(jdbcTemplate);
        addressDao = new PostgresAddressDao(jdbcTemplate, locationDao);

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
    }

    @AfterEach
    void tearDown() {
        jdbcTemplate.execute("DELETE FROM address");
        jdbcTemplate.execute("DELETE FROM location");
    }

    @Test
    void insertAddressShouldInsertAddress() {
        addressDao.insertAddress(l4.getId(), a4);
        Address result = addressDao.selectAddressById(l4.getId()).orElse(null);
        assertNotNull(result);
        assertEquals(l4.getId(), result.getId());
    }

    @Test
    void selectAddressByIdShouldReturnAnAddress() {
        UUID addressId = a1.getId();
        Address result = addressDao.selectAddressById(addressId).orElse(null);
        assertNotNull(result);
        assertEquals(addressId, result.getId());
    }

    @Test
    void selectAllAddressesShouldReturnThreeAddresses() {
        List<Address> results = addressDao.selectAllAddresses();
        assertEquals(3, results.size());
    }

    @Test
    void updateAddressByIdShouldHaveDifferentAddressLine1() {
        UUID addressId = a1.getId();
        Address updateAddress = new Address(null, "123 Different Add.", a1.getAddressLine2(), a1.getCity(), a1.getState(), a1.getPostalCode(), a1.getLocation());

        addressDao.updateAddressById(addressId, updateAddress);

        Address result = addressDao.selectAddressById(addressId).orElse(null);
        assertNotNull(result);
        assertNotEquals(a1.getAddressLine1(), result.getAddressLine1());
        assertEquals(a1.getAddressLine2(), result.getAddressLine2());
        assertEquals(a1.getCity(), result.getCity());
        assertEquals(a1.getState(), result.getState());
        assertEquals(a1.getPostalCode(), result.getPostalCode());
    }

    @Test
    void updateAddressByIdShouldHaveDifferentAddressLine2() {
        UUID addressId = a1.getId();
        Address updateAddress = new Address(null, a1.getAddressLine1(), "Apt. N", a1.getCity(), a1.getState(), a1.getPostalCode(), a1.getLocation());

        addressDao.updateAddressById(addressId, updateAddress);

        Address result = addressDao.selectAddressById(addressId).orElse(null);
        assertNotNull(result);
        assertEquals(a1.getAddressLine1(), result.getAddressLine1());
        assertNotEquals(a1.getAddressLine2(), result.getAddressLine2());
        assertEquals(a1.getCity(), result.getCity());
        assertEquals(a1.getState(), result.getState());
        assertEquals(a1.getPostalCode(), result.getPostalCode());
    }

    @Test
    void updateAddressByIdShouldHaveDifferentCity() {
        UUID addressId = a1.getId();
        Address updateAddress = new Address(null, a1.getAddressLine1(), a1.getAddressLine2(), "Basco", a1.getState(), a1.getPostalCode(), a1.getLocation());

        addressDao.updateAddressById(addressId, updateAddress);

        Address result = addressDao.selectAddressById(addressId).orElse(null);
        assertNotNull(result);
        assertEquals(a1.getAddressLine1(), result.getAddressLine1());
        assertEquals(a1.getAddressLine2(), result.getAddressLine2());
        assertNotEquals(a1.getCity(), result.getCity());
        assertEquals(a1.getState(), result.getState());
        assertEquals(a1.getPostalCode(), result.getPostalCode());
    }

    @Test
    void updateAddressByIdShouldHaveDifferentState() {
        UUID addressId = a1.getId();
        Address updateAddress = new Address(null, a1.getAddressLine1(), a1.getAddressLine2(), a1.getCity(), "XY", a1.getPostalCode(), a1.getLocation());

        addressDao.updateAddressById(addressId, updateAddress);

        Address result = addressDao.selectAddressById(addressId).orElse(null);
        assertNotNull(result);
        assertEquals(a1.getAddressLine1(), result.getAddressLine1());
        assertEquals(a1.getAddressLine2(), result.getAddressLine2());
        assertEquals(a1.getCity(), result.getCity());
        assertNotEquals(a1.getState(), result.getState());
        assertEquals(a1.getPostalCode(), result.getPostalCode());
    }

    @Test
    void updateAddressByIdShouldHaveDifferentPostalCode() {
        UUID addressId = a1.getId();
        Address updateAddress = new Address(null, a1.getAddressLine1(), a1.getAddressLine2(), a1.getCity(), a1.getState(),"000000", a1.getLocation());

        addressDao.updateAddressById(addressId, updateAddress);

        Address result = addressDao.selectAddressById(addressId).orElse(null);
        assertNotNull(result);
        assertEquals(a1.getAddressLine1(), result.getAddressLine1());
        assertEquals(a1.getAddressLine2(), result.getAddressLine2());
        assertEquals(a1.getCity(), result.getCity());
        assertNotEquals(a1.getState(), result.getState());
        assertEquals(a1.getPostalCode(), result.getPostalCode());
    }

    @Test
    void updateAddressByIdShouldHaveDifferentLocation() {
        UUID addressId = a1.getId();
        Address updateAddress = new Address(null, a1.getAddressLine1(), a1.getAddressLine2(), a1.getCity(), a1.getState(),a1.getPostalCode(), l4);

        addressDao.updateAddressById(addressId, updateAddress);

        Address result = addressDao.selectAddressById(addressId).orElse(null);
        assertNotNull(result);
        assertEquals(a1.getAddressLine1(), result.getAddressLine1());
        assertEquals(a1.getAddressLine2(), result.getAddressLine2());
        assertEquals(a1.getCity(), result.getCity());
        assertEquals(a1.getState(), result.getState());
        assertNotEquals(a1.getPostalCode(), result.getPostalCode());
    }

    @Test
    void deleteAddressByIdShouldDeleteAddress() {
        UUID addressId = a2.getId();
        addressDao.deleteAddressById(addressId);
        Address address = addressDao.selectAddressById(addressId).orElse(null);
        assertNull(address);
    }

    @Test
    void deleteAddressByIdShouldNotDeleteAnyAddress() {
        addressDao.deleteAddressById(UUID.randomUUID());
        List<Address> results = addressDao.selectAllAddresses();
        assertEquals(3, results.size());
    }
}