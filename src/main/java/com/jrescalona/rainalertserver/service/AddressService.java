package com.jrescalona.rainalertserver.service;

import com.jrescalona.rainalertserver.doa.IAddressDoa;
import com.jrescalona.rainalertserver.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AddressService {
    private final IAddressDoa addressDoa;

    @Autowired
    public AddressService(@Qualifier("PostgresAddressDoa") IAddressDoa addressDoa) {
        this.addressDoa = addressDoa;
    }

    /**
     * Adds a new address
     * @param address Address
     */
    public void addAddress(Address address) throws RuntimeException {
        addressDoa.insertAddress(address);
    }

    /**
     * Gets all available projects
     * @return List<Address>
     */
    public List<Address> getAllAddresses() {
        return addressDoa.selectAllAddresses();
    }

    /**
     * Gets project with given id
     * @param id UUID
     * @return Address if found, null otherwise
     */
    public Address getAddressById(UUID id) {
        return addressDoa.selectAddressById(id).orElse(null);
    }


    /**
     * Update a project with new project instance using id
     * @param id UUID
     * @param address Address
     * @return 0 if successful, 1 otherwise
     */
    public int updateAddressById(UUID id, Address address) {
        return addressDoa.updateAddressById(id, address);
    }

    /**
     * Delete a project using id
     * @param id UUID
     * @return 0 if successful, 1 otherwise
     */
    public int deleteAddressById(UUID id) {
        return addressDoa.deleteAddressById(id);
    }
}
