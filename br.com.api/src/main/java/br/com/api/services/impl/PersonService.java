package br.com.api.services.impl;

import br.com.api.dto.PersonDTO;
import br.com.api.entities.Address;
import br.com.api.entities.Person;
import br.com.api.exception.exceptions.PersonNotFoundException;
import br.com.api.repositories.PersonRepository;
import br.com.api.services.IPersonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PersonService implements IPersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> findAll() {
        List<Person> persons = personRepository.findAll();
        if (persons.isEmpty()) {
            throw new PersonNotFoundException("no person registered, perform the first registration");
        }

        return persons;
    }

    @Override
    public Person findById(Long id) {
        Optional<Person> obj = personRepository.findById(id);
        if (obj.isEmpty()) {
            throw new PersonNotFoundException("A person with id " + id + " was not found.");
        }

        return obj.get();
    }

    @Override
    public Person create(PersonDTO personDTO) {
        Person person = new Person();
        person.setName(personDTO.getName());
        person.setBirthDate(personDTO.getBirthDate());
        for (Address ad : personDTO.getAddresses()) {
            person.addAddress(ad);
        }

        return personRepository.save(person);
    }

    @Override
    public Person update(Long id, PersonDTO person) {
        Person entity = personRepository.getReferenceById(id);
        Optional<Person> personCheck = personRepository.findById(id);

        if (personCheck.isEmpty()) {
            throw new PersonNotFoundException("A person with id " + id + " was not found.");
        }

        updateData(entity, person);
        return personRepository.save(entity);
    }

    private void updateData(Person entity, PersonDTO person) {
        entity.setName(person.getName());
        entity.setBirthDate(person.getBirthDate());
    }
}
