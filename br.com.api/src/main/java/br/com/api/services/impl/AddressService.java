package br.com.api.services.impl;

import br.com.api.dto.AddressDTO;
import br.com.api.dto.AddressStatusDTO;
import br.com.api.entities.Address;
import br.com.api.entities.Person;
import br.com.api.entities.enums.AddressStatus;
import br.com.api.exception.exceptions.AddressNotFoundException;
import br.com.api.exception.exceptions.PersonNotFoundException;
import br.com.api.exception.exceptions.PrincipalAddressExistsException;
import br.com.api.repositories.AddressRepository;
import br.com.api.services.IAddressService;
import br.com.api.services.IPersonService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService implements IAddressService {

    private final AddressRepository addressRepository;
    private final IPersonService personService;

    public AddressService(AddressRepository addressRepository, IPersonService personService) {
        this.addressRepository = addressRepository;
        this.personService = personService;
    }

    @Override
    public List<Address> findByPersonId(Long id) {
        Person person = personService.findById(id);
        if (person == null) {
            throw new PersonNotFoundException("A person with id " + id + " was not found.");
        }
        List<Address> addresses = addressRepository.findByPersonId(id);
        if (addresses.isEmpty()) {
            throw new AddressNotFoundException("No address were found for person with id " + id + ".");
        }

        return addresses;
    }

    @Override
    public Address create(AddressDTO addressDTO) {
        Person person = personService.findById(addressDTO.getPerson().getId());


        AddressStatus status = AddressStatus.valueOf(addressDTO.getStatus());

        Address address = new Address();
        address.setStreet(addressDTO.getStreet());
        address.setPostalCode(addressDTO.getPostalCode());
        address.setNumber(addressDTO.getNumber());
        address.setCity(addressDTO.getCity());
        address.setStatus(status);


        address.setPerson(person);

        if (checkPrincipal(address)) {
            List<Address> addresses = address.getPerson().getAddresses();
            for (Address ad : addresses) {
                if (checkPrincipal(ad)) {
                    throw new PrincipalAddressExistsException("A PRINCIPAL address already exists");
                }
            }

        }

        return addressRepository.save(address);
    }

    @Override
    public void updateStatus(AddressStatusDTO addressStatusDTO, Long id) {
        // BUSCANDO O ID DA PESSOA
        Person person = personService.findById(id);

        // OBTENDO O STATUS (AddressStatus)
        AddressStatus status = AddressStatus.valueOf(addressStatusDTO.getStatus());

        // BUSCANDO OS ENDEREÇOS DA PESSOA
        List<Address> addresses = person.getAddresses();

        // VERIFICANDO SE O ID DE ENDEREÇO CONSTA NA LISTA DE ENDEREÇOS
        if(!checkIdExists(addresses, addressStatusDTO.getId())){
            throw new AddressNotFoundException("Invalid id");
        }

        // CASO O STATUS PASSADO FOR SECONDARY
        if(addressStatusDTO.getStatus() == 2){
            for(Address ad: addresses){
                if(ad.getId().equals(addressStatusDTO.getId())){
                    ad.setStatus(status);
                    addressRepository.save(ad);
                }
            }
        } else {
            // ALTERANDO O STATUS PRINCIPAL, PARA GARANTIR QUE NÃO TENHA DOIS
            for(Address ad: addresses){
                if(checkPrincipal(ad)){
                    ad.setStatus(AddressStatus.SECONDARY);
                }
            }
            for(Address ad: addresses){
                if(ad.getId().equals(addressStatusDTO.getId())){
                    ad.setStatus(status);
                    addressRepository.save(ad);
                }
            }
        }

    }

    private boolean checkIdExists(List<Address> addresses, Long id){
        boolean verify = false;
        for(Address ad : addresses){
            if(ad.getId().equals(id)){
                verify = true;
            }
        }
        return verify;
    }


    private boolean checkPrincipal(Address address) {
        return address.getStatus().getCode() == 1;
    }
}
