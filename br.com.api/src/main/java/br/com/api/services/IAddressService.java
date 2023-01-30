package br.com.api.services;

import br.com.api.dto.AddressDTO;
import br.com.api.dto.AddressStatusDTO;
import br.com.api.entities.Address;

import java.util.List;

public interface IAddressService {

    List<Address> findByPersonId(Long id);

    Address create(AddressDTO address);

    void updateStatus(AddressStatusDTO addressStatusDTO, Long id);
}
