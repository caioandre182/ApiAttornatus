package br.com.api.services;

import br.com.api.dto.PersonDTO;
import br.com.api.entities.Person;

import java.util.List;

public interface IPersonService {

    List<Person> findAll();

    Person findById(Long id);

    Person create(PersonDTO personDTO);

    Person update(Long id, PersonDTO person);
}
