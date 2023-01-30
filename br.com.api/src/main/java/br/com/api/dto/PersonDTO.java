package br.com.api.dto;

import br.com.api.entities.Address;
import br.com.api.entities.Person;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersonDTO {
    private Long id;

    @NotBlank(message = "field not found")
    @Size(min = 3, max = 100, message = "Name must be between 3 or 100 characters")
    private String name;

    @Past(message = "Invalid date")
    private LocalDate birthDate;

    private List<Address> addresses = new ArrayList<>();

    public PersonDTO() {
    }

    public PersonDTO(Person person) {
        this.id = person.getId();
        this.name = person.getName();
        this.birthDate = person.getBirthDate();
        this.addresses = person.getAddresses();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }
}
