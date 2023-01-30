package br.com.api.controllers;

import br.com.api.dto.PersonDTO;
import br.com.api.entities.Person;
import br.com.api.services.IPersonService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/persons")
public class PersonController {

    private final IPersonService personService;

    public PersonController(IPersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Person> findAll() {
        return personService.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Person findById(@PathVariable Long id) {
        return personService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Person create(@Valid @RequestBody PersonDTO person) {
        return personService.create(person);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Person update(@PathVariable Long id, @RequestBody PersonDTO person) {
        return personService.update(id, person);
    }

}
