package com.jrescalona.rainalertserver.doa;

import com.jrescalona.rainalertserver.model.Address;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IAddressDoa {
    int insertAddress(Address address);
    int insertAddress(UUID id, Address address);
    Optional<Address> selectAddressById(UUID id);
    List<Address> selectAllAddresses();
    int updateAddressById(UUID id, Address address);
    int deleteAddressById(UUID id);
}
