package br.com.api.controllers;

import br.com.api.dto.AddressDTO;
import br.com.api.entities.Address;
import br.com.api.services.IAddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/address")
public class AddressController {

    @Autowired
    private final IAddressService addressService;

    public AddressController(IAddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Address> findByPersonId(@PathVariable Long id) {
        return addressService.findByPersonId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Address create(@Valid @RequestBody AddressDTO address) {

        return addressService.create(address);
    }

}
