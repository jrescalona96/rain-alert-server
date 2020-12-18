package com.jrescalona.rainalertserver.dao;


import com.jrescalona.rainalertserver.mapper.AddressMapper;
import com.jrescalona.rainalertserver.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("PostgresAddressDao")
public class PostgresAddressDao implements IAddressDao {

    private final JdbcTemplate jdbcTemplate;
    private final ILocationDao locationDao;

    @Autowired
    public PostgresAddressDao(JdbcTemplate jdbcTemplate, @Qualifier("PostgresLocationDao") ILocationDao locationDoa) {
        this.jdbcTemplate = jdbcTemplate;
        this.locationDao = locationDoa;
    }

    /**
     * Inserts new address and location if new
     * @param id Address id
     * @param address new Address to insert
     * @return 0
     */
    @Override
    public int insertAddress(UUID id, Address address) {
        // TODO: check if location is new
        UUID locationId = UUID.randomUUID();
        locationDao.insertLocation(locationId, address.getLocation());
        final String INSERT_SQL = "INSERT INTO address(id, address_line1, address_line2, city, state, postal_code, location_id)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(
                INSERT_SQL,
                locationId,
                address.getAddressLine1(),
                address.getAddressLine2(),
                address.getCity(),
                address.getState(),
                address.getPostalCode(),
                locationId);
        return 0;
    }

    @Override
    public Optional<Address> selectAddressById(UUID id) {
        final String SQL = "SELECT " +
                        "a.id as address_id" +
                        "a.address_line1, " +
                        "a.address_line2, " +
                        "a.city, " +
                        "a.state," +
                        "a.postal_code" +
                    "\nFROM address a" +
                    "\nJOIN location l" +
                    "\nON l.id = a.location_id";
        Address address = jdbcTemplate.queryForObject(SQL, new AddressMapper(), id);
        return Optional.ofNullable(address);
    }

    @Override
    public List<Address> selectAllAddresses() {
        return null;
    }

    @Override
    public int updateAddressById(UUID id, Address address) {
        return 0;
    }

    @Override
    public int deleteAddressById(UUID id) {
        return 0;
    }
}
